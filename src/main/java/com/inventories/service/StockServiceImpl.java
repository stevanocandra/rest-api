package com.inventories.service;

import com.inventories.model.StockEntity;
import com.inventories.repo.StockRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("StockService")
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepo stockRepo;

    public StockEntity findByProductCode(String productCode) {
        return stockRepo.findStockEntitiesByProductCode(productCode);
    }

    public StockEntity addQTY(StockEntity stockEntity, int count){
        stockEntity.setQuantityInHand(stockEntity.getQuantityInHand() + count);
        return stockEntity;
    }

    @Transactional
    public StockEntity update(StockEntity stockEntity){
        return stockRepo.save(stockEntity);
    }
}
