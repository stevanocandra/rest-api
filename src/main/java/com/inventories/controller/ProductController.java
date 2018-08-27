package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.kafka.KafkaStorage;
import com.inventories.model.CustomMessage;
import com.inventories.model.ProductEntity;
import com.inventories.service.ProductService;
import com.inventories.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.post.product}")
    String postProductTopic;

    @Value("${inventories.kafka.patch.product}")
    String patchProductTopic;

    @Autowired
    ProductService productService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaStorage kafkaStorage;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getProduct(@PathVariable("id") int id){
        logger.info("Fetching Product with id {}", id);
        ProductEntity product = null;
        try {
            product = productService.findProductById(id);
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<ProductEntity>(product, HttpStatus.OK);
    }

    @GetMapping(value = "/{page}/{size}")
    public ResponseEntity<?> getProducts(@PathVariable("page") int page, @PathVariable("size") int size){
        logger.info("Fetching products from page '{}' with size '{}'", page, size);
        Page<ProductEntity> products = null;
        try {
            products = productService.getAllByProductName(page, size);
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<Page>(products, HttpStatus.OK);
    }

    @PatchMapping(value = "/{code}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> updateProduct(@PathVariable("code") String code, @RequestBody ProductEntity productEntity){
        logger.info("Updating product '{}' , topic '{}'", productEntity.getProductName(), patchProductTopic);
        CustomMessage customMessage = new CustomMessage();
        try {
            ProductEntity product = productService.findProductCode(code);
            if (product != null) {
                productEntity.setProductCode(code);
                productEntity.setId(product.getId());
                kafkaProducer.postProduct(patchProductTopic, kafkaGroupId, productEntity);
                customMessage.setStatusCode(HttpStatus.OK.value());
                customMessage.setMessage("The product updated");
            } else {
                customMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customMessage.setMessage("Product Code " + code + " Not Found!");
                return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }

    @PostMapping(value = "", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> addProduct(@RequestBody ProductEntity productEntity){
        CustomMessage customMessage = new CustomMessage();
        try {
            kafkaProducer.postProduct(postProductTopic, kafkaGroupId, productEntity);
            customMessage.setStatusCode(HttpStatus.OK.value());
            customMessage.setMessage("Created new product");
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }
}
