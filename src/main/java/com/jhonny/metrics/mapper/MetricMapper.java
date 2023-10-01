package com.jhonny.metrics.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.jhonny.metrics.entity.Metric;
import com.jhonny.metrics.payload.MetricPayload;

@Mapper
public interface MetricMapper {
	
	MetricMapper INSTANCE = Mappers.getMapper(MetricMapper.class);
	
	MetricPayload metricToMetricPayload(Metric metric);
	Metric metricPayloadToMetric(MetricPayload metricPayload);
}
