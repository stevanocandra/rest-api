package com.inventories.service;

import com.inventories.model.BrandManufacturerEntity;

public interface BrandManufacturerService {
    Iterable<BrandManufacturerEntity> getAllBrandManufacturer();
    BrandManufacturerEntity addBrandManufacturer(BrandManufacturerEntity brandManufacturer);
}
