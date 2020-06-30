package com.valejandrov.webflux.app.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valejandrov.webflux.app.entity.Mutant;
import com.valejandrov.webflux.app.repository.IAdnVerificadosRepository;

@Service
public class AdnVerificadosServiceImpl implements IAdnVerificadosService{

	@Autowired
	private IAdnVerificadosRepository adnVerificadosRepository;
	

	public void add(Mutant mutante) throws InterruptedException, ExecutionException {
		adnVerificadosRepository.add(mutante);
	}


	public String[] get(long id) throws InterruptedException, ExecutionException {
		return adnVerificadosRepository.get(id);
	}

	public List<String[]> getAll() throws InterruptedException, ExecutionException {
		return adnVerificadosRepository.getAll();
	}
	
}
