package com.valejandrov.webflux.app;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MercadolibreWebfluxApplication {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public static void main(String[] args) {
		SpringApplication.run(MercadolibreWebfluxApplication.class, args);
		System.out.println("Thread Count: "+ Thread.activeCount());
	}
	
	@PostConstruct
    public void init() {
        log.info("CPU: {}", Runtime.getRuntime().availableProcessors());
    }

}
