package com.inventories.service;

import com.inventories.model.PoQueueEntity;
import com.inventories.repo.PurchaseOrderQueueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("PurchaseOrderQueueService")
public class PurchaseOrderQueueServiceImpl implements PurchaseOrderQueueService {

    @Autowired
    PurchaseOrderQueueRepo purchaseOrderQueueRepo;

    public Page<PoQueueEntity> findAll(int page, int size){
        return purchaseOrderQueueRepo.findAll(new PageRequest(page, size, Sort.Direction.ASC));
    }

    public PoQueueEntity addPurchaseOrderQueue(PoQueueEntity poQueueEntity){
        poQueueEntity.setId(purchaseOrderQueueRepo.getNextSeriesId().intValue());
        return purchaseOrderQueueRepo.save(poQueueEntity);
    }
}
