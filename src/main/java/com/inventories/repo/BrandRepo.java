package com.inventories.repo;

import com.inventories.model.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;

public interface BrandRepo extends JpaRepository<BrandEntity, Integer> {
    BrandEntity findAllByBrandNameIsLike(String name);
    BrandEntity getAllByBrandName(Pageable pageable);
    BrandEntity findById(int id);

    @Query(value = "SELECT nextval('brand_id_seq')", nativeQuery = true)
    Long getNextSeriesId();
}
