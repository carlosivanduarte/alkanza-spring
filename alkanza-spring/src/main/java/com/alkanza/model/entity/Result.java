package com.alkanza.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=false)
@Entity
@Table(name = "tbl_result")
public class Result {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	private Double userLatitude;
	private Double userLongitude;
	private Integer radius;
	private Double calculation;
}
