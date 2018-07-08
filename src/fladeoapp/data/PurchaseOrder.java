/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fladeoapp.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Candi-PC
 */
@Entity
@Table(name = "purchase_order")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseOrder.findAll", query = "SELECT p FROM PurchaseOrder p"),
    @NamedQuery(name = "PurchaseOrder.findByNoPO", query = "SELECT p FROM PurchaseOrder p WHERE p.noPO = :noPO"),
    @NamedQuery(name = "PurchaseOrder.findByTglPO", query = "SELECT p FROM PurchaseOrder p WHERE p.tglPO = :tglPO"),
    @NamedQuery(name = "PurchaseOrder.findByTglKirim", query = "SELECT p FROM PurchaseOrder p WHERE p.tglKirim = :tglKirim"),
    @NamedQuery(name = "PurchaseOrder.findByKdSupplier", query = "SELECT p FROM PurchaseOrder p WHERE p.kdSupplier = :kdSupplier"),
    @NamedQuery(name = "PurchaseOrder.findByTotalQty", query = "SELECT p FROM PurchaseOrder p WHERE p.totalQty = :totalQty"),
    @NamedQuery(name = "PurchaseOrder.findByUsername", query = "SELECT p FROM PurchaseOrder p WHERE p.username = :username")})
public class PurchaseOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "No_PO")
    private String noPO;
    @Basic(optional = false)
    @Column(name = "Tgl_PO")
    @Temporal(TemporalType.DATE)
    private Date tglPO;
    @Basic(optional = false)
    @Column(name = "Tgl_Kirim")
    @Temporal(TemporalType.DATE)
    private Date tglKirim;
    @Basic(optional = false)
    @Column(name = "Kd_Supplier")
    private String kdSupplier;
    @Basic(optional = false)
    @Column(name = "Total_Qty")
    private int totalQty;
    @Basic(optional = false)
    @Column(name = "Username")
    private String username;

    public PurchaseOrder() {
    }

    public PurchaseOrder(String noPO) {
        this.noPO = noPO;
    }

    public PurchaseOrder(String noPO, Date tglPO, Date tglKirim, String kdSupplier, int totalQty, String username) {
        this.noPO = noPO;
        this.tglPO = tglPO;
        this.tglKirim = tglKirim;
        this.kdSupplier = kdSupplier;
        this.totalQty = totalQty;
        this.username = username;
    }

    public String getNoPO() {
        return noPO;
    }

    public void setNoPO(String noPO) {
        this.noPO = noPO;
    }

    public Date getTglPO() {
        return tglPO;
    }

    public void setTglPO(Date tglPO) {
        this.tglPO = tglPO;
    }

    public Date getTglKirim() {
        return tglKirim;
    }

    public void setTglKirim(Date tglKirim) {
        this.tglKirim = tglKirim;
    }

    public String getKdSupplier() {
        return kdSupplier;
    }

    public void setKdSupplier(String kdSupplier) {
        this.kdSupplier = kdSupplier;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPO != null ? noPO.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.noPO == null && other.noPO != null) || (this.noPO != null && !this.noPO.equals(other.noPO))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.PurchaseOrder[ noPO=" + noPO + " ]";
    }
    
}
