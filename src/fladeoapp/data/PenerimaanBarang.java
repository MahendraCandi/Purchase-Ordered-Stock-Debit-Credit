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
 * @author 0085
 */
@Entity
@Table(name = "penerimaan_barang")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PenerimaanBarang.findAll", query = "SELECT p FROM PenerimaanBarang p")
    , @NamedQuery(name = "PenerimaanBarang.findByNoTandaTerima", query = "SELECT p FROM PenerimaanBarang p WHERE p.noTandaTerima = :noTandaTerima")
    , @NamedQuery(name = "PenerimaanBarang.findByNoPO", query = "SELECT p FROM PenerimaanBarang p WHERE p.noPO = :noPO")
    , @NamedQuery(name = "PenerimaanBarang.findByTglTerimaBarang", query = "SELECT p FROM PenerimaanBarang p WHERE p.tglTerimaBarang = :tglTerimaBarang")})
public class PenerimaanBarang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "No_Tanda_Terima")
    private String noTandaTerima;
    @Basic(optional = false)
    @Column(name = "No_PO")
    private String noPO;
    @Basic(optional = false)
    @Column(name = "Tgl_Terima_Barang")
    @Temporal(TemporalType.DATE)
    private Date tglTerimaBarang;

    public PenerimaanBarang() {
    }

    public PenerimaanBarang(String noTandaTerima) {
        this.noTandaTerima = noTandaTerima;
    }

    public PenerimaanBarang(String noTandaTerima, String noPO, Date tglTerimaBarang) {
        this.noTandaTerima = noTandaTerima;
        this.noPO = noPO;
        this.tglTerimaBarang = tglTerimaBarang;
    }

    public String getNoTandaTerima() {
        return noTandaTerima;
    }

    public void setNoTandaTerima(String noTandaTerima) {
        this.noTandaTerima = noTandaTerima;
    }

    public String getNoPO() {
        return noPO;
    }

    public void setNoPO(String noPO) {
        this.noPO = noPO;
    }

    public Date getTglTerimaBarang() {
        return tglTerimaBarang;
    }

    public void setTglTerimaBarang(Date tglTerimaBarang) {
        this.tglTerimaBarang = tglTerimaBarang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noTandaTerima != null ? noTandaTerima.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PenerimaanBarang)) {
            return false;
        }
        PenerimaanBarang other = (PenerimaanBarang) object;
        if ((this.noTandaTerima == null && other.noTandaTerima != null) || (this.noTandaTerima != null && !this.noTandaTerima.equals(other.noTandaTerima))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.PenerimaanBarang[ noTandaTerima=" + noTandaTerima + " ]";
    }
    
}
