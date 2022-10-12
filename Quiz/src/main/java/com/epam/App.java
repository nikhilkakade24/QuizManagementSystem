package com.epam;

import java.util.Scanner;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class App {

	private static ApplicationContext applicationContext;
	private static Logger logger = LogManager.getLogger(App.class);
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		applicationContext = SpringApplication.run(App.class, args);

		logger.log(Level.INFO, "****Welcome to quiz management system****");
		logger.log(Level.INFO, "\nLogin As: 1. Admin \t\t\t\t  2.User");

		displayAllBeans();

	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public WebClient.Builder getWebClientBulider() {
		return WebClient.builder();
	}

	public static void displayAllBeans() {
		String[] allBeanNames = applicationContext.getBeanDefinitionNames();
		logger.log(Level.INFO, "\n****Displaying all beans****");
		for (String beanName : allBeanNames) {

			logger.log(Level.INFO, beanName);

		}
	}
}
