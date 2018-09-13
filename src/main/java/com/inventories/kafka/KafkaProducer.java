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
}