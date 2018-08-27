package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.PoHeaderEntity;
import com.inventories.service.PurchaseOrderService;
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

@Controller
@RequestMapping(value = "/api/purchase/order")
public class PurchaseOrderController {
    public static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.purchase.order}")
    String postPurchaseOrderTopic;

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<?> getAllPOByPurchaseDate(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Fetching all brands");
        Page<PoHeaderEntity> po = null;
        try {
            po = purchaseOrderService.getAllPOByPurchaseDate(page, size);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(po, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addPO(@RequestBody PoHeaderEntity poHeaderEntity){
        logger.info("Process n ew Purchase Order");
        PoHeaderEntity poHeader = null;
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postPurchaseOrder(postPurchaseOrderTopic, kafkaGroupId, poHeaderEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new purchase order");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
