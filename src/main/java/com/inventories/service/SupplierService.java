package com.inventories.service;

import com.inventories.model.SupplierEntity;
import org.springframework.data.domain.Page;

public interface SupplierService {
    Page<SupplierEntity> getAllBySupplierName(int page, int size);
    SupplierEntity addSupplier(SupplierEntity supplier);
    SupplierEntity updateSupplier(SupplierEntity supplier);
}
