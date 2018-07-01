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
@Table(name = "purchase_order_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrderDetail.findAll", query = "SELECT p FROM PurchaseOrderDetail p")
    , @NamedQuery(name = "PurchaseOrderDetail.findById", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.id = :id")
    , @NamedQuery(name = "PurchaseOrderDetail.findByNoPO", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.noPO = :noPO")
    , @NamedQuery(name = "PurchaseOrderDetail.findByArtBarang", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.artBarang = :artBarang")
    , @NamedQuery(name = "PurchaseOrderDetail.findBySize", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.size = :size")
    , @NamedQuery(name = "PurchaseOrderDetail.findByQtyOrder", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.qtyOrder = :qtyOrder")
    , @NamedQuery(name = "PurchaseOrderDetail.findByHargaBeli", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.hargaBeli = :hargaBeli")
    , @NamedQuery(name = "PurchaseOrderDetail.findByHargaJual", query = "SELECT p FROM PurchaseOrderDetail p WHERE p.hargaJual = :hargaJual")})
public class PurchaseOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "No_PO")
    private String noPO;
    @Basic(optional = false)
    @Column(name = "Art_Barang")
    private String artBarang;
    @Basic(optional = false)
    @Column(name = "Size")
    private String size;
    @Basic(optional = false)
    @Column(name = "Qty_Order")
    private int qtyOrder;
    @Basic(optional = false)
    @Column(name = "Harga_Beli")
    private double hargaBeli;
    @Basic(optional = false)
    @Column(name = "Harga_Jual")
    private double hargaJual;

    public PurchaseOrderDetail() {
    }

    public PurchaseOrderDetail(Integer id) {
        this.id = id;
    }

    public PurchaseOrderDetail(Integer id, String noPO, String artBarang, String size, int qtyOrder, double hargaBeli, double hargaJual) {
        this.id = id;
        this.noPO = noPO;
        this.artBarang = artBarang;
        this.size = size;
        this.qtyOrder = qtyOrder;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoPO() {
        return noPO;
    }

    public void setNoPO(String noPO) {
        this.noPO = noPO;
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

    public int getQtyOrder() {
        return qtyOrder;
    }

    public void setQtyOrder(int qtyOrder) {
        this.qtyOrder = qtyOrder;
    }

    public double getHargaBeli() {
        return hargaBeli;
    }

    public void setHargaBeli(double hargaBeli) {
        this.hargaBeli = hargaBeli;
    }

    public double getHargaJual() {
        return hargaJual;
    }

    public void setHargaJual(double hargaJual) {
        this.hargaJual = hargaJual;
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
        if (!(object instanceof PurchaseOrderDetail)) {
            return false;
        }
        PurchaseOrderDetail other = (PurchaseOrderDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.PurchaseOrderDetail[ id=" + id + " ]";
    }
    
}
