package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.ProductPriceRecordsEntity;
import com.inventories.service.ProductPriceRecordsService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Controller
@RequestMapping("/api/product")
public class ProductPriceRecordsController {
    public static final Logger logger = LoggerFactory.getLogger(ProductPriceRecordsController.class);

    @Autowired
    ProductPriceRecordsService productPriceRecordsService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.product.history.price}")
    String postProductHistoryPriceTopic;

    @GetMapping(value = "/{code}/price/history/{page}/{size}")
    public ResponseEntity<?> getAllProductPriceRecordsByProductCode(@PathVariable("code") String code, @PathVariable("page") int page, @PathVariable("size") int size) {
        logger.info("Fetching all product code: '{}' , record history", code);
        Page<ProductPriceRecordsEntity> brand = null;
        try {
            brand = productPriceRecordsService.getAllProductPriceRecordsByProductCode(page, size);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(brand, HttpStatus.OK);
    }

    @PostMapping(value = "/{code}/price/history/{price}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addProductPriceRecords(@PathVariable("code") String code, @PathVariable(value = "price") BigDecimal price){
        logger.info("Add new product history price");
        ProductPriceRecordsEntity productPriceRecords = new ProductPriceRecordsEntity();
        CustomMessage customMessage = new CustomMessage();
        try {
            productPriceRecords.setProductCode(code);
            productPriceRecords.setProductPrice(price);
            productPriceRecords.setFromDate(new Timestamp(System.currentTimeMillis()));
            kafkaProducer.postPriceRecords(postProductHistoryPriceTopic, kafkaGroupId, productPriceRecords);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Create new history price product");
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
