package com.example.loanApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LoanAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanAppApplication.class, args);
	}

}
