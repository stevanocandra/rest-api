package com.inventories.service;

import com.inventories.model.BrandManufacturerEntity;
import com.inventories.repo.BrandManufacturerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service("BrandManufacturerService")
public class BrandManufacturerServiceImpl implements BrandManufacturerService {
    @Autowired
    BrandManufacturerRepo brandManufacturerRepo;

    public Iterable<BrandManufacturerEntity> getAllBrandManufacturer(){
        return brandManufacturerRepo.findAll();
    }

    public BrandManufacturerEntity addBrandManufacturer(BrandManufacturerEntity brandManufacturer) {
        brandManufacturer.setId(brandManufacturerRepo.getNextSeriesId().intValue());
        return brandManufacturerRepo.save(brandManufacturer);
    }
}
