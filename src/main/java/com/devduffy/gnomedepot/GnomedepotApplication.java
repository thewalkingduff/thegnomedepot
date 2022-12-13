package com.devduffy.gnomedepot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.devduffy.gnomedepot.repository.ProductRepository;
import com.devduffy.gnomedepot.service.ProductService;

@SpringBootApplication()
public class GnomedepotApplication {

	public static void main(String[] args) {

		SpringApplication.run(GnomedepotApplication.class, args);
	}

}
