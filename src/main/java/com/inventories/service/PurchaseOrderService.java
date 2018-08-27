package com.inventories.service;

import com.inventories.model.PoHeaderEntity;
import org.springframework.data.domain.Page;

public interface PurchaseOrderService {
    Page<PoHeaderEntity> getAllPOByPurchaseDate(int page, int size);
    PoHeaderEntity addPO(PoHeaderEntity poHeaderEntity);
}
