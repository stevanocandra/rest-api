package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.GenericProductEntity;
import com.inventories.service.GenericProductService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product/generic")
public class GenericProductController {
    public static final Logger logger = LoggerFactory.getLogger(GenericProductController.class);

    @Autowired
    GenericProductService genericProductService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${inventories.kafka.post.generic}")
    String postGenericTopic;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllGenericProduct(){
        Iterable<GenericProductEntity> genericProduct = null;
        try {
            genericProduct = genericProductService.getAllGenericProduct();
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Iterable>(genericProduct, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addGenericProduct(@RequestBody GenericProductEntity genericProductEntity){
        logger.info(("Process add new generic product"));
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postGeneric(postGenericTopic, kafkaGroupId, genericProductEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new generic product");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
