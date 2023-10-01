package com.jhonny.metrics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jhonny.metrics.entity.Metric;

public interface MetricsRepository extends JpaRepository<Metric, Integer> {
	
	@SuppressWarnings("unchecked")
	Metric save(Metric metric);
	List<Metric> findAll();
}
