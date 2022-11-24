package com.devduffy.gnomedepot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class GnomedepotApplication {

	public static void main(String[] args) {
		SpringApplication.run(GnomedepotApplication.class, args);
	}

}
