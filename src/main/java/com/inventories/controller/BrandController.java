package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.BrandEntity;
import com.inventories.util.ArrayListCustomMessage;
import com.inventories.util.MultiResource;
import com.inventories.model.CustomMessage;
import com.inventories.service.BrandService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;

@RestController
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

    @Value("${inventories.kafka.put.brand}")
    String putBrandTopic;

    @Value("${inventories.kafka.patch.brand}")
    String patchBrandTopic;


    @GetMapping(value="")
    public ResponseEntity<?> getAllByBrandName(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam(value = "sort", defaultValue = "brandName,asc") String sort, PagedResourcesAssembler pagedResourcesAssembler){
        logger.info("Fetching all brands");
        Page<BrandEntity> brand = null;
        try {
            brand = brandService.getAllByBrandName(page, size, sort);
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.put("Cache-Control", Arrays.asList("max-age=5"));
        PagedResources<MultiResource> pagedResources = pagedResourcesAssembler.toResource(brand);
        return new ResponseEntity<PagedResources>(pagedResources, headers, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addBrand(@RequestBody BrandEntity brandEntity){
        logger.info(("Process add new brand"));
        Resources<CustomMessage> res = null;
        try {
            kafkaProducer.postBrand(postBrandTopic, kafkaGroupId, brandEntity);
            List<CustomMessage> customMessageList = ArrayListCustomMessage.setMessage("Created new brand", HttpStatus.OK);
            res = new Resources<>(customMessageList);
            res.add(linkTo(BrandController.class).withSelfRel());
            res.add(linkTo(BrandManufacturerController.class).withRel("brand_manufacturer"));
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Resources>(res, HttpStatus.OK);
    }

    @PostMapping(value = "/layered", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addLayeredBrand(@RequestBody BrandEntity brandEntity){
        logger.info(("Process add new brand"));
        Resources<CustomMessage> res = null;
        try {
            brandService.addBrand(brandEntity);
            List<CustomMessage> customMessageList = ArrayListCustomMessage.setMessage("Created new brand layered", HttpStatus.OK);
            res = new Resources<>(customMessageList);
            res.add(linkTo(BrandController.class).withSelfRel());
            res.add(linkTo(BrandManufacturerController.class).withRel("brand_manufacturer"));
        } catch (Exception e){
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Resources>(res, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> putBrand(@PathVariable("id") int id, @RequestBody BrandEntity brandEntity){
        logger.info("Process put brand");
        Resources<CustomMessage> res = null;
        try {
            List<CustomMessage> customMessageList = null;
            BrandEntity brand = brandService.findById(id);
            if (brand != null) {
                customMessageList = ArrayListCustomMessage.setMessage("Put brand process", HttpStatus.OK);
                brandEntity.setId(id);
                kafkaProducer.postBrand(putBrandTopic, kafkaGroupId, brandEntity);
            } else {
                customMessageList = ArrayListCustomMessage.setMessage("Brand Id" + id + " Not Found!", HttpStatus.BAD_REQUEST);
                res = new Resources<>(customMessageList);
                res.add(linkTo(BrandController.class).withSelfRel());
                return new ResponseEntity<Resources>(res, HttpStatus.BAD_REQUEST);
            }
            res = new Resources<>(customMessageList);
            res.add(linkTo(BrandController.class).slash(id).withSelfRel());
            res.add(linkTo(BrandManufacturerController.class).withRel("brand_manufacturer"));
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Resources>(res, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> updateBrand(@PathVariable("id") int id, @RequestBody BrandEntity brandEntity){
        logger.info("Process patch brand");
        Resources<CustomMessage> res = null;
        try {
            List<CustomMessage> customMessageList = null;
            BrandEntity brand = brandService.findById(id);
            if (brand != null) {
                customMessageList = ArrayListCustomMessage.setMessage("Patch brand process", HttpStatus.OK);
                brandEntity.setId(id);
                brandEntity.setBrandCode(brand.getBrandCode());
                kafkaProducer.postBrand(patchBrandTopic, kafkaGroupId, brandEntity);
            } else {
                customMessageList = ArrayListCustomMessage.setMessage("Brand Id" + id + " Not Found!", HttpStatus.BAD_REQUEST);
                res = new Resources<>(customMessageList);
                res.add(linkTo(BrandController.class).withSelfRel());
                return new ResponseEntity<Resources>(res, HttpStatus.BAD_REQUEST);
            }
            res = new Resources<>(customMessageList);
            res.add(linkTo(BrandController.class).slash(id).withSelfRel());
            res.add(linkTo(BrandManufacturerController.class).withRel("brand_manufacturer"));
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Resources>(res, HttpStatus.OK);
    }
}
