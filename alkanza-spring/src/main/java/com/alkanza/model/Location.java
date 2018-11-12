package com.alkanza.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Location {
	
	@NotNull
	private Double lat;
	
	@NotNull
	private Double lng;
}
