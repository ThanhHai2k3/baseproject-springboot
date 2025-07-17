package com.example.projectbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProjectbaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectbaseApplication.class, args);
	}

}
