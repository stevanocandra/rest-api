package com.inventories.service;

import com.inventories.model.ProductEntity;
import com.inventories.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<ProductEntity> getAllByProductName(int page, int size) {
        return productRepo.findAll(new PageRequest(page, size, Sort.Direction.ASC, "productName"));
    }

    public ProductEntity updateProduct(ProductEntity productEntity) {
        return productRepo.save(productEntity);
    }

    public ProductEntity addProduct(ProductEntity product) {
        product.setId(productRepo.getNextSeriesId().intValue());
        return productRepo.save(product);
    }
}