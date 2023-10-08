package com.iktpreobuka.project.entities.dtoes;

import java.time.LocalDate;

import com.iktpreobuka.project.entities.OfferEntity;
import com.iktpreobuka.project.entities.UserEntity;

import jakarta.persistence.Column;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.FutureOrPresent;

public class VoucherDTO {
	@Column(nullable = false)
	@FutureOrPresent(message = "Expiration date can't be date in past")
	private LocalDate expirationDate;
	@Column(nullable = false)
	@AssertFalse
	private Boolean isUsed;
	@Column(nullable = false)
	private OfferEntity offer;
	@Column(nullable = false)
	private UserEntity user;

	public VoucherDTO() {
		super();
	}

	public VoucherDTO(@FutureOrPresent(message = "Expiration date can't be date in past") LocalDate expirationDate,
			@AssertFalse Boolean isUsed, OfferEntity offer, UserEntity user) {
		super();
		this.expirationDate = expirationDate;
		this.isUsed = isUsed;
		this.offer = offer;
		this.user = user;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
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
