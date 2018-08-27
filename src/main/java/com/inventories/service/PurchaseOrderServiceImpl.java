package com.inventories.service;

import com.inventories.model.PoHeaderEntity;
import com.inventories.repo.PurchaseOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("PurchaseOrderService")
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;

    public Page<PoHeaderEntity> getAllPOByPurchaseDate(int page, int size){
        return purchaseOrderRepo.findAll(new PageRequest(page, size, Sort.Direction.DESC, "purchaseDate"));
    }

    public PoHeaderEntity addPO(PoHeaderEntity poHeaderEntity){
        poHeaderEntity.setId(purchaseOrderRepo.getNextSeriesId().intValue());
        return purchaseOrderRepo.save(poHeaderEntity);
    }
}
