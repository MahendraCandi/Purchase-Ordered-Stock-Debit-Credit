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
@Table(name = "detail_purchase_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailPurchaseOrder.findAll", query = "SELECT d FROM DetailPurchaseOrder d")
    , @NamedQuery(name = "DetailPurchaseOrder.findById", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.id = :id")
    , @NamedQuery(name = "DetailPurchaseOrder.findByNoPO", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.noPO = :noPO")
    , @NamedQuery(name = "DetailPurchaseOrder.findByKdBarang", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.kdBarang = :kdBarang")
    , @NamedQuery(name = "DetailPurchaseOrder.findByQtyOrder", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.qtyOrder = :qtyOrder")
    , @NamedQuery(name = "DetailPurchaseOrder.findByHargaBeli", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.hargaBeli = :hargaBeli")
    , @NamedQuery(name = "DetailPurchaseOrder.findByHargaJual", query = "SELECT d FROM DetailPurchaseOrder d WHERE d.hargaJual = :hargaJual")})
public class DetailPurchaseOrder implements Serializable {

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
    @Column(name = "Kd_Barang")
    private String kdBarang;
    @Basic(optional = false)
    @Column(name = "Qty_Order")
    private int qtyOrder;
    @Basic(optional = false)
    @Column(name = "Harga_Beli")
    private double hargaBeli;
    @Basic(optional = false)
    @Column(name = "Harga_Jual")
    private double hargaJual;

    public DetailPurchaseOrder() {
    }

    public DetailPurchaseOrder(Integer id) {
        this.id = id;
    }

    public DetailPurchaseOrder(Integer id, String noPO, String kdBarang, int qtyOrder, double hargaBeli, double hargaJual) {
        this.id = id;
        this.noPO = noPO;
        this.kdBarang = kdBarang;
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

    public String getKdBarang() {
        return kdBarang;
    }

    public void setKdBarang(String kdBarang) {
        this.kdBarang = kdBarang;
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
        if (!(object instanceof DetailPurchaseOrder)) {
            return false;
        }
        DetailPurchaseOrder other = (DetailPurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.DetailPurchaseOrder[ id=" + id + " ]";
    }
    
}
