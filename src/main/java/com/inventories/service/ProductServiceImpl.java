package com.inventories.service;

import com.inventories.model.ProductEntity;
import com.inventories.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    public ProductEntity findProductById(int id){
        return productRepo.findById(id).get();
    }

    public ProductEntity findProductCode(String code){
        return productRepo.findProductEntityByProductCode(code);
    }
}