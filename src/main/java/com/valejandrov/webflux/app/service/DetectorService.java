package com.valejandrov.webflux.app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Mono;

public class DetectorService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public static Mono<Boolean> isMutant(String[] adn) {
		System.out.println( Thread.currentThread().getName() );
		
		int contadorSecuenciaMutante = 0;

		contadorSecuenciaMutante += horizontalDetection(adn, contadorSecuenciaMutante);
		//System.out.println("Contador de mutantes[Horizontal]: " + contadorSecuenciaMutante);
		
		if (contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}

		contadorSecuenciaMutante = verticalDetection(adn, contadorSecuenciaMutante);
		//System.out.println("Contador de mutantes[Vertical]: " + contadorSecuenciaMutante);
		
		if (contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}
		
		contadorSecuenciaMutante = mainDiagonalDetection(adn, contadorSecuenciaMutante);
		//System.out.println("Contador de mutantes[Diagonal-Principal]: " + contadorSecuenciaMutante);
		
		if (contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		}

		contadorSecuenciaMutante = secondaryDiagonalDetection(adn, contadorSecuenciaMutante);
		//System.out.println("Contador de mutantes[Diagonal-Secundaria]: " + contadorSecuenciaMutante);
		
		if (contadorSecuenciaMutante >= 2) {
			return Mono.just(true);
		} else {
			return Mono.just(false);
		}

	}

	private static int horizontalDetection(String[] adn, int contadorSecuenciaMutante) {

		int fila = 0;
		while (fila < adn.length && contadorSecuenciaMutante < 2) {

			if (adn[fila].contains("CCCC")) {
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("AAAA")) {
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("GGGG")) {
				contadorSecuenciaMutante++;
			}
			if (adn[fila].contains("TTTT")) {
				contadorSecuenciaMutante++;
			}
			fila++;
		}
		return contadorSecuenciaMutante;
	}

	private static int verticalDetection(String[] adn, int contadorSecuenciaMutante) {

		int x = 0;
		int y = 0;
		int igualdades = 0;
		boolean continuar = true;

		while (x < adn.length && contadorSecuenciaMutante < 2) {

			while (contadorSecuenciaMutante < 2 && ((adn.length - y - 1) >= (3 - igualdades)) && continuar) {
				if (adn[y].charAt(x) == adn[y + 1].charAt(x)) {
					igualdades++;
					if (igualdades == 3) {
						contadorSecuenciaMutante++;
						//System.out.println("Deteccion Vertical, Columna: "+x);
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
		return contadorSecuenciaMutante;
	}

	private static int mainDiagonalDetection(String[] adn, int contadorSecuenciaMutante) {

		int x = 0;
		int y = 0;
		final int N = adn.length;

		int limit = N - 4;

		int igualdades = 0;
		boolean continuar = true;
				
		//System.out.println("Recorriendo en y");
		for (int fila = limit; fila >= 0; fila--) {
			y = fila;
			while (contadorSecuenciaMutante < 2 && (( N - y - 1) >= (3 - igualdades)) && continuar) {
				//System.out.println("Y: " + y + " ,X: " + x + " vs " + "Y: " + (y + 1) + " ,X: " + (x + 1));
				if (adn[y].charAt(x) == adn[y + 1].charAt(x + 1)) {
					igualdades++;
					if (igualdades == 3) {
						//System.out.println("Secuencia diagonal principal 1 encontrada!!");
						contadorSecuenciaMutante++;
						continuar = false;
					}
				} else {
					igualdades = 0;
				}
				y++;
				x++;
			}
			x = 0;
			igualdades = 0;
			continuar = true;
		}
		
		//System.out.println("Contador de mutantes[Dentro de Diagonal]: " + contadorSecuenciaMutante);

		int x0 = 1;
		y = 0;

		//System.out.println("Recorriendo en x");
		for (int col = x0; col <= limit; col++) {
			x = col;
			while (contadorSecuenciaMutante < 2 && (( N - x - 1) >= (3 - igualdades)) && continuar) {
				//System.out.println("Y: " + y + " ,X: " + x + " vs " + "Y: " + (y + 1) + " ,X: " + (x + 1));
				if (adn[y].charAt(x) == adn[y + 1].charAt(x + 1)) {
					igualdades++;
					if (igualdades == 3) {
						//System.out.println("Secuencia diagonal principal 2 encontrada!!");
						contadorSecuenciaMutante++;
						continuar = false;
					}
				} else {
					igualdades = 0;
				}
				y++;
				x++;
			}
			y = 0;
			igualdades = 0;
			continuar = true;
		}
		//System.out.println("Contador de mutantes[Dentro de Diagonal despues de x]: " + contadorSecuenciaMutante);

		return contadorSecuenciaMutante;
	}
	
	
	private static int secondaryDiagonalDetection(String[] adn, int contadorSecuenciaMutante) {

		final int N = adn.length;
		int x = N - 1;
		int y = 0;

		int limit = N - 4;

		int igualdades = 0;
		boolean continuar = true;
		
		//System.out.println("Recorriendo en y");
		for (int fila = limit; fila >= 0; fila--) {
			y = fila;
			while (contadorSecuenciaMutante < 2 && (( N - y - 1) >= (3 - igualdades)) && continuar) {
				//System.out.println("Y: " + y + " ,X: " + x + " vs " + "Y: " + (y + 1) + " ,X: " + (x - 1));
				if (adn[y].charAt(x) == adn[y + 1].charAt(x - 1)) {
					igualdades++;
					if (igualdades == 3) {
						//System.out.println("Secuencia diagonal secundaria 1 encontrada!!");
						contadorSecuenciaMutante++;
						continuar = false;
					}
				} else {
					igualdades = 0;
				}
				y++;
				x--;
			}
			x = N - 1;
			igualdades = 0;
			continuar = true;
		}
		
		//System.out.println("Contador de mutantes[Dentro de Diagonal]: " + contadorSecuenciaMutante);

		int x0 = N - 2;
		y = 0;

		//System.out.println("Recorriendo en x");
		for (int col = x0; col >= 0; col--) {
			x = col;
			while (contadorSecuenciaMutante < 2 && (( x ) >= (3 - igualdades)) && continuar) {
				//System.out.println("Y: " + y + " ,X: " + x + " vs " + "Y: " + (y + 1) + " ,X: " + (x - 1));
				if (adn[y].charAt(x) == adn[y + 1].charAt(x - 1)) {
					igualdades++;
					if (igualdades == 3) {
						//System.out.println("Secuencia diagonal secundaria 2 encontrada!!");
						contadorSecuenciaMutante++;
						continuar = false;
					}
				} else {
					igualdades = 0;
				}
				y++;
				x--;
			}
			y = 0;
			igualdades = 0;
			continuar = true;
		}
		//System.out.println("Contador de mutantes[Dentro de Diagonal despues de x]: " + contadorSecuenciaMutante);

		return contadorSecuenciaMutante;
	}
	

}
