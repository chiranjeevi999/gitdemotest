package com.cg.RestfulService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * When beans package is different
 * 
 * @SpringBootApplication(scanBasePackages="user")
 * 
 */
@SpringBootApplication
public class RestfulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulServiceApplication.class, args);
	}

}
