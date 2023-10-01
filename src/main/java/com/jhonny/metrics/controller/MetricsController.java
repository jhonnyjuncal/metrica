package com.jhonny.metrics.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jhonny.metrics.entity.Metric;
import com.jhonny.metrics.mapper.MetricMapper;
import com.jhonny.metrics.payload.MetricPayload;
import com.jhonny.metrics.service.MetricsService;

@RequestMapping("/metrics/")
@RestController
public class MetricsController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	private MetricsService metricsService;
	
	
	public MetricsController(MetricsService metricsService) {
		this.metricsService = metricsService;
	}
	
	
	@PostMapping("v1/add")
	public ResponseEntity<MetricPayload> saveMetric(@RequestBody MetricPayload payload) {
		MetricPayload metricResult = null;
		
		try {
			Metric metricOut = metricsService.createNewMetric(MetricMapper.INSTANCE.metricPayloadToMetric(payload));
			log.info("saved new Metric");
			
			metricResult = MetricMapper.INSTANCE.metricToMetricPayload(metricOut);
			
		} catch (Exception ex) {
			log.error("Error saving a new entity");
			log.error(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(metricResult);
	}
	
	
	@GetMapping("v1/list")
	public ResponseEntity<List<MetricPayload>> listMetrics() {
		List<MetricPayload> metricResultList = null;
		
		try {
			List<Metric> listAllMetrics = metricsService.listAllMetrics();
			log.info("Getting metrics list");
			
			metricResultList = listAllMetrics.stream().map(metric -> 
				MetricMapper.INSTANCE.metricToMetricPayload(metric))
				.collect(Collectors.toList());
			
		} catch (Exception ex) {
			log.error("Error getting metrics list");
			log.error(ex.getMessage());
			return ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.ok(metricResultList);
	}
}
