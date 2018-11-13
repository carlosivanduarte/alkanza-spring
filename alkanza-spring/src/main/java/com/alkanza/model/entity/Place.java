package com.alkanza.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder(toBuilder = true)
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "tbl_place")
public class Place {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	private String name;	
	private Double latitude;
	private Double longitude;
	private Double distance;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Result result;
	
	public Place(){}	
}
