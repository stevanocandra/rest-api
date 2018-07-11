package com.inventories.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "stock", schema = "public", catalog = "inventories")
public class StockEntity {
    private int id;
    private Integer productId;
    private Integer rackId;
    private Long quantityInHand;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @JoinColumn(name = "product_id")
    public Integer getProductId() { return productId; }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "rack_id")
    public Integer getRackId() {
        return rackId;
    }

    public void setRackId(Integer rackId) {
        this.rackId = rackId;
    }

    @Basic
    @Column(name = "quantity_in_hand")
    public Long getQuantityInHand() {
        return quantityInHand;
    }

    public void setQuantityInHand(Long quantityInHand) {
        this.quantityInHand = quantityInHand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockEntity that = (StockEntity) o;
        return id == that.id &&
                Objects.equals(rackId, that.rackId) &&
                Objects.equals(quantityInHand, that.quantityInHand);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, rackId, quantityInHand);
    }
}
