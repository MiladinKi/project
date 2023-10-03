package com.iktpreobuka.project.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.project.entities.EUserRole;
import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;
import com.iktpreobuka.project.entities.VoucherEntity;
import com.iktpreobuka.project.repository.OfferRepository;
import com.iktpreobuka.project.repository.UserRepository;
import com.iktpreobuka.project.repository.VoucherRepository;
import com.iktpreobuka.project.services.EmailService;

@RestController
@RequestMapping(path = "/api/v1/vouchers")
public class VoucherController {

	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<VoucherEntity> getAllVouchers(){
		return voucherRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/{offerId}/buyer/{buyerId}")
	public VoucherEntity addVoucher(@RequestParam LocalDate expirationDate, @RequestParam Boolean isUsed,
			@PathVariable Integer offerId, @PathVariable Integer buyerId) {
		VoucherEntity newVoucher = new VoucherEntity();
		OfferEntity offer = offerRepository.findById(offerId).get();
		UserEntity buyer = userRepository.findById(buyerId).get();
		if(buyer.getUserRole() == EUserRole.ROLE_CUSTOMER) {
			newVoucher.setExpirationDate(expirationDate);
			newVoucher.setIsUsed(isUsed);
			newVoucher.setOffer(offer);
			newVoucher.setUser(buyer);
			voucherRepository.save(newVoucher);
			emailService.sendVoucherEmail(buyer.getEmail(), newVoucher);
			return newVoucher;
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public VoucherEntity changeVoucher(@RequestBody VoucherEntity updatedVoucher, 
			@PathVariable Integer id) {
		VoucherEntity voucher = voucherRepository.findById(id).get();
		if(updatedVoucher.getExpirationDate() != null) {
			voucher.setExpirationDate(updatedVoucher.getExpirationDate());
		}
		if(updatedVoucher.getIsUsed() != null) {
			voucher.setIsUsed(updatedVoucher.getIsUsed());
		}
		if(updatedVoucher.getOffer() != null) {
			voucher.setOffer(updatedVoucher.getOffer());
		}
		if(updatedVoucher.getUser() != null) {
			voucher.setUser(updatedVoucher.getUser());
		}
		voucherRepository.save(voucher);
		return voucher;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteVoucher(@PathVariable Integer id) {
		voucherRepository.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByBuyer/{buyerId}")
	public Iterable<VoucherEntity> findByBuyer(@PathVariable Integer buyerId){
		return voucherRepository.findByUserId(buyerId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findByOffer/{offerId}")
	public Iterable<VoucherEntity> getVouchersByOffer(@PathVariable Integer offerId) {
	    return voucherRepository.findByOffer_Id(offerId);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/findNonExpiredVoucher")
	public Iterable<VoucherEntity> findNonExpiredVoucher(){
		return voucherRepository.findByExpirationDateGreaterThanEqual(LocalDate.now());
	}

}
