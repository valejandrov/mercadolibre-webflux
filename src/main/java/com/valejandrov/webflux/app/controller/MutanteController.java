package com.valejandrov.webflux.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.valejandrov.webflux.app.service.MainService;
import com.valejandrov.webflux.app.entity.Mutant;
import com.valejandrov.webflux.app.service.IAdnVerificadosService;

import reactor.core.publisher.Mono;

import java.sql.Timestamp;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/")
public class MutanteController {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MainService mainService;
	
	@GetMapping("/stats")
	public String estadistica() {
		return "todo";
	}

	@PostMapping(value = "/mutante", consumes = "application/json", produces = "application/json")
	String createPerson(@RequestBody String dna,ServerHttpResponse response) throws Exception {
		System.out.println("Thread Count: " + Thread.activeCount());
		
		Mono<Boolean> respuesta = mainService.start(dna);
		
		respuesta.subscribe(isMutant -> {
			if(isMutant) {
				response.setStatusCode(HttpStatus.OK);
			}else {
				response.setStatusCode(HttpStatus.FORBIDDEN);
			}
		});
		
		return "";
	}
}
