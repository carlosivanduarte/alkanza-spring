package com.alkanza.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ProcessResult {
	
	private LocalDateTime createDateTime;	
	private Double userLatitude;
	private Double userLongitude;
	private Integer radius;
	private Double calculation;
}
