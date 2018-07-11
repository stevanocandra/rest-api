package com.inventories.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "inventories")
public class ProductEntity {
    private int id;
    private String productCode;
    private String productName;
    private String productDescription;
    private BigDecimal productPrice;
    private Boolean hasLot;
    private String singleUnitProductCode;
    private String lotInformation;
    private Boolean isActive;
    private Boolean deleted;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Basic
    @Column(name = "product_name")
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Basic
    @Column(name = "product_description")
    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    @Basic
    @Column(name = "product_price")
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Basic
    @Column(name = "has_lot")
    public Boolean getHasLot() {
        return hasLot;
    }

    public void setHasLot(Boolean hasLot) {
        this.hasLot = hasLot;
    }

    @Basic
    @Column(name = "single_unit_product_code")
    public String getSingleUnitProductCode() {
        return singleUnitProductCode;
    }

    public void setSingleUnitProductCode(String singleUnitProductCode) {
        this.singleUnitProductCode = singleUnitProductCode;
    }

    @Basic
    @Column(name = "lot_information")
    public String getLotInformation() {
        return lotInformation;
    }

    public void setLotInformation(String lotInformation) {
        this.lotInformation = lotInformation;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id == that.id &&
                Objects.equals(productCode, that.productCode) &&
                Objects.equals(productName, that.productName) &&
                Objects.equals(productDescription, that.productDescription) &&
                Objects.equals(productPrice, that.productPrice) &&
                Objects.equals(hasLot, that.hasLot) &&
                Objects.equals(singleUnitProductCode, that.singleUnitProductCode) &&
                Objects.equals(lotInformation, that.lotInformation) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(deleted, that.deleted);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, productCode, productName, productDescription, productPrice, hasLot, singleUnitProductCode, lotInformation, isActive, deleted);
    }
}
