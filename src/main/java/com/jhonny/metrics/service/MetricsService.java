package com.jhonny.metrics.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jhonny.metrics.entity.Metric;
import com.jhonny.metrics.repository.MetricsRepository;

@Service
public class MetricsService {
	
	private MetricsRepository metricsRepo;
	
	
	public MetricsService(MetricsRepository metricsRepo) {
		this.metricsRepo = metricsRepo;
	}
	
	/**
	 * Service to create a new Metric
	 * @param metric
	 * @return Metric saved entity
	 */
	public Metric createNewMetric(Metric metric) {
		return metricsRepo.save(metric);
	}
	
	/**
	 * Service to list saved Metric entities
	 * @return List Metric
	 */
	public List<Metric> listAllMetrics() {
		return metricsRepo.findAll();
	}
}
