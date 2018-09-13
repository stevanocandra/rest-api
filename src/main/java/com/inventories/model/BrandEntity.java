package com.inventories.model;
import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "brand", schema = "public", catalog = "inventories")
public class BrandEntity {
    private int id;
    private String manufacturerId;
    private String brandCode;
    private String brandName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "manufacturer_id")
    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Basic
    @Column(name = "brand_code")
    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    @Basic
    @Column(name = "brand_name")
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandEntity that = (BrandEntity) o;
        return id == that.id &&
                Objects.equals(manufacturerId, that.manufacturerId) &&
                Objects.equals(brandCode, that.brandCode) &&
                Objects.equals(brandName, that.brandName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, manufacturerId, brandCode, brandName);
    }
}
