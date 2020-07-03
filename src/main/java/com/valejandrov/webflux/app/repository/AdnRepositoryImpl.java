package com.valejandrov.webflux.app.repository;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.FieldValue;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.valejandrov.webflux.app.entity.Estadistica;
import com.valejandrov.webflux.app.entity.Mutant;

@Repository
public class AdnRepositoryImpl implements IAdnRepository {

	@Autowired
	private Firestore firestore;

	public void addMutantAndCount(Mutant mutante) throws InterruptedException, ExecutionException {
		this.firestore.collection("adnVerificados").add(mutante);
		DocumentReference resumen = this.firestore.collection("estadistica").document("resumen");
		final ApiFuture<WriteResult> updateFuture = resumen.update("mutantes", FieldValue.increment(1));
	}

	public void countHuman() {
		DocumentReference resumen = this.firestore.collection("estadistica").document("resumen");
		final ApiFuture<WriteResult> updateFuture = resumen
				    .update("humanos", FieldValue.increment(1));
	}

	public JSONObject getSummary() throws InterruptedException, ExecutionException {
		
		ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = this.firestore.collection("estadistica").document("resumen").get();
		Estadistica est = documentSnapshotApiFuture.get().toObject(Estadistica.class);
		System.out.println("Humanos: "+est.getHumanos()+" , Mutantes:"+est.getMutantes());
		
		int humanos = est.getHumanos();
		int mutantes = est.getMutantes();
		double ratio;
		ratio = (mutantes*1.0)/humanos;
		System.out.println(ratio);
				
		NumberFormat nf_out = NumberFormat.getNumberInstance(Locale.UK);
		nf_out.setMaximumFractionDigits(2);
		String output = nf_out.format(ratio);
		
		String respuesta = "{\"count_mutant_dna\": "+String.valueOf(est.getMutantes())+", \"count_human_dna\": "+ String.valueOf(est.getHumanos())
								+", \"ratio\": "+output+"}";
		System.out.println(respuesta);
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(respuesta);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return json;
	}

}