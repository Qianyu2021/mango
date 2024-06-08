package com.Mangos.MangosBlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Home {

	public static void main(String[] args) {

		SpringApplication.run(Home.class, args);
	}

}


