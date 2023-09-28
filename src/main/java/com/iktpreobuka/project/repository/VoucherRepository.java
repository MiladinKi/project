package com.iktpreobuka.project.repository;

import java.time.LocalDate;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.project.entities.VoucherEntity;

public interface VoucherRepository extends CrudRepository<VoucherEntity, Integer> {
	public Iterable<VoucherEntity> findByUserId(Integer userId);
	public Iterable<VoucherEntity> findByOffer_Id(Integer offerId);
	public Iterable<VoucherEntity> findByExpirationDateGreaterThanEqual(LocalDate date);
}
