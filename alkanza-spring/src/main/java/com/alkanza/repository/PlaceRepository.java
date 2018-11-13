package com.alkanza.repository;

import org.springframework.data.repository.CrudRepository;

import com.alkanza.model.entity.Place;

public interface PlaceRepository extends CrudRepository<Place, Long> {

}