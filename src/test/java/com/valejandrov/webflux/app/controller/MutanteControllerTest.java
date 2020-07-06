package com.valejandrov.webflux.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = MutanteController.class)
public class MutanteControllerTest {
	
	@Autowired
	private WebApplicationContext appContext;
	
    @Autowired
    private WebTestClient webClient;
	
	//private MockMvc mockController;
	
	/*
	@Before
	public void setup() {
		this.mockController = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}

	
	@Test
	public void contextLoads() {
	}
	*/

	// Test without Mutant sequences

	@Test
	public void testForbidden1Mutante() throws Exception {

		String request = "{\n"+ 
				"\"dna\": " +
				"        [\"ACCT\",\n" + 
				"         \"CTCA\",\n" + 
				"         \"ACGC\",\n" + 
				"         \"ACCT\",\n" + 
				"        ]\n" +
				"}"; 
		/*
		mockController.perform(
				post("/mutante/")
					.contentType(MediaType.ALL_VALUE)
					.content(request.getBytes()))
				.andExpect(status().isForbidden())
			;
			*/
		
        webClient.post()
        	.uri("/mutante")
        	.bodyValue(BodyInserters.)
        	.expectStatus().isOk();
		
	}
	
}
