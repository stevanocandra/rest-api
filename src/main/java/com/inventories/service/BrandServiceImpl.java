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

    public Page<BrandEntity> getAllByBrandName(int page, int size, String sort){
        String[] sortSplit = sort.split(",");
        return brandRepo.findAll(new PageRequest(page, size, (sortSplit[1].toUpperCase().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC), sortSplit[0]));
    }

    public BrandEntity findById(int id){
        return brandRepo.findById(id);
    }

    public BrandEntity addBrand(BrandEntity brand) {
        brand.setId(brandRepo.getNextSeriesId().intValue());
        return brandRepo.save(brand);
    }

    public BrandEntity updateBrand(BrandEntity brand) {
        return brandRepo.save(brand);
    }
}
