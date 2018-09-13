package com.inventories.service;

import com.inventories.model.BrandEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface BrandService {
    BrandEntity findAllByBrandNameIsLike(String name);
    Page<BrandEntity> getAllByBrandName(int page, int size, String sort);
    BrandEntity addBrand(BrandEntity brand);
    BrandEntity findById(int id);
    BrandEntity updateBrand(BrandEntity brandEntity);
}
