package com.carlos.costura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class CosturaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CosturaApplication.class, args);
	}

}
