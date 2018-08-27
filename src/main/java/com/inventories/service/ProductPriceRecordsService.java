package com.inventories.service;

import com.inventories.model.ProductPriceRecordsEntity;
import org.springframework.data.domain.Page;

public interface ProductPriceRecordsService {
    Page<ProductPriceRecordsEntity> getAllProductPriceRecordsByProductCode(int page, int size);
    ProductPriceRecordsEntity addProductPriceRecords(ProductPriceRecordsEntity productPriceRecordsEntity);
}
