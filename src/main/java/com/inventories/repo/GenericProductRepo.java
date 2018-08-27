package com.inventories.repo;

import com.inventories.model.GenericProductEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GenericProductRepo extends CrudRepository<GenericProductEntity, Integer> {
    @Query(value = "SELECT nextval('generic_product_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
