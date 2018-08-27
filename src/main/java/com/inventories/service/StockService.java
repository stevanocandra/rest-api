package com.inventories.service;

import com.inventories.model.StockEntity;

public interface StockService {
    StockEntity findByProductCode(String productCode);
    StockEntity addQTY(StockEntity stockEntity, int count);
    StockEntity update(StockEntity stockEntity);
}
