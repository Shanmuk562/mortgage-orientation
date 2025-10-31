package com.bank.mortgage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MortgageOrientationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MortgageOrientationApplication.class, args);
	}

}
