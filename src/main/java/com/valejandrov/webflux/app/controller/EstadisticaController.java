package com.valejandrov.webflux.app.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.valejandrov.webflux.app.service.MainService;

@RestController 
public class EstadisticaController {
	
	@Autowired
	private MainService mainService;
	
	@GetMapping(value = "/stats", produces = "application/json")
	public JSONObject estadistica() throws Exception {
		return mainService.stats();		
	}
	
}
