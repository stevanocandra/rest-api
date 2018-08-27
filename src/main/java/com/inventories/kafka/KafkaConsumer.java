package com.inventories.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inventories.model.*;
import com.inventories.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    KafkaStorage storage;

    @Autowired
    ProductService productService;

    @Autowired
    ProductLotService productLotService;

    @Autowired
    ProductPriceRecordsService productPriceRecordsService;

    @Autowired
    BrandService brandService;

    @Autowired
    BrandManufacturerService brandManufacturerService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    GenericProductService genericProductService;

    @Autowired
    StockService stockService;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    @Autowired
    PurchaseOrderQueueService purchaseOrderQueueService;

    @Autowired
    SupplierService supplierService;

    @KafkaListener(topics = "inventories.kafka.post.product", groupId = "inventories")
    public void processPostProduct(String productJSON){
        logger.info("received content = '{}'", productJSON);
        try {
            ObjectMapper mapper = new ObjectMapper();
            ProductEntity productEntity = mapper.readValue(productJSON, ProductEntity.class);
            ProductEntity product = productService.addProduct(productEntity);
//            storage.putProduct(product);
            logger.info("Success process product '{}' with topic '{}'", product.getProductName(), "inventories.kafka.post.product");
        } catch (Exception e) {
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.patch.product", groupId = "inventories")
    public void processPatchProduct(String productJSON){
        logger.info("received content = '{}'", productJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ProductEntity productEntity = mapper.readValue(productJSON, ProductEntity.class);
            ProductEntity product = productService.updateProduct(productEntity);
            logger.info("Success process product '{}' with topic '{}'", product.getProductName(), "inventories.kafka.patch.product");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.product.lot", groupId = "inventories")
    public void processPostProductLot(String productLotJSON){
        logger.info("received content = '{}'", productLotJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ProductLotEntity productLotEntity = mapper.readValue(productLotJSON, ProductLotEntity.class);
            ProductLotEntity productLot = productLotService.addProductLotEntity(productLotEntity);
            logger.info("Success process product with lot code '{}' with topic '{}'", productLot.getLotCode(), "inventories.kafka.post.product.lot");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.product.history.price", groupId = "inventories")
    public void processPostProductHistoryPrice(String productHistoryPriceJSON){
        logger.info("received content = '{}'", productHistoryPriceJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            ProductPriceRecordsEntity productPriceRecordsEntity = mapper.readValue(productHistoryPriceJSON, ProductPriceRecordsEntity.class);
            ProductPriceRecordsEntity productPriceRecords = productPriceRecordsService.addProductPriceRecords(productPriceRecordsEntity);
            logger.info("Success process product with price '{}' with topic '{}'", productPriceRecords.getProductPrice(), "inventories.kafka.post.product.history.price");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.brand", groupId = "inventories")
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

    @KafkaListener(topics = "inventories.kafka.post.brand.manufacturer", groupId = "inventories")
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

    @KafkaListener(topics = "inventories.kafka.post.category", groupId = "inventories")
    public void processPostCategory(String categoryJSON){
        logger.info("received content = '{}'", categoryJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            CategoryEntity categoryEntity = mapper.readValue(categoryJSON, CategoryEntity.class);
            CategoryEntity category = categoryService.addCategory(categoryEntity);
            logger.info("Success process brand manufacturer '{}' with topic '{}'", category.getCategoryName(), "inventories.kafka.post.category");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.generic", groupId = "inventories")
    public void processPostGeneric(String genericJSON){
        logger.info("received content = '{}'", genericJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            GenericProductEntity genericProductEntity = mapper.readValue(genericJSON, GenericProductEntity.class);
            GenericProductEntity generic = genericProductService.addGenericProduct(genericProductEntity);
            logger.info("Success process generic '{}' with topic '{}'", generic.getGenericName(), "inventories.kafka.post.generic");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.update.stock.product", groupId = "inventories")
    public void processPatchStockProduct(String stockProductJSON){
        logger.info("received content = '{}'", stockProductJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            StockEntity stockEntity = mapper.readValue(stockProductJSON, StockEntity.class);
            StockEntity stock = stockService.update(stockEntity);
            logger.info("Success process product code '{}', stock '{}' with topic '{}'", stock.getProductCode(), stock.getQuantityInHand(), "inventories.kafka.update.stock.product");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.purchase.order", groupId = "inventories")
    public void processPostPurchaseOrder(String purchaseOrderJSON){
        logger.info("received content = '{}'", purchaseOrderJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            PoHeaderEntity poHeaderEntity = mapper.readValue(purchaseOrderJSON, PoHeaderEntity.class);
            PoHeaderEntity po = purchaseOrderService.addPO(poHeaderEntity);
            logger.info("Success process po date '{}', amount '{}' with topic '{}'", po.getPurchaseDate(), po.getTotalAmount(), "inventories.kafka.post.purchase.order");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.purchase.order.queue", groupId = "inventories")
    public void processPostPurchaseOrderQueue(String purchaseOrderQueueJSON){
        logger.info("received content = '{}'", purchaseOrderQueueJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            PoQueueEntity poQueueEntity = mapper.readValue(purchaseOrderQueueJSON, PoQueueEntity.class);
            PoQueueEntity poQ = purchaseOrderQueueService.addPurchaseOrderQueue(poQueueEntity);
            logger.info("Success process po quantity '{}', price '{}' with topic '{}'", poQ.getQuantity(), poQ.getUnitPrice(), "inventories.kafka.post.purchase.order.queue");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.post.supplier", groupId = "inventories")
    public void processPostSupplier(String supplierJSON){
        logger.info("received content = '{}'", supplierJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            SupplierEntity supplierEntity = mapper.readValue(supplierJSON, SupplierEntity.class);
            SupplierEntity supplier = supplierService.addSupplier(supplierEntity);
            logger.info("Success process supplier name '{}', amount '{}' with topic '{}'", supplier.getSupplierName(), supplier.getSupplierPic(), "inventories.kafka.post.supplier");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }

    @KafkaListener(topics = "inventories.kafka.patch.supplier", groupId = "inventories")
    public void processPatchSupplier(String supplierJSON){
        logger.info("received content = '{}'", supplierJSON);
        try{
            ObjectMapper mapper = new ObjectMapper();
            SupplierEntity supplierEntity = mapper.readValue(supplierJSON, SupplierEntity.class);
            SupplierEntity supplier = supplierService.updateSupplier(supplierEntity);
            logger.info("Success process supplier name '{}', amount '{}' with topic '{}'", supplier.getSupplierName(), supplier.getSupplierPic(), "inventories.kafka.patch.supplier");
        } catch (Exception e){
            logger.error("An error occurred! '{}'", e.getMessage());
        }
    }
}