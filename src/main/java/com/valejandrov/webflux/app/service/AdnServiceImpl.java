package com.valejandrov.webflux.app.service;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valejandrov.webflux.app.entity.Estadistica;
import com.valejandrov.webflux.app.entity.Mutant;
import com.valejandrov.webflux.app.repository.IAdnRepository;

@Service
public class AdnServiceImpl implements IAdnService{

	@Autowired
	private IAdnRepository adnRepository;
	
	public void add(Mutant mutante) throws InterruptedException, ExecutionException {
		adnRepository.addMutantAndCount(mutante);
	}
	
	public void countHuman() throws InterruptedException, ExecutionException {
		adnRepository.countHuman();
	}

	public JSONObject stats() throws InterruptedException, ExecutionException {
		
		Estadistica est;
		JSONObject json = new JSONObject();
		try {
			est = adnRepository.getEstadistica();
			int humanos = est.getHumanos();
			int mutantes = est.getMutantes();
			
			double ratio;
			ratio = (mutantes*1.0)/humanos;
			System.out.println(ratio);
					
			NumberFormat nf_out = NumberFormat.getNumberInstance(Locale.UK);
			nf_out.setMaximumFractionDigits(2);
			String output = nf_out.format(ratio);
			String respuesta = "{\"count_mutant_dna\": "+String.valueOf(est.getMutantes())+", \"count_human_dna\": "+ String.valueOf(est.getHumanos())
									+", \"ratio\": "+output+"}";
			System.out.println(respuesta);
			
			JSONParser parser = new JSONParser();
			json = (JSONObject) parser.parse(respuesta);
		} catch (Exception e) {
			e.getMessage();
		}
				
		return json;		
	}
	
}
