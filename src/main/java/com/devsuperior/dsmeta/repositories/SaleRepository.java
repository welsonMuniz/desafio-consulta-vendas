package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SalesReportDTO;
import com.devsuperior.dsmeta.dto.SalesSummarytDTO;
import com.devsuperior.dsmeta.projections.SalesSummaryProjection;
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


    @Query(nativeQuery = true, value = "SELECT TB_SELLER.NAME, sum(TB_SALES.amount) as total FROM TB_SALES " +
            "INNER JOIN TB_SELLER ON (TB_SALES.SELLER_ID = TB_SELLER .ID) " +
            "WHERE TB_SALES.DATE >= :startDate " +
            "  AND TB_SALES.DATE <= :endDate " +
            "GROUP BY TB_SELLER.NAME ")
    Page<SalesSummaryProjection> summaryByDate (LocalDate startDate, LocalDate endDate , Pageable pageable);

}