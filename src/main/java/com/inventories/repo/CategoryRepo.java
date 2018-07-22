package com.inventories.repo;

import com.inventories.model.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository<CategoryEntity, Integer> {
}
