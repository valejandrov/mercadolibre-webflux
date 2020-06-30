package com.valejandrov.webflux.app.repository;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.cloud.firestore.Firestore;
import com.valejandrov.webflux.app.entity.Mutant;


@Repository
public class AdnVerificadosRepositoryImpl implements IAdnVerificadosRepository{

	@Autowired
	private Firestore firestore;
	
	public void add(Mutant mutante) throws InterruptedException, ExecutionException {
		System.out.println("Antes de grabar");
		//String id = this.firestore.document("adnVerificados").getId();
		this.firestore.collection("adnVerificados").add(mutante);
	}

	public String[] get(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String[]> getAll() {
		// TODO Auto-generated method stub
		return null;
	}



	
}
