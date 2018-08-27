package com.inventories.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ProductPriceRecordsEntityPK implements Serializable {
    private String productCode;
    private Timestamp fromDate;

    @Column(name = "product_code")
    @Id
    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    @Column(name = "from_date")
    @Id
    public Timestamp getFromDate() {
        return fromDate;
    }

    public void setFromDate(Timestamp fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceRecordsEntityPK that = (ProductPriceRecordsEntityPK) o;
        return Objects.equals(productCode, that.productCode) &&
                Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(productCode, fromDate);
    }
}
