package com.inventories.controller;


import com.inventories.kafka.KafkaProducer;
import com.inventories.model.BrandEntity;
import com.inventories.model.CustomMessage;
import com.inventories.service.BrandService;
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
@RequestMapping(value = "/api/brand")
public class BrandController {
    public static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandService brandService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.brand}")
    String postBrandTopic;


    @GetMapping(value="/{page}/{size}")
    public ResponseEntity<?> getAllByBrandName(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Fetching all brands");
        Page<BrandEntity> brand = null;
        try {
            brand = brandService.getAllByBrandName(page, size);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(brand, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addBrand(@RequestBody BrandEntity brandEntity){
        logger.info(("Process add new brand"));
        BrandEntity brand = null;
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postBrand(postBrandTopic, kafkaGroupId, brandEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new brand");
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
