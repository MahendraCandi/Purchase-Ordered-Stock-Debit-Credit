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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b"),
    @NamedQuery(name = "Barang.findByKdBarang", query = "SELECT b FROM Barang b WHERE b.kdBarang = :kdBarang"),
    @NamedQuery(name = "Barang.findByNamaBarang", query = "SELECT b FROM Barang b WHERE b.namaBarang = :namaBarang"),
    @NamedQuery(name = "Barang.findByHrgBeli", query = "SELECT b FROM Barang b WHERE b.hrgBeli = :hrgBeli"),
    @NamedQuery(name = "Barang.findByHrgJual", query = "SELECT b FROM Barang b WHERE b.hrgJual = :hrgJual"),
    @NamedQuery(name = "Barang.findByKdSupplier", query = "SELECT b FROM Barang b WHERE b.kdSupplier = :kdSupplier"),
    @NamedQuery(name = "Barang.findByWarna", query = "SELECT b FROM Barang b WHERE b.warna = :warna"),
    @NamedQuery(name = "Barang.findBySize", query = "SELECT b FROM Barang b WHERE b.size = :size")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kd_Barang")
    private String kdBarang;
    @Basic(optional = false)
    @Column(name = "Nama_Barang")
    private String namaBarang;
    @Basic(optional = false)
    @Column(name = "Hrg_Beli")
    private double hrgBeli;
    @Basic(optional = false)
    @Column(name = "Hrg_Jual")
    private double hrgJual;
    @Basic(optional = false)
    @Column(name = "Kd_Supplier")
    private String kdSupplier;
    @Basic(optional = false)
    @Column(name = "Warna")
    private String warna;
    @Basic(optional = false)
    @Column(name = "size")
    private String size;
    @Lob
    @Column(name = "foto")
    private byte[] foto;

    public Barang() {
    }

    public Barang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public Barang(String kdBarang, String namaBarang, double hrgBeli, double hrgJual, String kdSupplier, String warna, String size) {
        this.kdBarang = kdBarang;
        this.namaBarang = namaBarang;
        this.hrgBeli = hrgBeli;
        this.hrgJual = hrgJual;
        this.kdSupplier = kdSupplier;
        this.warna = warna;
        this.size = size;
    }

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
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

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdBarang != null ? kdBarang.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.kdBarang == null && other.kdBarang != null) || (this.kdBarang != null && !this.kdBarang.equals(other.kdBarang))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.Barang[ kdBarang=" + kdBarang + " ]";
    }
    
}
