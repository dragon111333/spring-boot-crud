package com.testsdemo.testcrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.SpringVersion;

@SpringBootApplication
@ComponentScan(basePackages = "com.testsdemo.testcrud")
public class TestcrudApplication {

	public static void main(String[] args) {
		printSpringVersion();
		SpringApplication.run(TestcrudApplication.class, args);
	}

	private static void printSpringVersion(){
		System.out.println("version: " + SpringVersion.getVersion());
	}

}
