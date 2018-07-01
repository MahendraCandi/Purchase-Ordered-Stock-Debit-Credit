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
@Table(name = "jurnal_detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JurnalDetail.findAll", query = "SELECT j FROM JurnalDetail j")
    , @NamedQuery(name = "JurnalDetail.findById", query = "SELECT j FROM JurnalDetail j WHERE j.id = :id")
    , @NamedQuery(name = "JurnalDetail.findByNoJurnal", query = "SELECT j FROM JurnalDetail j WHERE j.noJurnal = :noJurnal")
    , @NamedQuery(name = "JurnalDetail.findByKdAkun", query = "SELECT j FROM JurnalDetail j WHERE j.kdAkun = :kdAkun")
    , @NamedQuery(name = "JurnalDetail.findByDebet", query = "SELECT j FROM JurnalDetail j WHERE j.debet = :debet")
    , @NamedQuery(name = "JurnalDetail.findByKredit", query = "SELECT j FROM JurnalDetail j WHERE j.kredit = :kredit")})
public class JurnalDetail implements Serializable {

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
    @Column(name = "Kd_Akun")
    private String kdAkun;
    @Basic(optional = false)
    @Column(name = "Debet")
    private double debet;
    @Basic(optional = false)
    @Column(name = "Kredit")
    private double kredit;

    public JurnalDetail() {
    }

    public JurnalDetail(Integer id) {
        this.id = id;
    }

    public JurnalDetail(Integer id, String noJurnal, String kdAkun, double debet, double kredit) {
        this.id = id;
        this.noJurnal = noJurnal;
        this.kdAkun = kdAkun;
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

    public String getKdAkun() {
        return kdAkun;
    }

    public void setKdAkun(String kdAkun) {
        this.kdAkun = kdAkun;
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
        if (!(object instanceof JurnalDetail)) {
            return false;
        }
        JurnalDetail other = (JurnalDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.JurnalDetail[ id=" + id + " ]";
    }
    
}
