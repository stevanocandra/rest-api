package com.inventories.controller;


import com.inventories.model.ProductEntity;
import com.inventories.service.ProductService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("id") int id){
        logger.info("Fetching Product with id {}", id);
        ProductEntity product = null;
        try {
            product = productService.findProductById(id);
        } catch (Exception e) {
            logger.error("An error occurred!");
            return new ResponseEntity(new CustomErrorType("An error occurred: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ProductEntity>(product, HttpStatus.OK);
    }
}
