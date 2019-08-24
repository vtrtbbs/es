package com.badilong.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class BadilongSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(BadilongSearchApplication.class, args);
	}

}
