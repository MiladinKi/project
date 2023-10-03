package com.iktpreobuka.project.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.VoucherEntity;
import com.iktpreobuka.project.repository.VoucherRepository;

@Service
public class VoucherServiseImpl implements VoucherService {

	@Autowired
	private VoucherRepository voucherRepository;
	@Autowired
    private EmailService emailService;
	@Override
	public VoucherEntity createVoucherFromBill(BillEntity bill) {
		   VoucherEntity voucher = new VoucherEntity();
	        

	        voucher.setExpirationDate(LocalDate.now().plusDays(30)); // Na primer, vaučer ističe za 30 dana
	        voucher.setIsUsed(false); // Postavite status vaučera na nepotrošen
	        voucher.setOffer(bill.getOffer());
	        voucher.setUser(bill.getUser());

	        voucherRepository.save(voucher);
	        emailService.sendVoucherEmail(bill.getUser(), voucher);
	        return voucher;
	}

}
