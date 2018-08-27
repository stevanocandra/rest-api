package com.inventories.service;

import com.inventories.model.ProductLotEntity;
import com.inventories.repo.ProductLotRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductLotService")
public class ProductLotServiceImpl implements ProductLotService {
    @Autowired
    ProductLotRepo productLotRepo;

    public Iterable<ProductLotEntity> getAllProductLotEntity(){
        return productLotRepo.findAll();
    }

    public ProductLotEntity addProductLotEntity(ProductLotEntity productLotEntity){
        productLotEntity.setId(productLotRepo.getNextSeriesId().intValue());
        return productLotRepo.save(productLotEntity);
    }
}
