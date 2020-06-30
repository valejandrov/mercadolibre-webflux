package com.valejandrov.webflux.app.service;

import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.valejandrov.webflux.app.entity.Mutant;

import reactor.core.publisher.Mono;

@Service
public class MainService {

	@Autowired
	private IAdnVerificadosService adnVerificadosService;
	
	public Mono<Boolean> start(String dna) throws ParseException{

		System.out.println("String: " + dna);

		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = (JSONObject) jsonParser.parse(dna);
		JSONArray jsonArray = (JSONArray) jsonObject.get("dna");

		String[] stringsArray = new String[jsonArray.size()];
		for (int i = 0; i < jsonArray.size(); i++) {
			stringsArray[i] = (String) jsonArray.get(i);
		}

		Mono<Boolean> respuesta = DetectorService.isMutant(stringsArray);
		
		respuesta.subscribe(isMutant -> {
			if(isMutant) {
				try {
					Mutant mutante = new Mutant();
					mutante.setDna(dna);
					adnVerificadosService.add(mutante);
					System.out.println(new Timestamp(System.currentTimeMillis()));
				} catch (Exception e) {
					System.out.println("El registro no se ha guardado!");
					System.out.println(e);
				}		
			}
		});
		return respuesta;		
	}

}
