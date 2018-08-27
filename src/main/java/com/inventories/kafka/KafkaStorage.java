package com.inventories.kafka;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import com.inventories.model.ProductEntity;
import com.inventories.model.BrandEntity;

@Component
public class KafkaStorage {
    private List<ProductEntity> storageProduct = new ArrayList<ProductEntity>();
    private List<BrandEntity> storageBrand = new ArrayList<BrandEntity>();

    public void putProduct(ProductEntity productEntity){
        storageProduct.add(productEntity);
    }

    public void clearProduct(){
        storageProduct.clear();
    }

    public void clearBrand(){
        storageProduct.clear();
    }

    public void clearAll(){
        storageProduct.clear();
        storageBrand.clear();
    }
}