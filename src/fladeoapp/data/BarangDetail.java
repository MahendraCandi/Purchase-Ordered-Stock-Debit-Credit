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
@Table(name = "barang_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BarangDetail.findAll", query = "SELECT b FROM BarangDetail b")
    , @NamedQuery(name = "BarangDetail.findById", query = "SELECT b FROM BarangDetail b WHERE b.id = :id")
    , @NamedQuery(name = "BarangDetail.findByArtBarang", query = "SELECT b FROM BarangDetail b WHERE b.artBarang = :artBarang")
    , @NamedQuery(name = "BarangDetail.findBySize", query = "SELECT b FROM BarangDetail b WHERE b.size = :size")
    , @NamedQuery(name = "BarangDetail.findByQtyBarang", query = "SELECT b FROM BarangDetail b WHERE b.qtyBarang = :qtyBarang")})
public class BarangDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "Art_Barang")
    private String artBarang;
    @Basic(optional = false)
    @Column(name = "Size")
    private String size;
    @Basic(optional = false)
    @Column(name = "Qty_Barang")
    private int qtyBarang;

    public BarangDetail() {
    }

    public BarangDetail(Integer id) {
        this.id = id;
    }

    public BarangDetail(Integer id, String artBarang, String size, int qtyBarang) {
        this.id = id;
        this.artBarang = artBarang;
        this.size = size;
        this.qtyBarang = qtyBarang;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtBarang() {
        return artBarang;
    }

    public void setArtBarang(String artBarang) {
        this.artBarang = artBarang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQtyBarang() {
        return qtyBarang;
    }

    public void setQtyBarang(int qtyBarang) {
        this.qtyBarang = qtyBarang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BarangDetail)) {
            return false;
        }
        BarangDetail other = (BarangDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.BarangDetail[ id=" + id + " ]";
    }
    
}
