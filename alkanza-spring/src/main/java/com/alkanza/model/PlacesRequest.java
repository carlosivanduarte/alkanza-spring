package com.alkanza.model;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PlacesRequest {
	
	@NotNull
	private List<Point> points;
	
	@NotNull
	private Integer radius;
	
	@NotNull
	private String status;
	
	@NotNull
	@JsonProperty("user_location")
	private Location userLocation;
}
