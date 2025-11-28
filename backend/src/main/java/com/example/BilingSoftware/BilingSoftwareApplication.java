package com.example.BilingSoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.BilingSoftware.entity")
public class BilingSoftwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(BilingSoftwareApplication.class, args);
	}

}
