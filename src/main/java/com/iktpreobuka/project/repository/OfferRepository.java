package com.iktpreobuka.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.OfferEntity;

public interface OfferRepository extends CrudRepository<OfferEntity, Integer> {
	public Iterable<OfferEntity> findAllByActionPriceBetween(Double lower, Double upper);
	public Iterable<OfferEntity> findAllByCategory_IdAndOfferExpiresAfter(Integer categoryId, LocalDate currentDate);
}
