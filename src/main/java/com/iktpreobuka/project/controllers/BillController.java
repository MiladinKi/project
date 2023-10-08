package com.iktpreobuka.project.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.entities.BillEntity;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.dtoes.BillDTO;
import com.iktpreobuka.project.repository.BillRepository;
import com.iktpreobuka.project.repository.OfferRepository;
import com.iktpreobuka.project.repository.UserRepository;
import com.iktpreobuka.project.security.Views;
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
	
	@Autowired
//	private BillService billService;
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/public")
//	@JsonView(Views.Public.class)
//	public Iterable<BillEntity> getAllBillsPublic(){
//		return billRepository.findAll();
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value ="/private")
//	@JsonView(Views.Private.class)
//	public Iterable<BillEntity> getAllBillsPrivate(){
//		return billRepository.findAll();
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/admin")
//	@JsonView(Views.Admin.class)
//	public Iterable<BillEntity> getAllBillsAdmin(){
//		return billRepository.findAll();
//	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/{offerId}/buyer/{buyerId}")
//	public BillEntity addBill(@PathVariable Integer offerId,
//			@PathVariable Integer buyerId, @RequestBody BillDTO billDTO) {
//		BillEntity bill = new BillEntity();
//		OfferEntity offer = offerRepository.findById(offerId).get();
//		UserEntity user = userRepository.findById(buyerId).get();
//		bill.setBillCreated(LocalDate.now());
//		bill.setPaymentMade(billDTO.getPaymentMade());
//        bill.setPaymentCanceled(billDTO.getPaymentCanceled());
//		bill.setOffer(offer);
//		offerService.updateAvailableBoughtOffer(offerId, 1);
//		bill.setUser(user);
//		billRepository.save(bill);
//		return bill;
//		
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
//	public BillEntity changeBill(@RequestBody BillDTO updatedBillDTO, @PathVariable Integer id) {
//		BillEntity bill = billRepository.findById(id).get();
//		if(updatedBillDTO.getPaymentMade() != null) {
//			bill.setPaymentMade(updatedBillDTO.getPaymentMade());
//		}
//		if(updatedBillDTO.getPaymentCanceled() !=  null) {
//			bill.setPaymentCanceled(updatedBillDTO.getPaymentCanceled());
//		}
//		if(updatedBillDTO.getBillCreated() != null) {
//			bill.setBillCreated(updatedBillDTO.getBillCreated());
//		}
//		if(updatedBillDTO.getOffer() != null) {
//			bill.setOffer(updatedBillDTO.getOffer());
//		}
//		if(updatedBillDTO.getUser() != null) {
//			bill.setUser(updatedBillDTO.getUser());
//		}
//		billRepository.save(bill);
//		return bill;
//	}
//	
//	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//	public void deleteBill(@PathVariable Integer id) {
//		billRepository.deleteById(id);
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/findByBuyer/{buyerId}")
//	public Iterable<BillEntity> getAllByBuyer(@PathVariable Integer buyerId) {
//	    return billRepository.findByUserId(buyerId);
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/findByCategory/{categoryId}")
//	public Iterable<BillEntity> getAllByCategory(@PathVariable Integer categoryId) {
//	    return billRepository.findByOffer_CategoryId(categoryId);
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/findByDate/{startDate}/and/{endDate}")
//	public Iterable<BillEntity> getAllByCategory(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate) {
//	    return billRepository.findByBillCreatedBetween(startDate, endDate);
//	}
//	
//	@RequestMapping(method = RequestMethod.PUT, value = "/{billId}/cancel")
//	public BillEntity canceledBill(@PathVariable Integer billId) {
//		BillEntity bill = billRepository.findById(billId).get();
//		bill.setPaymentCanceled(true);
//		offerService.cancelOffer(bill.getOffer().getId());
//		billRepository.save(bill);
//		return bill;
//	}
//	
//	@RequestMapping(method = RequestMethod.GET, value = "/findByDateRange/{startDate}/and/{endDate}")
//	public Iterable<BillEntity> getBillsByDateRange(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//			@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate){
//		return billService.findBiilsByDateRange(startDate, endDate);
//		
//	}
    
	
	@RequestMapping(method = RequestMethod.GET, value = "/public")
	@JsonView(Views.Public.class)
	public ResponseEntity<Iterable<BillEntity>> getAllBillsPublic(){
		Iterable<BillEntity> allBills = billRepository.findAll();
		return new ResponseEntity<>(allBills, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value ="/private")
	@JsonView(Views.Private.class)
	public ResponseEntity<Iterable<BillEntity>> getAllBillsPrivate(){
		Iterable<BillEntity> allBills = billRepository.findAll();
		return new ResponseEntity<>(allBills, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/admin")
	@JsonView(Views.Admin.class)
	public ResponseEntity<Iterable<BillEntity>> getAllBillsAdmin(){
		Iterable<BillEntity> allBills = billRepository.findAll();
		return new ResponseEntity<>(allBills, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{offerId}/buyer/{buyerId}")
	public ResponseEntity<BillEntity> addBill(@PathVariable Integer offerId,
			@PathVariable Integer buyerId, @RequestBody BillDTO billDTO) {
		try {
		BillEntity bill = new BillEntity();
		OfferEntity offer = offerRepository.findById(offerId).get();
		UserEntity user = userRepository.findById(buyerId).get();
		bill.setBillCreated(LocalDate.now());
		bill.setPaymentMade(billDTO.getPaymentMade());
        bill.setPaymentCanceled(billDTO.getPaymentCanceled());
		bill.setOffer(offer);
		offerService.updateAvailableBoughtOffer(offerId, 1);
		bill.setUser(user);
		billRepository.save(bill);
		return new ResponseEntity<>(billRepository.save(bill), HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public BillEntity changeBill(@RequestBody BillDTO updatedBillDTO, @PathVariable Integer id) {
		BillEntity bill = billRepository.findById(id).get();
		if(updatedBillDTO.getPaymentMade() != null) {
			bill.setPaymentMade(updatedBillDTO.getPaymentMade());
		}
		if(updatedBillDTO.getPaymentCanceled() !=  null) {
			bill.setPaymentCanceled(updatedBillDTO.getPaymentCanceled());
		}
		if(updatedBillDTO.getBillCreated() != null) {
			bill.setBillCreated(updatedBillDTO.getBillCreated());
		}
		if(updatedBillDTO.getOffer() != null) {
			bill.setOffer(updatedBillDTO.getOffer());
		}
		if(updatedBillDTO.getUser() != null) {
			bill.setUser(updatedBillDTO.getUser());
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
