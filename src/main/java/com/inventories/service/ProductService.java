package com.inventories.service;

import com.inventories.model.ProductEntity;

public interface ProductService {
    ProductEntity findProductById(int id);
    ProductEntity findProductCode(String code);
}
