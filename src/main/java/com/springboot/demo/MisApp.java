package com.springboot.demo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class MisApp {

	@SuppressWarnings("unused")
	private final static Logger LOG = LogManager.getLogger(MisApp.class);

	public static void main(String[] args) {
		SpringApplication.run(MisApp.class, args);
	}
}
