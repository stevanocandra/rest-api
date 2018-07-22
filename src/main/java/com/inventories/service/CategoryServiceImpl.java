package com.inventories.service;

import com.inventories.model.CategoryEntity;
import com.inventories.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryService")
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepo categoryRepo;

    public Iterable<CategoryEntity> getAllCategory(){
        return categoryRepo.findAll();
    }
}
