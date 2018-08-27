package com.inventories.service;

import com.inventories.model.SupplierEntity;
import com.inventories.repo.SupplierRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("SupplierService")
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SupplierRepo supplierRepo;

    public Page<SupplierEntity> getAllBySupplierName(int page, int size){
        return supplierRepo.findAll(new PageRequest(page, size, Sort.Direction.ASC, "supplierName"));
    }

    public SupplierEntity addSupplier(SupplierEntity supplier){
        supplier.setId(supplierRepo.getNextSeriesId().intValue());
        return supplierRepo.save(supplier);
    }

    public SupplierEntity updateSupplier(SupplierEntity supplier){
        return supplierRepo.save(supplier);
    }
}
