package com.inventories.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product_price_records", schema = "public", catalog = "inventories")
@IdClass(ProductPriceRecordsEntityPK.class)
public class ProductPriceRecordsEntity {
    private String productCode;
    private Timestamp fromDate;
    private BigDecimal productPrice;

    @Id
    @Column(name = "product_code")
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Id
    @Column(name = "from_date")
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Basic
    @Column(name = "product_price")
    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceRecordsEntity that = (ProductPriceRecordsEntity) o;
        return Objects.equals(productCode, that.productCode) &&
                Objects.equals(fromDate, that.fromDate) &&
                Objects.equals(productPrice, that.productPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productCode, fromDate, productPrice);
    }
}
