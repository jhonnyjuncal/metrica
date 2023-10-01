package com.jhonny.metrics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.jhonny.metrics.repository")
public class MetricaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetricaApplication.class, args);
	}
	
}
