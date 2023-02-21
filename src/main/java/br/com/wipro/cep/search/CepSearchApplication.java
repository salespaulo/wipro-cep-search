package br.com.wipro.cep.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class CepSearchApplication {

	public static void main(String[] args) {
		healthy(SpringApplication.run(CepSearchApplication.class, args));
	}

	private static void healthy(ConfigurableApplicationContext context) {
		log.info("Running Application ID {}", context.getId());
	}
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

}
