package com.valejandrov.webflux.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MercadolibreWebfluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(MercadolibreWebfluxApplication.class, args);
		System.out.println("Thread Count: "+ Thread.activeCount());
	}

}
