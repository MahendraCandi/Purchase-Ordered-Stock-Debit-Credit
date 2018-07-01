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
@Table(name = "pelunasan_pembayaran")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PelunasanPembayaran.findAll", query = "SELECT p FROM PelunasanPembayaran p")
    , @NamedQuery(name = "PelunasanPembayaran.findByNoPembayaran", query = "SELECT p FROM PelunasanPembayaran p WHERE p.noPembayaran = :noPembayaran")
    , @NamedQuery(name = "PelunasanPembayaran.findByJthTempo", query = "SELECT p FROM PelunasanPembayaran p WHERE p.jthTempo = :jthTempo")
    , @NamedQuery(name = "PelunasanPembayaran.findByTotalBayar", query = "SELECT p FROM PelunasanPembayaran p WHERE p.totalBayar = :totalBayar")})
public class PelunasanPembayaran implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "No_Pembayaran")
    private String noPembayaran;
    @Basic(optional = false)
    @Column(name = "Jth_Tempo")
    @Temporal(TemporalType.DATE)
    private Date jthTempo;
    @Basic(optional = false)
    @Column(name = "Total_Bayar")
    private double totalBayar;

    public PelunasanPembayaran() {
    }

    public PelunasanPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public PelunasanPembayaran(String noPembayaran, Date jthTempo, double totalBayar) {
        this.noPembayaran = noPembayaran;
        this.jthTempo = jthTempo;
        this.totalBayar = totalBayar;
    }

    public String getNoPembayaran() {
        return noPembayaran;
    }

    public void setNoPembayaran(String noPembayaran) {
        this.noPembayaran = noPembayaran;
    }

    public Date getJthTempo() {
        return jthTempo;
    }

    public void setJthTempo(Date jthTempo) {
        this.jthTempo = jthTempo;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(double totalBayar) {
        this.totalBayar = totalBayar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noPembayaran != null ? noPembayaran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PelunasanPembayaran)) {
            return false;
        }
        PelunasanPembayaran other = (PelunasanPembayaran) object;
        if ((this.noPembayaran == null && other.noPembayaran != null) || (this.noPembayaran != null && !this.noPembayaran.equals(other.noPembayaran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.PelunasanPembayaran[ noPembayaran=" + noPembayaran + " ]";
    }
    
}
