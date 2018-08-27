package com.inventories.service;

import com.inventories.model.ProductPriceRecordsEntity;
import com.inventories.repo.ProductPriceRecordsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

@Service("ProductPriceRecords")
public class ProductPriceRecordsServiceImpl implements ProductPriceRecordsService {

    @Autowired
    ProductPriceRecordsRepo productPriceRecordsRepo;

    public Page<ProductPriceRecordsEntity> getAllProductPriceRecordsByProductCode(int page, int size){
        return productPriceRecordsRepo.findAll(new PageRequest(page, size, Sort.Direction.DESC, "fromDate"));
    }

    public ProductPriceRecordsEntity addProductPriceRecords(ProductPriceRecordsEntity productPriceRecordsEntity){
        return productPriceRecordsRepo.save(productPriceRecordsEntity);
    }
}
