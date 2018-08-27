package com.inventories.service;

import com.inventories.model.GenericProductEntity;

public interface GenericProductService {
    Iterable<GenericProductEntity> getAllGenericProduct();
    GenericProductEntity addGenericProduct(GenericProductEntity genericProductEntity);
}
