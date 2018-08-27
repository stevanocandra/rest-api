package com.inventories.repo;

import com.inventories.model.ProductLotEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductLotRepo extends CrudRepository<ProductLotEntity, Integer> {
    @Query(value = "SELECT nextval('product_lot_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
