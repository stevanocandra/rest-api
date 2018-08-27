package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.SupplierEntity;
import com.inventories.service.SupplierService;
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
@RequestMapping(value = "/api/supplier")
public class SupplierController {
    public static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

    @Autowired
    SupplierService supplierService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.supplier}")
    String postSupplierTopic;

    @Value("${inventories.kafka.patch.supplier}")
    String patchSupplierTopic;

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<?> getAllbySupplierName(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Feching all supplier");
        Page<SupplierEntity> supplier = null;
        try{
            supplier = supplierService.getAllBySupplierName(page, size);
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(supplier, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> postSupplier(@RequestBody SupplierEntity supplierEntity){
        logger.info(("Process add new supplier"));
        SupplierEntity supplier = null;
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postSupplier(postSupplierTopic, kafkaGroupId, supplierEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new supplier");
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }

    @PatchMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> patchSupplier(@RequestBody SupplierEntity supplierEntity){
        logger.info("Process to update supplier");
        SupplierEntity supplier = null;
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postSupplier(patchSupplierTopic, kafkaGroupId, supplierEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Update supplier");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
