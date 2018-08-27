package com.inventories.repo;

import com.inventories.model.SupplierEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface SupplierRepo extends PagingAndSortingRepository<SupplierEntity, Integer> {
    SupplierEntity getAllBySupplierName(Pageable pageable);

    @Query(value = "SELECT nextval('supplier_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}