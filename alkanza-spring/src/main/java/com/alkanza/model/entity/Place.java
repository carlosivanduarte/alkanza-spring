package com.alkanza.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder=false)
@Entity
@Table(name = "tbl_place")
public class Place {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	private String name;	
	private Double latitude;
	private Double longitude;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private Result result;

}
