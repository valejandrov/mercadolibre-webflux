package com.valejandrov.webflux.app.service;

import org.mockito.InjectMocks;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.valejandrov.webflux.app.repository.IAdnRepository;

@SpringBootTest
public class AdnServiceImplTest {

	// Create a mock of Resource to change its behaviour for testing
	@MockBean
	private IAdnRepository adnRepository;

	@InjectMocks
    private AdnServiceImpl service;

	/*
		
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
		
		IAdnRepository adnRepository = mock(IAdnRepository.class);
		when(adnRepository.getEstadistica()).thenReturn(est);
		
		System.out.println(adnRepository.getEstadistica().getHumanos());
		
		assertEquals(json, service.stats());
	}
	*/
}

		/*
		
		AdnServiceImpl adnService = new AdnServiceImpl();
        
        JSONObject json = new JSONObject();
        String respuesta = "{\"count_mutant_dna\": 5, \"count_human_dna\": 10, \"ratio\": 0.5}";
        JSONParser parser = new JSONParser();
        json = (JSONObject) parser.parse(respuesta);
		
		assertEquals(json, adnService.stats());
		
		*/

