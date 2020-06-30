package com.valejandrov.webflux.app.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.valejandrov.webflux.app.entity.Mutant;

public interface IAdnVerificadosRepository {
	
	public void add(Mutant mutante) throws InterruptedException, ExecutionException;
	
	public String[] get(long id);
	
	public List<String[]> getAll() throws InterruptedException, ExecutionException;
	
}
