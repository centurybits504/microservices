package com.company.division;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;



@SpringBootApplication
@EnableEurekaClient
public class DivisionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DivisionServiceApplication.class, args);
	}
}
