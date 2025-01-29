package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;

import java.time.LocalDate;

public class SalesReportDTO {

	private Long id;
	private LocalDate date;
	private Double amount;
	private String seller;


	public SalesReportDTO(Long id, LocalDate date, Double amount, String seller) {
		this.id = id;
		this.date = date;
		this.amount = amount;
		this.seller = seller;
	}

	public SalesReportDTO(Sale entity) {
		id = entity.getId();
		date = entity.getDate();
		amount = entity.getAmount();
		seller = entity.getSeller().getName();
	}



	public Long getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}

	public Double getAmount() {
		return amount;
	}

	public String getSeller() { return seller; }
}
