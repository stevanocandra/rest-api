package com.inventories.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "supplier", schema = "public", catalog = "inventories")
public class SupplierEntity {
    private int id;
    private String supplierCode;
    private String supplierName;
    private String supplierPic;
    private String supplierContactNo;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "supplier_code")
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Basic
    @Column(name = "supplier_name")
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "supplier_pic")
    public String getSupplierPic() {
        return supplierPic;
    }

    public void setSupplierPic(String supplierPic) {
        this.supplierPic = supplierPic;
    }

    @Basic
    @Column(name = "supplier_contact_no")
    public String getSupplierContactNo() {
        return supplierContactNo;
    }

    public void setSupplierContactNo(String supplierContactNo) {
        this.supplierContactNo = supplierContactNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SupplierEntity that = (SupplierEntity) o;
        return id == that.id &&
                Objects.equals(supplierCode, that.supplierCode) &&
                Objects.equals(supplierName, that.supplierName) &&
                Objects.equals(supplierPic, that.supplierPic) &&
                Objects.equals(supplierContactNo, that.supplierContactNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, supplierCode, supplierName, supplierPic, supplierContactNo);
    }
}
