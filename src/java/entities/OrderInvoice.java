/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fred
 */
@Entity
@Table(name = "order_invoice")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderInvoice.findAll", query = "SELECT o FROM OrderInvoice o"),
    @NamedQuery(name = "OrderInvoice.findByInvoiceId", query = "SELECT o FROM OrderInvoice o WHERE o.invoiceId = :invoiceId"),
    @NamedQuery(name = "OrderInvoice.findByQuantity", query = "SELECT o FROM OrderInvoice o WHERE o.quantity = :quantity")})
public class OrderInvoice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @Column(name = "quantity")
    private Integer quantity;
    @JoinColumn(name = "discount_code", referencedColumnName = "discount_code")
    @ManyToOne
    private Discount discountCode;
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @ManyToOne(optional = false)
    private Orders orderId;
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    @ManyToOne(optional = false)
    private Products productId;

    public OrderInvoice() {
    }

    public OrderInvoice(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Discount getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Discount discountCode) {
        this.discountCode = discountCode;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceId != null ? invoiceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderInvoice)) {
            return false;
        }
        OrderInvoice other = (OrderInvoice) object;
        if ((this.invoiceId == null && other.invoiceId != null) || (this.invoiceId != null && !this.invoiceId.equals(other.invoiceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderInvoice[ invoiceId=" + invoiceId + " ]";
    }
    
}
