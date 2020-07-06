package com.valejandrov.webflux.app.service;

import java.util.Arrays;

class TestCase {

	String name;
    String[] dna;
    boolean expectedResult;

    public TestCase(String name, String[] dna, boolean result) {
        this.name = name;
        this.dna = dna;
        this.expectedResult = result;
    }

	public String[] getDna() {
		return dna;
	}

	@Override
	public String toString() {
		return "TestCase [name=" + name + ", dna=" + Arrays.toString(dna) + ", expectedResult=" + expectedResult + "]";
	}
    
    


}