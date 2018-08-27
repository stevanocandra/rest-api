package com.inventories.service;

import com.inventories.model.ProductLotEntity;

public interface ProductLotService {
    Iterable<ProductLotEntity> getAllProductLotEntity();
    ProductLotEntity addProductLotEntity(ProductLotEntity productLotEntity);
}
