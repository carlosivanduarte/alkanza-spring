package com.alkanza.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder=false)
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tbl_result")
public class Result {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	private Double userLatitude;
	private Double userLongitude;
	private Integer radius;
	private Double calculation;
	
	public Result() {
		
	}
	
}
