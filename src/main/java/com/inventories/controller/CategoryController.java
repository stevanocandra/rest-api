package com.inventories.controller;

import com.inventories.model.CategoryEntity;
import com.inventories.service.CategoryService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/api/product/categories")
public class CategoryController {
    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategory(){
        Iterable<CategoryEntity> category = null;
        try{
            category = categoryService.getAllCategory();
        } catch (Exception e) {
            logger.error("An error occurred!");
            return new ResponseEntity(new CustomErrorType("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable>(category, HttpStatus.OK);
    }
}
