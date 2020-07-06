package com.valejandrov.webflux.app.service;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valejandrov.webflux.app.entity.Mutant;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Service
public class MainService {

	@Autowired
	private IAdnService adnService;
		
	public MainService() {
		super();
	}


	public Mono<Boolean> start(String dna) throws ParseException{

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(dna);
		JSONArray jsonArray = (JSONArray) jsonObject.get("dna");

		String[] dnaArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			dnaArray[i] = (String) jsonArray.get(i);
		}

		Mono<Boolean> respuesta = DetectorService.isMutant(dnaArray);
		
		Scheduler s = Schedulers.elastic();
		respuesta.publishOn(s).subscribe(isMutant -> {
			if(isMutant) {
				try {
					Mutant mutante = new Mutant();
					mutante.setDna(dna);
					adnService.add(mutante);
				} catch (Exception e) {
					System.out.println("Mutante: Problema para acceder a la base!");
					System.out.println(e);
				}		
			}else {
				try {
					adnService.countHuman();
				} catch (Exception e) {
					System.out.println("Humano: Problema para acceder a la base!");
					System.out.println(e);
				}		
			}
		});
		
		return respuesta;		
	}
	
	
	public JSONObject stats() throws ParseException, InterruptedException, ExecutionException{
		return adnService.stats();
	}
}
