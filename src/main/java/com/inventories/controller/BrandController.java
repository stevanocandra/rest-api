package com.inventories.controller;


import com.inventories.model.BrandEntity;
import com.inventories.service.BrandService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/api/brand")
public class BrandController {
    public static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandService brandService;

    @RequestMapping(value="/{page}/{size}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByBrandName(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Fetching all brands");
        Page<BrandEntity> brand = null;
        try {
            brand = brandService.getAllByBrandName(page, size);
        } catch (Exception e){
            logger.error("An error occurred!");
            return new ResponseEntity(new CustomErrorType("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Page>(brand, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addBrand(@RequestBody BrandEntity brandEntity){
        logger.info(("Process add new brand"));
        BrandEntity brand = null;
        try {
            brand = brandService.addBrand(brandEntity);
        } catch (Exception e){
            logger.error("An error occurred!");
            return new ResponseEntity(new CustomErrorType("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BrandEntity>(brand, HttpStatus.OK);
    }
}
