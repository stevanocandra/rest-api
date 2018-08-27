package com.inventories.repo;

import com.inventories.model.StockEntity;
import org.springframework.data.repository.CrudRepository;

public interface StockRepo extends CrudRepository<StockEntity, Integer> {
    StockEntity findStockEntitiesByProductCode(String productCode);
}
