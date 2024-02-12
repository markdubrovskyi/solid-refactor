package com.example.solidtesting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SolidTestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolidTestingApplication.class, args);
	}

}
