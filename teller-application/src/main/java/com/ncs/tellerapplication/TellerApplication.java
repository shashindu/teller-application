package com.ncs.tellerapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TellerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TellerApplication.class, args);
	}

}
