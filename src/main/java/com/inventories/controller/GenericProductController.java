package com.inventories.controller;

import com.inventories.model.GenericProductEntity;
import com.inventories.service.GenericProductService;
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
@RequestMapping("/api/product/generic")
public class GenericProductController {
    public static final Logger logger = LoggerFactory.getLogger(GenericProductController.class);

    @Autowired
    GenericProductService genericProductService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenericProduct(){
        Iterable<GenericProductEntity> genericProduct = null;
        try {
            genericProduct = genericProductService.getAllGenericProduct();
        } catch (Exception e){
            logger.error("An error occurred!");
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Iterable>(genericProduct, HttpStatus.OK);
    }
}
