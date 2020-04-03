package com.product.ecommerce.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * Web Server setting each service configuration
 * 
 * @author Somendu
 * @since Apr 2, 2020
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
public class WebServer {

	public static final String CUSTOMER_SERVICE_URL = "http://customer-service";

	public static final String ADMIN_SERVICE_URL = "http://admin-service";

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "web-server");
		SpringApplication.run(WebServer.class, args);
	}

	@LoadBalanced
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public WebCustomerService customerService() {
		return new WebCustomerService(CUSTOMER_SERVICE_URL);
	}

	@Bean
	public WebAdminService subtractionService() {
		return new WebAdminService(ADMIN_SERVICE_URL);
	}

}