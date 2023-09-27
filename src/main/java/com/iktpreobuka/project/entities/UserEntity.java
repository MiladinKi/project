package com.iktpreobuka.project.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitalizer", "hendler" })
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private EUserRole userRole;

	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<OfferEntity> offers = new ArrayList<OfferEntity>();

	@OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<BillEntity> bills = new ArrayList<>();

	public UserEntity() {
		super();
	}

	public UserEntity(Integer id, String firstName, String lastName, String username, String password, String email,
			EUserRole userRole, List<OfferEntity> offers, List<BillEntity> bills) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
		this.offers = offers;
		this.bills = bills;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EUserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(EUserRole userRole) {
		this.userRole = userRole;
	}

	public List<OfferEntity> getOffers() {
		return offers;
	}

	public void setOffers(List<OfferEntity> offers) {
		this.offers = offers;
	}

	public List<BillEntity> getBills() {
		return bills;
	}

	public void setBills(List<BillEntity> bills) {
		this.bills = bills;
	}

}
