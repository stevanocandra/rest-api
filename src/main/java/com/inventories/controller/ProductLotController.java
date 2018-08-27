package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.ProductLotEntity;
import com.inventories.service.ProductLotService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/product/lot")
public class ProductLotController {
    public static final Logger logger = LoggerFactory.getLogger(ProductLotController.class);

    @Autowired
    ProductLotService productLotService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.product.lot}")
    String postProductLot;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllProductLot(){
        Iterable<ProductLotEntity> productLot = null;
        try {
            productLot = productLotService.getAllProductLotEntity();
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Iterable>(productLot, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addProductLot(@RequestBody ProductLotEntity productLotEntity) {
        logger.info(("Process add new product lot"));
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postProductLot(postProductLot, kafkaGroupId, productLotEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new product lot");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
