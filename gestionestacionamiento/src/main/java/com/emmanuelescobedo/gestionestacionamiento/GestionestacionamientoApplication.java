package com.emmanuelescobedo.gestionestacionamiento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionestacionamientoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionestacionamientoApplication.class, args);
	}

}
