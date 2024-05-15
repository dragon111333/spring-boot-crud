package com.testsdemo.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.testsdemo.testcrud")
public class TestcrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestcrudApplication.class, args);
	}

}
