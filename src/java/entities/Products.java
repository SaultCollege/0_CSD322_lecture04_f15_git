/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fred
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByProductId", query = "SELECT p FROM Products p WHERE p.productId = :productId"),
    @NamedQuery(name = "Products.findByProductName", query = "SELECT p FROM Products p WHERE p.productName = :productName"),
    @NamedQuery(name = "Products.findByUnitPrice", query = "SELECT p FROM Products p WHERE p.unitPrice = :unitPrice"),
    @NamedQuery(name = "Products.findByUnitsOnHand", query = "SELECT p FROM Products p WHERE p.unitsOnHand = :unitsOnHand"),
    @NamedQuery(name = "Products.findByUnitsOnReorder", query = "SELECT p FROM Products p WHERE p.unitsOnReorder = :unitsOnReorder"),
    @NamedQuery(name = "Products.findByReorderLvl", query = "SELECT p FROM Products p WHERE p.reorderLvl = :reorderLvl"),
    @NamedQuery(name = "Products.findByDiscontinuedStatus", query = "SELECT p FROM Products p WHERE p.discontinuedStatus = :discontinuedStatus")})
public class Products implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "product_id")
    private Integer productId;
    @Size(max = 30)
    @Column(name = "product_name")
    private String productName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "unit_price")
    private Float unitPrice;
    @Column(name = "units_on_hand")
    private Integer unitsOnHand;
    @Column(name = "units_on_reorder")
    private Integer unitsOnReorder;
    @Column(name = "reorder_lvl")
    private Integer reorderLvl;
    @Column(name = "discontinued_status")
    private Boolean discontinuedStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId")
    private List<OrderInvoice> orderInvoiceList;
    @JoinColumn(name = "product_id", referencedColumnName = "product_cat_code", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private ProductCategory productCategory;
    @JoinColumn(name = "supplier_code", referencedColumnName = "supplier_code")
    @ManyToOne
    private Suppliers supplierCode;

    public Products() {
    }

    public Products(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getUnitsOnHand() {
        return unitsOnHand;
    }

    public void setUnitsOnHand(Integer unitsOnHand) {
        this.unitsOnHand = unitsOnHand;
    }

    public Integer getUnitsOnReorder() {
        return unitsOnReorder;
    }

    public void setUnitsOnReorder(Integer unitsOnReorder) {
        this.unitsOnReorder = unitsOnReorder;
    }

    public Integer getReorderLvl() {
        return reorderLvl;
    }

    public void setReorderLvl(Integer reorderLvl) {
        this.reorderLvl = reorderLvl;
    }

    public Boolean getDiscontinuedStatus() {
        return discontinuedStatus;
    }

    public void setDiscontinuedStatus(Boolean discontinuedStatus) {
        this.discontinuedStatus = discontinuedStatus;
    }

    @XmlTransient
    public List<OrderInvoice> getOrderInvoiceList() {
        return orderInvoiceList;
    }

    public void setOrderInvoiceList(List<OrderInvoice> orderInvoiceList) {
        this.orderInvoiceList = orderInvoiceList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Suppliers getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(Suppliers supplierCode) {
        this.supplierCode = supplierCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productId != null ? productId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.productId == null && other.productId != null) || (this.productId != null && !this.productId.equals(other.productId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Products[ productId=" + productId + " ]";
    }
    
}
