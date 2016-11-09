/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fred
 */
@Entity
@Table(name = "product_category")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductCategory.findAll", query = "SELECT p FROM ProductCategory p"),
    @NamedQuery(name = "ProductCategory.findByProductCatCode", query = "SELECT p FROM ProductCategory p WHERE p.productCatCode = :productCatCode"),
    @NamedQuery(name = "ProductCategory.findByProductCatDesc", query = "SELECT p FROM ProductCategory p WHERE p.productCatDesc = :productCatDesc")})
public class ProductCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_cat_code")
    private Integer productCatCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "product_cat_desc")
    private String productCatDesc;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "productCategory")
    private Products products;

    public ProductCategory() {
    }

    public ProductCategory(Integer productCatCode) {
        this.productCatCode = productCatCode;
    }

    public ProductCategory(Integer productCatCode, String productCatDesc) {
        this.productCatCode = productCatCode;
        this.productCatDesc = productCatDesc;
    }

    public Integer getProductCatCode() {
        return productCatCode;
    }

    public void setProductCatCode(Integer productCatCode) {
        this.productCatCode = productCatCode;
    }

    public String getProductCatDesc() {
        return productCatDesc;
    }

    public void setProductCatDesc(String productCatDesc) {
        this.productCatDesc = productCatDesc;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productCatCode != null ? productCatCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductCategory)) {
            return false;
        }
        ProductCategory other = (ProductCategory) object;
        if ((this.productCatCode == null && other.productCatCode != null) || (this.productCatCode != null && !this.productCatCode.equals(other.productCatCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductCategory[ productCatCode=" + productCatCode + " ]";
    }
    
}
