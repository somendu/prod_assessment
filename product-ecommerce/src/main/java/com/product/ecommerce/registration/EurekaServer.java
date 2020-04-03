package com.product.ecommerce.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 
 * Registering Eureka Server
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {
	public static void main(String[] args) {
		System.setProperty("spring.config.name", "eureka-server");

		SpringApplication.run(EurekaServer.class, args);
	}
}
