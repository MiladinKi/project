package com.iktpreobuka.project.entities;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.iktpreobuka.project.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BillEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private Boolean paymentMade;
	@Column(nullable = false)
	@JsonView(Views.Admin.class)
	private Boolean paymentCanceled;
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,
	pattern = "dd-MM-yyyy")
	@JsonView(Views.Public.class)
	private LocalDate billCreated;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "offer")
	@JsonView(Views.Private.class)
	@JsonBackReference
	private OfferEntity offer;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "user")
	@JsonView(Views.Private.class)
	@JsonBackReference
	private UserEntity user;

	public BillEntity() {
		super();
	}

	public BillEntity(Integer id, Boolean paymentMade, Boolean paymentCanceled, LocalDate billCreated, OfferEntity offer,
			UserEntity user) {
		super();
		this.id = id;
		this.paymentMade = paymentMade;
		this.paymentCanceled = paymentCanceled;
		this.billCreated = billCreated;
		this.offer = offer;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
