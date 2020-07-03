package com.valejandrov.webflux.app.repository;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;

import com.valejandrov.webflux.app.entity.Mutant;

public interface IAdnRepository {
	
	public void addMutantAndCount(Mutant mutante) throws InterruptedException, ExecutionException;
	
	public void countHuman() throws InterruptedException, ExecutionException;
	
	public JSONObject getSummary() throws InterruptedException, ExecutionException;
		
}
