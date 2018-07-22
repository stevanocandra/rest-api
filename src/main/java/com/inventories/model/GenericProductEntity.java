package com.inventories.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "generic_product", schema = "public", catalog = "inventories")
public class GenericProductEntity {
    private int id;
    private String genericName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "generic_name")
    public String getGenericName() {
        return genericName;
    }

    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenericProductEntity that = (GenericProductEntity) o;
        return id == that.id &&
                Objects.equals(genericName, that.genericName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, genericName);
    }
}
