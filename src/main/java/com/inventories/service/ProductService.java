package com.inventories.service;

import com.inventories.model.ProductEntity;
import org.springframework.data.domain.Page;

public interface ProductService {
    ProductEntity findProductById(int id);
    ProductEntity findProductCode(String code);
    Page<ProductEntity> getAllByProductName(int page, int size);
    ProductEntity updateProduct(ProductEntity productEntity);
    ProductEntity addProduct(ProductEntity productEntity);
}
