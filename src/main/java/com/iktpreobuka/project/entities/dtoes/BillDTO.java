package com.iktpreobuka.project.entities.dtoes;

import java.time.LocalDate;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class BillDTO {

	@AssertFalse
	@NotNull(message = "Polje paymentMade ne sme biti null.")
	private Boolean paymentMade = false;

	@NotNull(message = "Polje paymentCanceled ne sme biti null.")
	@AssertFalse
	private Boolean paymentCanceled;

	@PastOrPresent(message = "Datum billCreated ne sme biti u budućnosti.")
	private LocalDate billCreated;

	private OfferEntity offer;
	private UserEntity user;

	public BillDTO() {
		super();
	}

	public BillDTO(@AssertFalse @NotNull(message = "Polje paymentMade ne sme biti null.") Boolean paymentMade,
			@NotNull(message = "Polje paymentCanceled ne sme biti null.") @AssertFalse Boolean paymentCanceled,
			@FutureOrPresent(message = "Datum billCreated ne sme biti u budućnosti.") LocalDate billCreated,
			OfferEntity offer, UserEntity user) {
		super();
		this.paymentMade = paymentMade;
		this.paymentCanceled = paymentCanceled;
		this.billCreated = billCreated;
		this.offer = offer;
		this.user = user;
	}

	public Boolean getPaymentMade() {
		return paymentMade;
	}

	public void setPaymentMade(Boolean paymentMade) {
		this.paymentMade = paymentMade;
	}

	public Boolean getPaymentCanceled() {
		return paymentCanceled;
	}

	public void setPaymentCanceled(Boolean paymentCanceled) {
		this.paymentCanceled = paymentCanceled;
	}

	public LocalDate getBillCreated() {
		return billCreated;
	}

	public void setBillCreated(LocalDate billCreated) {
		this.billCreated = billCreated;
	}

	public OfferEntity getOffer() {
		return offer;
	}

	public void setOffer(OfferEntity offer) {
		this.offer = offer;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
