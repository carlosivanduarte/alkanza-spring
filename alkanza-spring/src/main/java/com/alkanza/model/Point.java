package com.alkanza.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Point {
	
	@NotNull
	private Location location;
	
	@NotNull
	private Double distance;
	
	private String name;
}