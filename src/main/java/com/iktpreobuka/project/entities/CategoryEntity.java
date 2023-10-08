package com.iktpreobuka.project.entities;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.resource.beans.internal.FallbackBeanInstanceProducer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Views.Public.class)
	private Integer id;
	@Column(nullable = false)
	@JsonView(Views.Public.class)
	private String categoryName;
	@Column(nullable = false)
	@Size(max = 50, message = "Category description can have maximum {max} characters.")
	@JsonView(Views.Public.class)
	private String categoryDescription;
	
	@OneToMany(mappedBy = "category", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JsonView(Views.Public.class)
	private List<OfferEntity> offers = new ArrayList<OfferEntity>();
	
	public CategoryEntity() {
		super();
	}

	

	public CategoryEntity(Integer id, String categoryName, String categoryDescription, List<OfferEntity> offers) {
		super();
		this.id = id;
		this.categoryName = categoryName;
		this.categoryDescription = categoryDescription;
		this.offers = offers;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}



	public List<OfferEntity> getOffers() {
		return offers;
	}



	public void setOffers(List<OfferEntity> offers) {
		this.offers = offers;
	}
	
	
	
}
