package com.jawahar.cityconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.jawahar.cityconnect")
public class CityConnectFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityConnectFinderApplication.class, args);
	}

}
