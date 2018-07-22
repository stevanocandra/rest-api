package com.inventories.service;

import com.inventories.model.BrandEntity;
import org.springframework.data.domain.Page;

public interface BrandService {
    BrandEntity findAllByBrandNameIsLike(String name);
    Page<BrandEntity> getAllByBrandName(int page, int size);
    BrandEntity addBrand(BrandEntity brand);
}
