package com.inventories.service;

import com.inventories.model.GenericProductEntity;
import com.inventories.repo.GenericProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GenericProductService")
public class GenericProductServiceImpl implements GenericProductService {
    @Autowired
    GenericProductRepo genericProductRepo;

    public Iterable<GenericProductEntity> getAllGenericProduct(){
        return genericProductRepo.findAll();
    }

    public GenericProductEntity addGenericProduct(GenericProductEntity genericProductEntity) {
        genericProductEntity.setId(genericProductRepo.getNextSeriesId().intValue());
        return genericProductRepo.save(genericProductEntity);
    }
}
