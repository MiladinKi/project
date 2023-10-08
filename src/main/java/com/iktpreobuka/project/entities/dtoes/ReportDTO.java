package com.iktpreobuka.project.entities.dtoes;

import java.util.List;

public class ReportDTO {
	private String categoryName;
	private List<ReportItemDTO> listOfReportItems;
	private Double sumOfIncomes;
	private Integer totalNumberOfSoldOffers;

	public ReportDTO() {
		super();
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ReportItemDTO> getListOfReportItems() {
		return listOfReportItems;
	}

	public void setListOfReportItems(List<ReportItemDTO> listOfReportItems) {
		this.listOfReportItems = listOfReportItems;
	}

	public Double getSumOfIncomes() {
		return sumOfIncomes;
	}

	public void setSumOfIncomes(Double sumOfIncomes) {
		this.sumOfIncomes = sumOfIncomes;
	}

	public Integer getTotalNumberOfSoldOffers() {
		return totalNumberOfSoldOffers;
	}

	public void setTotalNumberOfSoldOffers(Integer totalNumberOfSoldOffers) {
		this.totalNumberOfSoldOffers = totalNumberOfSoldOffers;
	}

}
