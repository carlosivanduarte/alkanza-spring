package com.alkanza.model.rest;

import java.util.List;

import lombok.Data;

@Data
public class PlacesRequest {
	
	private List<Place> places;
	private Integer radius;
	private String status;
}
