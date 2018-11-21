package com.alkanza.model;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=true)
public class Point {
	
	@NotNull
	private Location location;
	
	@NotNull
	private Double distance;
	
	private String name;
}