package br.com.diobank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@SpringBootApplication

public class DioBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(DioBankApplication.class, args);
	}

}
