package com.iktpreobuka.project.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;

public interface BillRepository extends CrudRepository<BillEntity, Integer> {
	 Iterable<BillEntity> findByUserId(Integer buyerId);
	 public Iterable<BillEntity> findByOffer_CategoryId(Integer categoryId);
	 public Iterable<BillEntity> findByBillCreatedBetween(LocalDate startDate, LocalDate endDate);
	 public Iterable<BillEntity> findByOffer_Category_IdAndPaymentCanceledFalse(Integer categoryId);
	 Iterable<BillEntity> findByOffer(OfferEntity offer);
}
