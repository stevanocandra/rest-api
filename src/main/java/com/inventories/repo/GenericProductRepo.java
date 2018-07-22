package com.inventories.repo;

import com.inventories.model.GenericProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface GenericProductRepo extends CrudRepository<GenericProductEntity, Integer> {
}
