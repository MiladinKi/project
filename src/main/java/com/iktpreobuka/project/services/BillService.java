package com.iktpreobuka.project.services;

import java.time.LocalDate;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;

public interface BillService {
	public Iterable<BillEntity> findBiilsByDateRange(LocalDate startDate, LocalDate endDate);
	public boolean hasActiveBillsInCategory(Integer categoryId);
	public void cancelBillsByOffer(OfferEntity offer);
}
