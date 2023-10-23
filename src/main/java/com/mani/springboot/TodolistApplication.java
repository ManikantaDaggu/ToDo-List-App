package com.mani.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
public class TodolistApplication  extends SpringServletContainerInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}

}
