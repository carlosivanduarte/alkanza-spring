package com.alkanza.model.db;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_result")
public class Result {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	private Date created;
	private String name;
	private Double latitude;
	private Double longitude;
	private Integer radius;
	private Double calculation;
}
