package com.valejandrov.webflux.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.valejandrov.webflux.app.entity.Estadistica;
import com.valejandrov.webflux.app.repository.AdnRepositoryImpl;



@RunWith(MockitoJUnitRunner.class)
public class AdnServiceImplTest {

	@InjectMocks
    private AdnServiceImpl service;

	@Mock
	private AdnRepositoryImpl adnServiceRepository;
	
	@Test
	public void statTest() throws InterruptedException, ExecutionException, ParseException {
		Estadistica est = new Estadistica();
		est.setHumanos(10);
		est.setMutantes(5);
		est.setRatio("0.5");
		
        JSONObject json = new JSONObject();
        String respuesta = "{\"count_mutant_dna\": 5, \"count_human_dna\": 10, \"ratio\": 0.5}";
        JSONParser parser = new JSONParser();
        json = (JSONObject) parser.parse(respuesta);
		
		when(adnServiceRepository.getEstadistica()).thenReturn(est);
		assertEquals(json,service.stats());
	}
}