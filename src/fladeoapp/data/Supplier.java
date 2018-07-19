/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fladeoapp.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "supplier")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supplier.findAll", query = "SELECT s FROM Supplier s"),
    @NamedQuery(name = "Supplier.findByKdSupplier", query = "SELECT s FROM Supplier s WHERE s.kdSupplier = :kdSupplier"),
    @NamedQuery(name = "Supplier.findByNmSupplier", query = "SELECT s FROM Supplier s WHERE s.nmSupplier = :nmSupplier"),
    @NamedQuery(name = "Supplier.findByAlamat", query = "SELECT s FROM Supplier s WHERE s.alamat = :alamat"),
    @NamedQuery(name = "Supplier.findByTelepon", query = "SELECT s FROM Supplier s WHERE s.telepon = :telepon"),
    @NamedQuery(name = "Supplier.findByKota", query = "SELECT s FROM Supplier s WHERE s.kota = :kota")})
public class Supplier implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kd_Supplier")
    private String kdSupplier;
    @Basic(optional = false)
    @Column(name = "Nm_Supplier")
    private String nmSupplier;
    @Basic(optional = false)
    @Column(name = "Alamat")
    private String alamat;
    @Basic(optional = false)
    @Column(name = "Telepon")
    private String telepon;
    @Basic(optional = false)
    @Column(name = "Kota")
    private String kota;

    public Supplier() {
    }

    public Supplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
    }

    public Supplier(String kdSupplier, String nmSupplier, String alamat, String telepon, String kota) {
        this.kdSupplier = kdSupplier;
        this.nmSupplier = nmSupplier;
        this.alamat = alamat;
        this.telepon = telepon;
        this.kota = kota;
    }

    public String getKdSupplier() {
        return kdSupplier;
    }

    public void setKdSupplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
    }

    public String getNmSupplier() {
        return nmSupplier;
    }

    public void setNmSupplier(String nmSupplier) {
        this.nmSupplier = nmSupplier;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdSupplier != null ? kdSupplier.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supplier)) {
            return false;
        }
        Supplier other = (Supplier) object;
        if ((this.kdSupplier == null && other.kdSupplier != null) || (this.kdSupplier != null && !this.kdSupplier.equals(other.kdSupplier))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.Supplier[ kdSupplier=" + kdSupplier + " ]";
    }
    
}
