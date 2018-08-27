package com.inventories.repo;

import com.inventories.model.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<ProductEntity, Integer> {
    ProductEntity findProductEntityByProductCode(String code);
    Page<ProductEntity> findAll(Pageable pageable);

    @Query(value = "SELECT nextval('product_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
