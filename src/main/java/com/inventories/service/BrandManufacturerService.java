package com.inventories.service;

import com.inventories.model.BrandManufacturerEntity;

import java.util.List;

public interface BrandManufacturerService {
    List<BrandManufacturerEntity> getAllBrandManufacturer();
    BrandManufacturerEntity addBrandManufacturer(BrandManufacturerEntity brandManufacturer);
}
