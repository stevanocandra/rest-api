package com.inventories.repo;

import com.inventories.model.BrandManufacturerEntity;
import org.springframework.data.repository.CrudRepository;

public interface BrandManufacturerRepo extends CrudRepository<BrandManufacturerEntity, Integer> {
}
