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
 * @author Candi-PC
 */
@Entity
@Table(name = "detail_jurnal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailJurnal.findAll", query = "SELECT d FROM DetailJurnal d"),
    @NamedQuery(name = "DetailJurnal.findById", query = "SELECT d FROM DetailJurnal d WHERE d.id = :id"),
    @NamedQuery(name = "DetailJurnal.findByNoJurnal", query = "SELECT d FROM DetailJurnal d WHERE d.noJurnal = :noJurnal"),
    @NamedQuery(name = "DetailJurnal.findByKdPerkiraan", query = "SELECT d FROM DetailJurnal d WHERE d.kdPerkiraan = :kdPerkiraan"),
    @NamedQuery(name = "DetailJurnal.findByDebet", query = "SELECT d FROM DetailJurnal d WHERE d.debet = :debet"),
    @NamedQuery(name = "DetailJurnal.findByKredit", query = "SELECT d FROM DetailJurnal d WHERE d.kredit = :kredit")})
public class DetailJurnal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "No_Jurnal")
    private String noJurnal;
    @Basic(optional = false)
    @Column(name = "Kd_Perkiraan")
    private String kdPerkiraan;
    @Basic(optional = false)
    @Column(name = "Debet")
    private double debet;
    @Basic(optional = false)
    @Column(name = "Kredit")
    private double kredit;

    public DetailJurnal() {
    }

    public DetailJurnal(Integer id) {
        this.id = id;
    }

    public DetailJurnal(Integer id, String noJurnal, String kdPerkiraan, double debet, double kredit) {
        this.id = id;
        this.noJurnal = noJurnal;
        this.kdPerkiraan = kdPerkiraan;
        this.debet = debet;
        this.kredit = kredit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoJurnal() {
        return noJurnal;
    }

    public void setNoJurnal(String noJurnal) {
        this.noJurnal = noJurnal;
    }

    public String getKdPerkiraan() {
        return kdPerkiraan;
    }

    public void setKdPerkiraan(String kdPerkiraan) {
        this.kdPerkiraan = kdPerkiraan;
    }

    public double getDebet() {
        return debet;
    }

    public void setDebet(double debet) {
        this.debet = debet;
    }

    public double getKredit() {
        return kredit;
    }

    public void setKredit(double kredit) {
        this.kredit = kredit;
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
        if (!(object instanceof DetailJurnal)) {
            return false;
        }
        DetailJurnal other = (DetailJurnal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.DetailJurnal[ id=" + id + " ]";
    }
    
}
