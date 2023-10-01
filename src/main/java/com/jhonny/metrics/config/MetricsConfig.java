package com.jhonny.metrics.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jhonny.metrics.repository.MetricsRepository;
import com.jhonny.metrics.service.MetricsService;

@Configuration
public class MetricsConfig {
	
	@Autowired
	MetricsRepository metricsRepository;
	
	@Bean
	MetricsService metricsService() {
		return new MetricsService(metricsRepository);
	}
	
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server inMemoryH2DatabaseaServer() throws SQLException {
	    return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
	}
}
