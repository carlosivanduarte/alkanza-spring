package com.alkanza.model.rest;

import lombok.Data;

@Data
public class Place {
	
	private Location location;
	private Double distance;
	private String name;
}