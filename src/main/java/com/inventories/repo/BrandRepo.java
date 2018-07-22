package com.inventories.repo;

import com.inventories.model.BrandEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface BrandRepo extends PagingAndSortingRepository<BrandEntity, Integer> {
    BrandEntity findAllByBrandNameIsLike(String name);
    BrandEntity getAllByBrandName(Pageable pageable);

    @Query(value = "SELECT nextval('brand_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
