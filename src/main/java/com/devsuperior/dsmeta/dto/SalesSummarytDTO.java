package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;

import java.time.LocalDate;

public class SalesSummarytDTO {

	private String seller;
	private Double amount;

	public SalesSummarytDTO(String seller, Double amount ) {
		this.seller = seller;
		this.amount = amount;

	}

	public SalesSummarytDTO(SalesSummaryProjection projection) {
		seller = projection.getName();
		amount = projection.getTotal();
	}

	public Double getAmount() {
		return amount;
	}

	public String getSeller() { return seller; }
}
