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
 * @author 0085
 */
@Entity
@Table(name = "data_akun")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataAkun.findAll", query = "SELECT d FROM DataAkun d")
    , @NamedQuery(name = "DataAkun.findByKdAkun", query = "SELECT d FROM DataAkun d WHERE d.kdAkun = :kdAkun")
    , @NamedQuery(name = "DataAkun.findByNmAkun", query = "SELECT d FROM DataAkun d WHERE d.nmAkun = :nmAkun")})
public class DataAkun implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kd_Akun")
    private String kdAkun;
    @Basic(optional = false)
    @Column(name = "Nm_Akun")
    private String nmAkun;

    public DataAkun() {
    }

    public DataAkun(String kdAkun) {
        this.kdAkun = kdAkun;
    }

    public DataAkun(String kdAkun, String nmAkun) {
        this.kdAkun = kdAkun;
        this.nmAkun = nmAkun;
    }

    public String getKdAkun() {
        return kdAkun;
    }

    public void setKdAkun(String kdAkun) {
        this.kdAkun = kdAkun;
    }

    public String getNmAkun() {
        return nmAkun;
    }

    public void setNmAkun(String nmAkun) {
        this.nmAkun = nmAkun;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdAkun != null ? kdAkun.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataAkun)) {
            return false;
        }
        DataAkun other = (DataAkun) object;
        if ((this.kdAkun == null && other.kdAkun != null) || (this.kdAkun != null && !this.kdAkun.equals(other.kdAkun))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.DataAkun[ kdAkun=" + kdAkun + " ]";
    }
    
}
