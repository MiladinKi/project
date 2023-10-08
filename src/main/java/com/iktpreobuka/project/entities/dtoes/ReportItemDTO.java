package com.iktpreobuka.project.entities.dtoes;

import java.time.LocalDate;

public class ReportItemDTO {
	private LocalDate date;
	private Double income;
	private Integer numberOfOffers;

	public ReportItemDTO() {
		super();
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public Integer getNumberOfOffers() {
		return numberOfOffers;
	}

	public void setNumberOfOffers(Integer numberOfOffers) {
		this.numberOfOffers = numberOfOffers;
	}

}
