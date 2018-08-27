package com.inventories.service;

import com.inventories.model.PoQueueEntity;
import org.springframework.data.domain.Page;

public interface PurchaseOrderQueueService {
    Page<PoQueueEntity> findAll(int page, int size);
    PoQueueEntity addPurchaseOrderQueue(PoQueueEntity poQueueEntity);
}
