package com.inventories.model;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.Objects;

@Entity
@RestResource
@Table(name = "brand_manufacturer", schema = "public", catalog = "inventories")
public class BrandManufacturerEntity {
    private int id;
    private String manufacturerName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "manufacturer_name")
    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandManufacturerEntity that = (BrandManufacturerEntity) o;
        return id == that.id &&
                Objects.equals(manufacturerName, that.manufacturerName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, manufacturerName);
    }
}
