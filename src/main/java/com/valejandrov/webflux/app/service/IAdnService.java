package com.valejandrov.webflux.app.service;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;

import com.valejandrov.webflux.app.entity.Mutant;

public interface IAdnService {

		public void add(Mutant dna) throws InterruptedException, ExecutionException;
		
		public void countHuman() throws InterruptedException, ExecutionException;
		
		public JSONObject stats() throws InterruptedException, ExecutionException; 
}
