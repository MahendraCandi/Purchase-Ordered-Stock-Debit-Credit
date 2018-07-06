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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 0085
 */
@Entity
@Table(name = "barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b")
    , @NamedQuery(name = "Barang.findByIdBarang", query = "SELECT b FROM Barang b WHERE b.idBarang = :idBarang")
    , @NamedQuery(name = "Barang.findByKdBarang", query = "SELECT b FROM Barang b WHERE b.kdBarang = :kdBarang")
    , @NamedQuery(name = "Barang.findByJenisBarang", query = "SELECT b FROM Barang b WHERE b.jenisBarang = :jenisBarang")
    , @NamedQuery(name = "Barang.findByHrgBeli", query = "SELECT b FROM Barang b WHERE b.hrgBeli = :hrgBeli")
    , @NamedQuery(name = "Barang.findByHrgJual", query = "SELECT b FROM Barang b WHERE b.hrgJual = :hrgJual")
    , @NamedQuery(name = "Barang.findByKdSupplier", query = "SELECT b FROM Barang b WHERE b.kdSupplier = :kdSupplier")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Barang")
    private Integer idBarang;
    @Basic(optional = false)
    @Column(name = "Kd_Barang")
    private String kdBarang;
    @Basic(optional = false)
    @Column(name = "Jenis_Barang")
    private String jenisBarang;
    @Basic(optional = false)
    @Column(name = "Hrg_Beli")
    private double hrgBeli;
    @Basic(optional = false)
    @Column(name = "Hrg_Jual")
    private double hrgJual;
    @Basic(optional = false)
    @Column(name = "Kd_Supplier")
    private String kdSupplier;

    public Barang() {
    }

    public Barang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public Barang(Integer idBarang, String kdBarang, String jenisBarang, double hrgBeli, double hrgJual, String kdSupplier) {
        this.idBarang = idBarang;
        this.kdBarang = kdBarang;
        this.jenisBarang = jenisBarang;
        this.hrgBeli = hrgBeli;
        this.hrgJual = hrgJual;
        this.kdSupplier = kdSupplier;
    }

    public Integer getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Integer idBarang) {
        this.idBarang = idBarang;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getJenisBarang() {
        return jenisBarang;
    }

    public void setJenisBarang(String jenisBarang) {
        this.jenisBarang = jenisBarang;
    }

    public double getHrgBeli() {
        return hrgBeli;
    }

    public void setHrgBeli(double hrgBeli) {
        this.hrgBeli = hrgBeli;
    }

    public double getHrgJual() {
        return hrgJual;
    }

    public void setHrgJual(double hrgJual) {
        this.hrgJual = hrgJual;
    }

    public String getKdSupplier() {
        return kdSupplier;
    }

    public void setKdSupplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBarang != null ? idBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.idBarang == null && other.idBarang != null) || (this.idBarang != null && !this.idBarang.equals(other.idBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.Barang[ idBarang=" + idBarang + " ]";
    }
    
}
