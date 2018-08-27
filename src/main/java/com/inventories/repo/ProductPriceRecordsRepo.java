package com.inventories.repo;

import com.inventories.model.ProductPriceRecordsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface ProductPriceRecordsRepo extends PagingAndSortingRepository<ProductPriceRecordsEntity, Integer> {
    ProductPriceRecordsEntity getAllProductPriceRecordsByProductCode(Pageable pageable);
}
