package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.BrandManufacturerEntity;
import com.inventories.model.CustomMessage;
import com.inventories.resource.BrandManufacturerResource;
import com.inventories.service.BrandManufacturerService;
import com.inventories.util.CustomErrorType;
import com.inventories.resource.MultiResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Controller
@RequestMapping("/admin/brand/manufacturer")
public class BrandManufacturerController {
    public static final Logger logger = LoggerFactory.getLogger(BrandManufacturerController.class);

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.brand.manufacturer}")
    String postBrandManufacturerTopic;

    @GetMapping(value = "")
    public ResponseEntity<?> getAllBrandManufactuer(){
        List<BrandManufacturerEntity> brandManufacturer = null;
        try {
            brandManufacturer = brandManufacturerService.getAllBrandManufacturer();
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        Resources<BrandManufacturerResource> res = new Resources(brandManufacturer);
        res.add(linkTo(BrandManufacturerController.class).withSelfRel());
        res.add(linkTo(BrandController.class).withRel("brand"));
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addBrandManufacturer(@RequestBody BrandManufacturerEntity brandManufacturerEntity){
        BrandManufacturerEntity brandManufacturer = null;
        logger.info(("Process add new brand manufacturer"));
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postBrandManufacturer(postBrandManufacturerTopic, kafkaGroupId, brandManufacturerEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new brand manufacturer");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
