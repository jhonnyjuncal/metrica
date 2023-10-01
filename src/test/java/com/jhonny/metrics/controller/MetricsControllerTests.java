package com.jhonny.metrics.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import com.jhonny.metrics.entity.Metric;
import com.jhonny.metrics.mapper.MetricMapper;
import com.jhonny.metrics.payload.MetricPayload;
import com.jhonny.metrics.service.MetricsService;

@SpringBootTest
public class MetricsControllerTests {
	
	@Mock
	private MetricsService metricsService;
	
	
	@Test
	public void saveMetricUnitTest() {
		MetricPayload metricPayload = createEntityUnSavedMetricPayload1();
		Metric metric = MetricMapper.INSTANCE.metricPayloadToMetric(metricPayload);
		Mockito.when(metricsService.createNewMetric(metric)).thenReturn(createMetricSaved1());
		MetricsController metricController = new MetricsController(metricsService);
		
		ResponseEntity<MetricPayload> savedMetric = metricController.saveMetric(metricPayload);
		
		Assert.notNull(savedMetric, "Obeject RepsonseEntity has not be null");
		Assert.notNull(savedMetric.getBody(), "Object MetricPayload has not be null");
		Assert.isTrue(savedMetric.getBody().getId().equals(1), "Identifier for new entity is 1");
	}
	
	@Test
	public void listMetricsUnitTest() {
		Mockito.when(metricsService.listAllMetrics()).thenReturn(createMetricPayloadSavedList());
		MetricsController metricController = new MetricsController(metricsService);
		
		ResponseEntity<List<MetricPayload>> listMetrics = metricController.listMetrics();
		
		Assert.notNull(listMetrics, "Obeject RepsonseEntity has not be null");
		Assert.notNull(listMetrics.getBody(), "Object MetricPayload has not be null");
		Assert.notEmpty(listMetrics.getBody(), "List of Metrics cannot be empty");
		Assert.isTrue(listMetrics.getBody().get(0).getId().equals(1), "Identifier for first entity in list must be '1'");
		Assert.isTrue(listMetrics.getBody().get(1).getId().equals(2), "Identifier for second entity in list must be '2'");
	}
	
	
	private MetricPayload createEntityUnSavedMetricPayload1() {
		MetricPayload metricPayload = new MetricPayload();
		metricPayload.setName("Pants");
		metricPayload.setSalesUnit(38);
		metricPayload.setStock("S:10 / M:20 / L:30");
		
		return metricPayload;
	}
	
	private Metric createMetricSaved1() {
		Metric metric = new Metric();
		metric.setId(1);
		metric.setName("Pants");
		metric.setSalesUnit(38);
		metric.setStock("S:10 / M:20 / L:30");
		
		return metric;
	}
	
	private List<Metric> createMetricPayloadSavedList() {
		List<Metric> resultMetricList = new ArrayList<>();
		
		Metric metric1 = new Metric();
		metric1.setId(1);
		metric1.setName("Pants");
		metric1.setSalesUnit(38);
		metric1.setStock("S:10 / M:20 / L:30");
		resultMetricList.add(metric1);
		
		Metric metric2 = new Metric();
		metric2.setId(2);
		metric2.setName("T-shirt");
		metric2.setSalesUnit(100);
		metric2.setStock("S:2 / M:4 / L:0");
		resultMetricList.add(metric2);
		
		return resultMetricList;
	}
}
