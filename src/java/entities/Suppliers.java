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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fred
 */
@Entity
@Table(name = "suppliers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Suppliers.findAll", query = "SELECT s FROM Suppliers s"),
    @NamedQuery(name = "Suppliers.findBySupplierCode", query = "SELECT s FROM Suppliers s WHERE s.supplierCode = :supplierCode"),
    @NamedQuery(name = "Suppliers.findBySupplierName", query = "SELECT s FROM Suppliers s WHERE s.supplierName = :supplierName")})
public class Suppliers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "supplier_code")
    private Integer supplierCode;
    @Size(max = 45)
    @Column(name = "supplier_name")
    private String supplierName;
    @JoinColumn(name = "region", referencedColumnName = "region_code")
    @ManyToOne
    private SupplierRegion region;
    @OneToMany(mappedBy = "supplierCode")
    private List<Products> productsList;

    public Suppliers() {
    }

    public Suppliers(Integer supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(Integer supplierCode) {
        this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public SupplierRegion getRegion() {
        return region;
    }

    public void setRegion(SupplierRegion region) {
        this.region = region;
    }

    @XmlTransient
    public List<Products> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<Products> productsList) {
        this.productsList = productsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (supplierCode != null ? supplierCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Suppliers)) {
            return false;
        }
        Suppliers other = (Suppliers) object;
        if ((this.supplierCode == null && other.supplierCode != null) || (this.supplierCode != null && !this.supplierCode.equals(other.supplierCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Suppliers[ supplierCode=" + supplierCode + " ]";
    }
    
}
