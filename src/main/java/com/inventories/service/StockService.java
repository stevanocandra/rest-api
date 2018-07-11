package com.inventories.service;

import com.inventories.model.StockEntity;

import java.util.Optional;

public interface StockService {
    StockEntity findByProductId(int id);
    StockEntity addQTY(StockEntity stockEntity, int count);
    StockEntity update(StockEntity stockEntity);
}
