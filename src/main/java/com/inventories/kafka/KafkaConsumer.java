package com.inventories.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventories.model.*;
import com.inventories.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

@Component
@Transactional
public class KafkaConsumer {
    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    BrandService brandService;

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.post.brand", groupId = "inventories")
    public void processPostBrand(String brandJSON){
        logger.info("received content = '{}'", brandJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            BrandEntity brandEntity = mapper.readValue(brandJSON, BrandEntity.class);
            BrandEntity brand = brandService.addBrand(brandEntity);
            logger.info("Success process brand '{}' with topic '{}'", brand.getBrandName(), "inventories.kafka.post.brand");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

//    @KafkaListener(topics = , groupId = "inventories")
//    public void processPutBrand(String brandJSON){
//        logger.info("received content = '{}'", brandJSON);
//        try{
//            ObjectMapper mapper = new ObjectMapper();
//            BrandEntity brandEntity = mapper.readValue(brandJSON, BrandEntity.class);
//            BrandEntity brand = brandService.updateBrand(brandEntity);
//            logger.info("Success process brand '{}' with topic '{}'", brand.getBrandName(), "inventories.kafka.put.brand");
//        } catch (Exception e){
//            logger.error("An error occurred! '{}'", e.getMessage());
//        }
//    }

    @KafkaListener(topics = {"4igc0qsg-inventories.kafka.put.brand", "4igc0qsg-inventories.kafka.patch.brand"}, groupId = "inventories")
    public void processPatchBrand(String brandJSON){
        logger.info("received content = '{}'", brandJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            BrandEntity brandEntity = mapper.readValue(brandJSON, BrandEntity.class);
            BrandEntity brand = brandService.updateBrand(brandEntity);
            logger.info("Success process brand '{}' with topic '{}'", brand.getBrandName(), "inventories.kafka.patch.brand/inventories.kafka.put.brand");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "4igc0qsg-inventories.kafka.post.brand.manufacturer", groupId = "inventories")
    public void processPostBrandManufacturer(String brandManufacturerJSON){
        logger.info("received content = '{}'", brandManufacturerJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            BrandManufacturerEntity brandManufacturerEntity = mapper.readValue(brandManufacturerJSON, BrandManufacturerEntity.class);
            BrandManufacturerEntity brandManufacturer = brandManufacturerService.addBrandManufacturer(brandManufacturerEntity);
            logger.info("Success process brand manufacturer '{}' with topic '{}'", brandManufacturer.getManufacturerName(), "inventories.kafka.post.brand");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }
}