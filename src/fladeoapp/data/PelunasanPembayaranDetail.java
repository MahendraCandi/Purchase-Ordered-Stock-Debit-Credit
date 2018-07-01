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
@Table(name = "pelunasan_pembayaran_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PelunasanPembayaranDetail.findAll", query = "SELECT p FROM PelunasanPembayaranDetail p")
    , @NamedQuery(name = "PelunasanPembayaranDetail.findById", query = "SELECT p FROM PelunasanPembayaranDetail p WHERE p.id = :id")
    , @NamedQuery(name = "PelunasanPembayaranDetail.findByNoPembayaran", query = "SELECT p FROM PelunasanPembayaranDetail p WHERE p.noPembayaran = :noPembayaran")
    , @NamedQuery(name = "PelunasanPembayaranDetail.findByNoTransaksi", query = "SELECT p FROM PelunasanPembayaranDetail p WHERE p.noTransaksi = :noTransaksi")})
public class PelunasanPembayaranDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "No_Pembayaran")
    private String noPembayaran;
    @Basic(optional = false)
    @Column(name = "No_Transaksi")
    private String noTransaksi;

    public PelunasanPembayaranDetail() {
    }

    public PelunasanPembayaranDetail(Integer id) {
        this.id = id;
    }

    public PelunasanPembayaranDetail(Integer id, String noPembayaran, String noTransaksi) {
        this.id = id;
        this.noPembayaran = noPembayaran;
        this.noTransaksi = noTransaksi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
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
        if (!(object instanceof PelunasanPembayaranDetail)) {
            return false;
        }
        PelunasanPembayaranDetail other = (PelunasanPembayaranDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.PelunasanPembayaranDetail[ id=" + id + " ]";
    }
    
}
