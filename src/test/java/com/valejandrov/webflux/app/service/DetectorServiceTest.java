package com.valejandrov.webflux.app.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.InjectMocks;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class DetectorServiceTest {

	private final ArrayList<TestCase> humanCases;
	private final ArrayList<TestCase> mutantCases;

	private String[] dnaMutante1 = new String[] { "ATGCGA", "CAGTGC", "TTATGG", "AGAAGG", "CCCCTA", "TCGCTG" };

	private String[] dnaMutante2 = new String[] { "GTGCGA", "AAAAGC", "TCATGG", "CGATAG", "CCCTTA", "TTTTCG" };

	private String[] dnaHumano1 = new String[] { "GTGC", "CACT", "TCCT", "TCGC" };

	private String[] dnaHumano2 = new String[] { "GTGC", "CCCC", "TCCT", "TCGC" };

	
	
	public DetectorServiceTest() {
		humanCases = new ArrayList<>();
		mutantCases = new ArrayList<>();

		humanCases.add(new TestCase("4x4 1H", new String[] { 
				"GTGT", 
				"GGTG", 
				"CCCC", 
				"TGCA" 
				}, false));

		humanCases.add(new TestCase("4x4 1V", new String[] { 
				"TCCC", 
				"TCTC", 
				"TTCA", 
				"TCAG"
				}, false));

		humanCases.add(new TestCase("4x4 0", new String[] { 
				"CCGT", 
				"CCCT", 
				"GACA", 
				"CCAG"
				}, false));
		
		mutantCases.add(new TestCase("4x4 2H", new String[] { 
				"GTGT", 
				"GGGG", 
				"CCTC", 
				"AAAA" 
				}, true));
		
		mutantCases.add(new TestCase("4x4 2H", new String[] { 
				"GTGT", 
				"TTTT", 
				"CCTC", 
				"AAAA" 
				}, true));
		
		mutantCases.add(new TestCase("4x4 1DP 1DS", new String[] { 
				"GTGA", 
				"TGAT", 
				"CAGC", 
				"AAAG" 
				}, true));
		
		mutantCases.add(new TestCase("4x4 2V", new String[] { 
				"GTGC", 
				"GGAC", 
				"GAGC", 
				"GAAC" 
				}, true));
		
		mutantCases.add(new TestCase("5x5 2DP", new String[] { 
				"GTGCA", 
				"CGTCA", 
				"GAGTA",
				"GAGGT", 
				"CAAGT" 
				}, true));
		
		mutantCases.add(new TestCase("5x5 2DS", new String[] { 
				"GTGCAC", 
				"CGCAAC", 
				"GCACAC",
				"CAGGCT",
				"CAGGCC",
				"CAAGTC" 
				}, true));

	}


	
	@Test
	public void testHumanos() {
		System.out.println("\nHumanos");
		testIsMutant(humanCases);
	}
	
	@Test
	public void testMutantes() {
		System.out.println("\nMutantes");
		testIsMutant(mutantCases);
	}
	
	
	
	public void testIsMutant(ArrayList<TestCase> cases) {
		System.out.println(cases);
		for (TestCase t : cases) {
			System.out.println(t);
			Mono<Boolean> uno = DetectorService.isMutant(t.getDna());
		    StepVerifier.create(uno).expectNext(t.expectedResult).verifyComplete();
		}
		
	}

}
