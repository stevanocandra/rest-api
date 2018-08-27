package com.inventories.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "product_lot", schema = "public", catalog = "inventories")
public class ProductLotEntity {
    private int id;
    private String lotCode;
    private Timestamp dateManufactured;
    private Timestamp dateExpiry;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "lot_code")
    public String getLotCode() {
        return lotCode;
    }

    public void setLotCode(String lotCode) {
        this.lotCode = lotCode;
    }

    @Basic
    @Column(name = "date_manufactured")
    public Timestamp getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(Timestamp dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    @Basic
    @Column(name = "date_expiry")
    public Timestamp getDateExpiry() {
        return dateExpiry;
    }

    public void setDateExpiry(Timestamp dateExpiry) {
        this.dateExpiry = dateExpiry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductLotEntity that = (ProductLotEntity) o;
        return id == that.id &&
                Objects.equals(lotCode, that.lotCode) &&
                Objects.equals(dateManufactured, that.dateManufactured) &&
                Objects.equals(dateExpiry, that.dateExpiry);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, lotCode, dateManufactured, dateExpiry);
    }
}
