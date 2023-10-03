package com.iktpreobuka.project.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.repository.BillRepository;

@Service
public class BillServiceImpl implements BillService {

//	@Autowired
//	private BillRepository billRepository;
	private final BillRepository billRepository;

	@Autowired
	public BillServiceImpl(BillRepository billRepository) {
		this.billRepository = billRepository;
	}

	@Autowired
	private BillService billService;

	@Override
	public Iterable<BillEntity> findBiilsByDateRange(LocalDate startDate, LocalDate endDate) {

		return billRepository.findByBillCreatedBetween(startDate, endDate);
	}

	@Override
	public boolean hasActiveBillsInCategory(Integer categoryId) {
		LocalDate currentDate = LocalDate.now();
		Iterable<BillEntity> activeBills = billRepository.findByOffer_Category_IdAndPaymentCanceledFalse(categoryId);
		return activeBills.iterator().hasNext();

	}

	@Override
	public void cancelBillsByOffer(OfferEntity offer) {
		List<BillEntity> bills = (List<BillEntity>) billRepository.findByOffer(offer);
		for (BillEntity bill : bills) {
			bill.setPaymentCanceled(true);
		}

		billRepository.saveAll(bills);
	}

}
