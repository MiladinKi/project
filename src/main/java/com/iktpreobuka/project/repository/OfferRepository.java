package com.iktpreobuka.project.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferRepository extends CrudRepository<OfferEntity, Integer> {
	public Iterable<OfferEntity> findAllByActionPriceBetween(Double lower, Double upper);
}
