package com.valejandrov.webflux.app.service;

import reactor.core.publisher.Mono;

public class DetectorService {

	public static Mono<Boolean> isMutant(String[] adn) {

		Mono<Boolean> mutante;
		int contadorSecuenciaMutante = 0;
		
		contadorSecuenciaMutante = horizontalDetection(adn,contadorSecuenciaMutante);
		
		if(contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}
		
		return Mono.just(false);

	}

	
	private static int horizontalDetection(String[] adn,int contadorSecuenciaMutante) {
		// Secuencias horizontales
		int fila = 0;
		while (fila < adn.length && contadorSecuenciaMutante < 2) {

			if (adn[fila].contains("CCCC")) {
				System.out.println("Tiene C");
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("AAAA")) {
				System.out.println("Tiene A");
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("GGGG")) {
				System.out.println("Tiene G");
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("TTTT")) {
				System.out.println("Tiene T");
				contadorSecuenciaMutante++;
			}
			fila++;
		}
		return contadorSecuenciaMutante;
	}

	
	/*
	private static Mono<Boolean> verticalDetection(String[] adn) {

		long startTime = System.nanoTime();

		// Secuencias verticales
		int x = 0;
		int y = 0;
		int letrasIguales = 0;
		while (x < adn.length && contadorSecuenciaMutante < 2) {
			if (adn[y].charAt(x) == adn[y + 1].charAt(x)) {
				letrasIguales++;
			} else {
				letrasIguales = 0;
			}
			y++;

		}

		if (contadorSecuenciaMutante >= 2)
			return true;

		long endTime = System.nanoTime() - startTime;
		System.out.println(endTime);
		
	}
	*/
}
