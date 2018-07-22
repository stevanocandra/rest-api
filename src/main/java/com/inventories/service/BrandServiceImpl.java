package com.inventories.service;

import com.inventories.model.BrandEntity;
import com.inventories.repo.BrandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("BrandService")
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepo brandRepo;

    public BrandEntity findAllByBrandNameIsLike(String name) {
        return brandRepo.findAllByBrandNameIsLike(name);
    }

    public Page<BrandEntity> getAllByBrandName(int page, int size){
        return brandRepo.findAll(new PageRequest(page, size, Sort.Direction.ASC, "brandName"));
    }

    public BrandEntity addBrand(BrandEntity brand) {
        brand.setId(brandRepo.getNextSeriesId().intValue());
        return brandRepo.save(brand);
    }
}
