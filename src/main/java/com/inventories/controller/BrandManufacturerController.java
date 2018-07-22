package com.inventories.controller;

import com.inventories.model.BrandManufacturerEntity;
import com.inventories.service.BrandManufacturerService;
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
@RequestMapping("/api/brand/manufacturer")
public class BrandManufacturerController {
    public static final Logger logger = LoggerFactory.getLogger(BrandManufacturerController.class);

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllBrandManufactuer(){
        Iterable<BrandManufacturerEntity> brandManufacturer = null;
        try {
            brandManufacturer = brandManufacturerService.getAllBrandManufacturer();
        } catch (Exception e) {
            logger.error("An error occurred!");
            return new ResponseEntity(new CustomErrorType("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Iterable>(brandManufacturer, HttpStatus.OK);
    }
}
