package com.prodera.CarRegistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "com.prodera.CarRegistry")
public class CarRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarRegistryApplication.class, args);
	}
}
