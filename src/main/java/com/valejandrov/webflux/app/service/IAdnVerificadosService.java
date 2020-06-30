package com.valejandrov.webflux.app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.valejandrov.webflux.app.entity.Mutant;

public interface IAdnVerificadosService {

		public void add(Mutant dna) throws InterruptedException, ExecutionException;
		
		String[] get(long id) throws InterruptedException, ExecutionException;
		
		List<String[]> getAll() throws InterruptedException, ExecutionException;
		
}
