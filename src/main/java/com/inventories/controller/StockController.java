package com.inventories.controller;

import com.inventories.model.ProductEntity;
import com.inventories.model.StockEntity;
import com.inventories.service.ProductService;
import com.inventories.service.StockService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/stock")
public class StockController {
    public static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    ProductService productService;

    @Autowired
    StockService stockService;

    /**
     * To update stock based on parameter barcode
     * @param code == Product Code == Product Barcode
     * @return
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateStockProduct(@PathVariable("code") String code, @RequestParam(value = "qty")Optional<String> qty){
        logger.info("Fetching Product with code {}", code);
        ProductEntity product = null;
        StockEntity stock = null;
        try{
            product = productService.findProductCode(code);
            if (product != null){
                logger.info("Fetch qty : {}", qty);
                stock = stockService.findByProductId(product.getId());
                if (qty.isPresent()){
                    stock = stockService.addQTY(stock, Integer.parseInt(qty.get()));
                } else {
                    stock = stockService.addQTY(stock, 1);
                }
                logger.info("Fetch added qty : {}", qty);
                logger.info("Updating...");
                stockService.update(stock);
                logger.info("Success update...");
            }
        } catch (Exception e) {
            logger.error("An error occurred!");
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<StockEntity>(stock, HttpStatus.OK);
    }
}
