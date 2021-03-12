package com.pixeltrice.springbootimagegalleryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootMediaGalleryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMediaGalleryAppApplication.class, args);
	}

}
