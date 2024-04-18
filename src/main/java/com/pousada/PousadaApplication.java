package com.pousada;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Pousada Back-End", version = "1", description = "Aplicação back-end para o sistema de gerenciamento de uma pousada"))
public class PousadaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PousadaApplication.class, args);
	}

}
