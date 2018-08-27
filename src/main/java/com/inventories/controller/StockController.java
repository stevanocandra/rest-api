package com.inventories.controller;

import com.inventories.kafka.KafkaProducer;
import com.inventories.model.CustomMessage;
import com.inventories.model.StockEntity;
import com.inventories.service.StockService;
import com.inventories.util.CustomErrorType;
import io.sentry.Sentry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/api/stock")
public class StockController {
    public static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    StockService stockService;

    @Autowired
    KafkaProducer kafkaProducer;

    @Value("${spring.kafka.consumer.group-id}")
    String kafkaGroupId;

    @Value("${inventories.kafka.update.stock.product}")
    String updateStockProductTopic;

    /**
     * To update stock based on parameter barcode
     * @param code == Product Code == Product Barcode
     * @return
     */
    @PatchMapping(value = "/{code}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> updateStockProduct(@PathVariable("code") String code, @RequestParam(value = "qty")Optional<String> qty){
        logger.info("Fetching Product with code {}", code);
        StockEntity stock = null;
        CustomMessage customMessage = new CustomMessage();
        try{
            stock = stockService.findByProductCode(code);
            if (stock != null){
                logger.info("Fetch qty : {}", qty);
                Sentry.capture("Fetch qty : " + qty);
                if (qty.isPresent()){
                    stock = stockService.addQTY(stock, Integer.parseInt(qty.get()));
                } else {
                    stock = stockService.addQTY(stock, 1);
                }
                logger.info("Fetch added qty : {}", qty);
                logger.info("Updating...");
                kafkaProducer.updateStockProduct(updateStockProductTopic, kafkaGroupId, stock);
                logger.info("Success update...");
                customMessage.setStatusCode(HttpStatus.OK.value());
                customMessage.setMessage("Success update stock product");
            } else {
                customMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
                customMessage.setMessage("Product Code " + code + " Not Found!");
                return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            logger.error("An error occurred! {}", e.getMessage());
            Sentry.capture(e);
            CustomErrorType.returnResponsEntityError(e.getMessage());
        }
        return new ResponseEntity<CustomMessage>(customMessage, HttpStatus.OK);
    }

    @PutMapping(value = "/{code}", consumes = {"application/json", "application/soap+xml"})
    public ResponseEntity<?> updateStock(@PathVariable("code") String code, @RequestBody StockEntity stockEntity) {
        logger.info("Fetching Product with code {}", code);
        StockEntity stock = null;
        CustomMessage customMessage = new CustomMessage();
        try {
            stock = stockService.findByProductCode(code);
            if (stock != null) {
                stockEntity.setProductCode(code);
                logger.info("Updating all values...");
                kafkaProducer.updateStockProduct(updateStockProductTopic, kafkaGroupId, stockEntity);
                logger.info("Success update...");
                customMessage.setStatusCode(HttpStatus.OK.value());
                customMessage.setMessage("Success update stock product");
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
}
