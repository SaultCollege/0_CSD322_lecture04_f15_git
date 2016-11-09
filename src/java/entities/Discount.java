/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fred
 */
@Entity
@Table(name = "discount")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Discount.findAll", query = "SELECT d FROM Discount d"),
    @NamedQuery(name = "Discount.findByDiscountCode", query = "SELECT d FROM Discount d WHERE d.discountCode = :discountCode"),
    @NamedQuery(name = "Discount.findByDiscountPercentage", query = "SELECT d FROM Discount d WHERE d.discountPercentage = :discountPercentage")})
public class Discount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount_code")
    private Integer discountCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "discount_percentage")
    private float discountPercentage;
    @OneToMany(mappedBy = "discountCode")
    private List<OrderInvoice> orderInvoiceList;

    public Discount() {
    }

    public Discount(Integer discountCode) {
        this.discountCode = discountCode;
    }

    public Discount(Integer discountCode, float discountPercentage) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
    }

    public Integer getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Integer discountCode) {
        this.discountCode = discountCode;
    }

    public float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @XmlTransient
    public List<OrderInvoice> getOrderInvoiceList() {
        return orderInvoiceList;
    }

    public void setOrderInvoiceList(List<OrderInvoice> orderInvoiceList) {
        this.orderInvoiceList = orderInvoiceList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (discountCode != null ? discountCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Discount)) {
            return false;
        }
        Discount other = (Discount) object;
        if ((this.discountCode == null && other.discountCode != null) || (this.discountCode != null && !this.discountCode.equals(other.discountCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Discount[ discountCode=" + discountCode + " ]";
    }
    
}
