package com.inventories.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "public", catalog = "inventories")
public class CategoryEntity {
    private int id;
    private String categoryCode;
    private String categoryName;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "category_code")
    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    @Basic
    @Column(name = "category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(categoryCode, that.categoryCode) &&
                Objects.equals(categoryName, that.categoryName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, categoryCode, categoryName);
    }
}
