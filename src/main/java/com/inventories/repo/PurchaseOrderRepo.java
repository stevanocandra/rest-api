package com.inventories.repo;

import com.inventories.model.PoHeaderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface PurchaseOrderRepo extends PagingAndSortingRepository<PoHeaderEntity, Integer> {
    PoHeaderEntity getAllPurchaseOrderById(Pageable pageable);

    PoHeaderEntity getAllByPurchaseDateOrderByPurchaseDate(Pageable pageable);

    @Query(value = "SELECT nextval('po_header_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
