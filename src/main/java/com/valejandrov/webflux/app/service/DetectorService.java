package com.valejandrov.webflux.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class DetectorService {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static Mono<Boolean> isMutant(String[] adn) {

		Mono<Boolean> mutante;
		int contadorSecuenciaMutante = 0;
		
		contadorSecuenciaMutante += horizontalDetection(adn,contadorSecuenciaMutante);
		
		if(contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}
		
		contadorSecuenciaMutante += verticalDetection(adn,contadorSecuenciaMutante);
		
		System.out.println("Contador de mutantes: "+contadorSecuenciaMutante);
		
		if(contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}else {
			return Mono.just(false);
		}
		
	}

	
	private static int horizontalDetection(String[] adn,int contadorSecuenciaMutante) {

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

	
	private static int verticalDetection(String[] adn,int contadorSecuenciaMutante) {

		long startTime = System.nanoTime();

		int x = 0;
		int y = 0;
		int igualdades = 0;
		boolean continuar = true;
		
		while(x < adn.length && contadorSecuenciaMutante < 2){
			
			while( contadorSecuenciaMutante < 2 && ( ( adn.length - y - 1 ) >= (3-igualdades) ) && continuar ){
				System.out.println("Comparando "+adn[y].charAt(x)+" con "+adn[y + 1].charAt(x));
				
				if (adn[y].charAt(x) == adn[y + 1].charAt(x)) {
					igualdades++;
					System.out.println("Igualdades:"+igualdades);
					if( igualdades == 3 ) {
						System.out.println("Columna: "+x);
						contadorSecuenciaMutante++;
						continuar = false;
					}
				} else {
					igualdades = 0;
				}
				y++;
			}
			x++;
			y = 0;
			igualdades = 0;
			continuar = true;
		}
		
		long endTime = System.nanoTime() - startTime;
		System.out.println(endTime);
		return contadorSecuenciaMutante;	
	}

}
