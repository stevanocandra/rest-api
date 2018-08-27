package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.PoQueueEntity;
import com.inventories.service.PurchaseOrderQueueService;
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
@RequestMapping(value = "/api/purchase/order/queue")
public class PurchaseOrderQueueController {
    public static final Logger logger = LoggerFactory.getLogger(PurchaseOrderQueueController.class);

    @Autowired
    PurchaseOrderQueueService purchaseOrderQueueService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.purchase.order.queue}")
    String postPurchaseOrderQueueTopic;

    @GetMapping(value = "{page}/{size}")
    public ResponseEntity<?> getAllQueue(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Fetching all purchase order queue");
        Page<PoQueueEntity> poQ = null;
        try {
            poQ = purchaseOrderQueueService.findAll(page, size);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(poQ, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addQueue(@RequestBody PoQueueEntity poQueueEntity){
        logger.info("Process n ew Purchase Order Queue");
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postPurchaseOrderQueue(postPurchaseOrderQueueTopic, kafkaGroupId, poQueueEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new purchase order queue");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
