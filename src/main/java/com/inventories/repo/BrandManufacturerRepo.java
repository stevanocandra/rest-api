package com.inventories.repo;

import com.inventories.model.BrandManufacturerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BrandManufacturerRepo extends CrudRepository<BrandManufacturerEntity, Integer> {
    @Query(value = "SELECT nextval('brand_manufacturer_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
