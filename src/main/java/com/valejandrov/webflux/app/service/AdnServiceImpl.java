package com.valejandrov.webflux.app.service;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return adnRepository.getSummary();		
	}
	
}
