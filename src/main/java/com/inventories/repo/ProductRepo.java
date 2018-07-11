package com.inventories.repo;

import com.inventories.model.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepo extends CrudRepository<ProductEntity, Integer> {
    ProductEntity findProductEntityByProductCode(String code);
}
