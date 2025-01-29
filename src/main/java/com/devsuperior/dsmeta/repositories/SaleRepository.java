package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummarytDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesReportDTO(obj.id, obj.date, obj.amount, obj.seller.name) "
            + "FROM Sale obj "
            + "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :sellerName,'%')) "
            +    "and obj.date >= :startDate "
            +    "and obj.date <= :endDate ")
    Page<SalesReportDTO> searchBySellerNameAndDate(String sellerName, LocalDate startDate, LocalDate endDate , Pageable pageable);


    @Query("SELECT new com.devsuperior.dsmeta.dto.SalesSummarytDTO(SUM(obj.amount) as amount) "
            + "FROM Sale obj "
            + "WHERE obj.date >= :startDate "
            +    "and obj.date <= :endDate "
            + "group by obj.seller.name")
    Page<SalesSummarytDTO> summaryByDate (LocalDate startDate, LocalDate endDate , Pageable pageable);
}
