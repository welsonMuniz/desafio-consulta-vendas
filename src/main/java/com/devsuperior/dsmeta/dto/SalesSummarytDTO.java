package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SalesSummarytDTO {

	private String seller;
	private Double amount;

	public SalesSummarytDTO(String seller, Double amount ) {
		this.seller = seller;
		this.amount = amount;

	}

	public SalesSummarytDTO(Sale entity) {
		seller = entity.getSeller().getName();
		amount = entity.getAmount();
	}

	public Double getAmount() {
		return amount;
	}

	public String getSeller() { return seller; }
}
