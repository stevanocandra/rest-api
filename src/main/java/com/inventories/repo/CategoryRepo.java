package com.inventories.repo;

import com.inventories.model.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<CategoryEntity, Integer> {
    @Query(value = "SELECT nextval('category_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
