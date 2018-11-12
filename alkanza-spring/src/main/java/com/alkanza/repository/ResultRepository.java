package com.alkanza.repository;

import org.springframework.data.repository.CrudRepository;

import com.alkanza.model.db.Result;

public interface ResultRepository extends CrudRepository<Result, Long> {

}