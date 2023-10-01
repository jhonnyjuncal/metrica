package com.jhonny.metrics.payload;

import java.io.Serializable;

import lombok.Data;

@Data
public class MetricPayload implements Serializable {
	
	private static final long serialVersionUID = 5901399054705182764L;
	
	private Integer id;
	private String name;
	private Integer salesUnit;
	private String stock;
}
