package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummarytDTO;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public Page<SalesReportDTO> getReportSales(
			String minDate,
			String maxDate,
			String name,
			Pageable pageable){

		LocalDate endPeriod = null;
		LocalDate startPeriod;
		if (maxDate.equalsIgnoreCase("")){
			endPeriod = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			endPeriod = LocalDate.parse(maxDate);
		}

		if (minDate.equalsIgnoreCase("")){
			startPeriod = endPeriod.minusYears(1L);
		}else{
			startPeriod = LocalDate.parse(minDate);
		}

		Page<SalesReportDTO> result = repository.searchBySellerNameAndDate(
				name,
				startPeriod,
				endPeriod,
				pageable);
		return result;
	}

	public Page<SalesSummaryProjection> getSummaryBySeller(
			String minDate,
			String maxDate,
			Pageable pageable){

		LocalDate endPeriod = null;
		LocalDate startPeriod;
		if (maxDate.equalsIgnoreCase("")){
			endPeriod = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		}else{
			endPeriod = LocalDate.parse(maxDate);
		}

		if (minDate.equalsIgnoreCase("")){
			startPeriod = endPeriod.minusYears(1L);
		}else{
			startPeriod = LocalDate.parse(minDate);
		}

		 Page<SalesSummaryProjection> result = repository.summaryByDate(
				startPeriod,
				endPeriod,
				pageable);
		return result;
	}

}