package com.inventories.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventories.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void postProduct(String topic, String groupId, ProductEntity prmProduct){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmProduct.getProductName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmProduct));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postProductLot(String topic, String groupId, ProductLotEntity prmProductLotEntity){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmProductLotEntity.getLotCode(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmProductLotEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postBrand(String topic, String groupId, BrandEntity prmBrand){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmBrand.getBrandName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmBrand));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postBrandManufacturer(String topic, String groupId, BrandManufacturerEntity prmBrandManufactuer){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmBrandManufactuer.getManufacturerName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmBrandManufactuer));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postCategory(String topic, String groupId, CategoryEntity prmCategoryEntity){
        try{
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmCategoryEntity.getCategoryName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmCategoryEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postGeneric(String topic, String groupId, GenericProductEntity prmGenericProductEntity){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmGenericProductEntity.getGenericName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmGenericProductEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postPriceRecords(String topic, String groupId, ProductPriceRecordsEntity prmProductPriceRecordsEntity){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmProductPriceRecordsEntity.getProductPrice(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmProductPriceRecordsEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void updateStockProduct(String topic, String groupId, StockEntity prmStockEntity){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmStockEntity.getProductCode(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmStockEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postSupplier(String topic, String groupId, SupplierEntity prmSupplierEntity){
        try {
            logger.info("Sending data to kafka = '{}' with topic '{}'", prmSupplierEntity.getSupplierName(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmSupplierEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postPurchaseOrder(String topic, String groupId, PoHeaderEntity prmPoHeaderEntity){
        try {
            logger.info("Sending data to kafka = '{}', '{}' with topic '{}'", prmPoHeaderEntity.getPurchaseDate(), prmPoHeaderEntity.getTotalAmount(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmPoHeaderEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    public void postPurchaseOrderQueue(String topic, String groupId, PoQueueEntity prmPoQueueEntity){
        try {
            logger.info("Sending data to kafka = '{}', '{}' with topic '{}'", prmPoQueueEntity.getQuantity(), prmPoQueueEntity.getUnitPrice(), topic);
            ObjectMapper mapper = new ObjectMapper();
            kafkaTemplate.send(topic, groupId, mapper.writeValueAsString(prmPoQueueEntity));
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }
}