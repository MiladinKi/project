package com.iktpreobuka.project.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.repository.BillRepository;
import com.iktpreobuka.project.repository.OfferRepository;
import com.iktpreobuka.project.repository.UserRepository;
import com.iktpreobuka.project.services.BillService;
import com.iktpreobuka.project.services.OfferService;

@RestController
@RequestMapping(path = "/api/v1/bills")
public class BillController {

	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OfferService offerService;
	
//	@Autowired
//	private BillService billService;
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<BillEntity> getAllBills(){
		return billRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{offerId}/buyer/{buyerId}")
	public BillEntity addBill(@PathVariable Integer offerId,
			@PathVariable Integer buyerId) {
		BillEntity bill = new BillEntity();
		OfferEntity offer = offerRepository.findById(offerId).get();
		UserEntity user = userRepository.findById(buyerId).get();
		bill.setBillCreated(LocalDate.now());
		bill.setPaymentMade(false);
		bill.setPaymentCanceled(false);
		bill.setOffer(offer);
		offerService.updateAvailableBoughtOffer(offerId, 1);
		bill.setUser(user);
		billRepository.save(bill);
		return bill;
		
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public BillEntity changeBill(@RequestBody BillEntity updatedBill, @PathVariable Integer id) {
		BillEntity bill = billRepository.findById(id).get();
		if(updatedBill.getPaymentMade() != null) {
			bill.setPaymentMade(updatedBill.getPaymentMade());
		}
		if(updatedBill.getPaymentCanceled() !=  null) {
			bill.setPaymentCanceled(updatedBill.getPaymentCanceled());
		}
		if(updatedBill.getBillCreated() != null) {
			bill.setBillCreated(updatedBill.getBillCreated());
		}
		if(updatedBill.getOffer() != null) {
			bill.setOffer(updatedBill.getOffer());
		}
		if(updatedBill.getUser() != null) {
			bill.setUser(updatedBill.getUser());
		}
		billRepository.save(bill);
		return bill;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteBill(@PathVariable Integer id) {
		billRepository.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByBuyer/{buyerId}")
	public Iterable<BillEntity> getAllByBuyer(@PathVariable Integer buyerId) {
	    return billRepository.findByUserId(buyerId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByCategory/{categoryId}")
	public Iterable<BillEntity> getAllByCategory(@PathVariable Integer categoryId) {
	    return billRepository.findByOffer_CategoryId(categoryId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByDate/{startDate}/and/{endDate}")
	public Iterable<BillEntity> getAllByCategory(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
	    return billRepository.findByBillCreatedBetween(startDate, endDate);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{billId}/cancel")
	public BillEntity canceledBill(@PathVariable Integer billId) {
		BillEntity bill = billRepository.findById(billId).get();
		bill.setPaymentCanceled(true);
		offerService.cancelOffer(bill.getOffer().getId());
		billRepository.save(bill);
		return bill;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByDateRange/{startDate}/and/{endDate}")
	public Iterable<BillEntity> getBillsByDateRange(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
		return billService.findBiilsByDateRange(startDate, endDate);
		
	}
	
}
