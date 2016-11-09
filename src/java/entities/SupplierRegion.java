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
@Table(name = "supplier_region")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SupplierRegion.findAll", query = "SELECT s FROM SupplierRegion s"),
    @NamedQuery(name = "SupplierRegion.findByRegionCode", query = "SELECT s FROM SupplierRegion s WHERE s.regionCode = :regionCode"),
    @NamedQuery(name = "SupplierRegion.findByRegionDesc", query = "SELECT s FROM SupplierRegion s WHERE s.regionDesc = :regionDesc")})
public class SupplierRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "region_code")
    private Integer regionCode;
    @Size(max = 45)
    @Column(name = "region_desc")
    private String regionDesc;
    @OneToMany(mappedBy = "region")
    private List<Suppliers> suppliersList;

    public SupplierRegion() {
    }

    public SupplierRegion(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionDesc() {
        return regionDesc;
    }

    public void setRegionDesc(String regionDesc) {
        this.regionDesc = regionDesc;
    }

    @XmlTransient
    public List<Suppliers> getSuppliersList() {
        return suppliersList;
    }

    public void setSuppliersList(List<Suppliers> suppliersList) {
        this.suppliersList = suppliersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (regionCode != null ? regionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SupplierRegion)) {
            return false;
        }
        SupplierRegion other = (SupplierRegion) object;
        if ((this.regionCode == null && other.regionCode != null) || (this.regionCode != null && !this.regionCode.equals(other.regionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.SupplierRegion[ regionCode=" + regionCode + " ]";
    }
    
}
