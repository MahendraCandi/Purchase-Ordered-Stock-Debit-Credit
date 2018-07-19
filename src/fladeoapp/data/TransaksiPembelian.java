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
@Table(name = "transaksi_pembelian")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TransaksiPembelian.findAll", query = "SELECT t FROM TransaksiPembelian t"),
    @NamedQuery(name = "TransaksiPembelian.findByNoTransaksi", query = "SELECT t FROM TransaksiPembelian t WHERE t.noTransaksi = :noTransaksi"),
    @NamedQuery(name = "TransaksiPembelian.findByNoTandaTerima", query = "SELECT t FROM TransaksiPembelian t WHERE t.noTandaTerima = :noTandaTerima"),
    @NamedQuery(name = "TransaksiPembelian.findByNoInvoice", query = "SELECT t FROM TransaksiPembelian t WHERE t.noInvoice = :noInvoice"),
    @NamedQuery(name = "TransaksiPembelian.findByTotalTransaksi", query = "SELECT t FROM TransaksiPembelian t WHERE t.totalTransaksi = :totalTransaksi")})
public class TransaksiPembelian implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "No_Transaksi")
    private String noTransaksi;
    @Basic(optional = false)
    @Column(name = "No_Tanda_Terima")
    private String noTandaTerima;
    @Basic(optional = false)
    @Column(name = "No_Invoice")
    private String noInvoice;
    @Basic(optional = false)
    @Column(name = "Total_Transaksi")
    private double totalTransaksi;

    public TransaksiPembelian() {
    }

    public TransaksiPembelian(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public TransaksiPembelian(String noTransaksi, String noTandaTerima, String noInvoice, double totalTransaksi) {
        this.noTransaksi = noTransaksi;
        this.noTandaTerima = noTandaTerima;
        this.noInvoice = noInvoice;
        this.totalTransaksi = totalTransaksi;
    }

    public String getNoTransaksi() {
        return noTransaksi;
    }

    public void setNoTransaksi(String noTransaksi) {
        this.noTransaksi = noTransaksi;
    }

    public String getNoTandaTerima() {
        return noTandaTerima;
    }

    public void setNoTandaTerima(String noTandaTerima) {
        this.noTandaTerima = noTandaTerima;
    }

    public String getNoInvoice() {
        return noInvoice;
    }

    public void setNoInvoice(String noInvoice) {
        this.noInvoice = noInvoice;
    }

    public double getTotalTransaksi() {
        return totalTransaksi;
    }

    public void setTotalTransaksi(double totalTransaksi) {
        this.totalTransaksi = totalTransaksi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (noTransaksi != null ? noTransaksi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TransaksiPembelian)) {
            return false;
        }
        TransaksiPembelian other = (TransaksiPembelian) object;
        if ((this.noTransaksi == null && other.noTransaksi != null) || (this.noTransaksi != null && !this.noTransaksi.equals(other.noTransaksi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.TransaksiPembelian[ noTransaksi=" + noTransaksi + " ]";
    }
    
}
