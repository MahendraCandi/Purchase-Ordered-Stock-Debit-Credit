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
@Table(name = "data_perkiraan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DataPerkiraan.findAll", query = "SELECT d FROM DataPerkiraan d"),
    @NamedQuery(name = "DataPerkiraan.findByKdPerkiraan", query = "SELECT d FROM DataPerkiraan d WHERE d.kdPerkiraan = :kdPerkiraan"),
    @NamedQuery(name = "DataPerkiraan.findByNmPerkiraan", query = "SELECT d FROM DataPerkiraan d WHERE d.nmPerkiraan = :nmPerkiraan")})
public class DataPerkiraan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Kd_Perkiraan")
    private String kdPerkiraan;
    @Basic(optional = false)
    @Column(name = "Nm_Perkiraan")
    private String nmPerkiraan;

    public DataPerkiraan() {
    }

    public DataPerkiraan(String kdPerkiraan) {
        this.kdPerkiraan = kdPerkiraan;
    }

    public DataPerkiraan(String kdPerkiraan, String nmPerkiraan) {
        this.kdPerkiraan = kdPerkiraan;
        this.nmPerkiraan = nmPerkiraan;
    }

    public String getKdPerkiraan() {
        return kdPerkiraan;
    }

    public void setKdPerkiraan(String kdPerkiraan) {
        this.kdPerkiraan = kdPerkiraan;
    }

    public String getNmPerkiraan() {
        return nmPerkiraan;
    }

    public void setNmPerkiraan(String nmPerkiraan) {
        this.nmPerkiraan = nmPerkiraan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdPerkiraan != null ? kdPerkiraan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataPerkiraan)) {
            return false;
        }
        DataPerkiraan other = (DataPerkiraan) object;
        if ((this.kdPerkiraan == null && other.kdPerkiraan != null) || (this.kdPerkiraan != null && !this.kdPerkiraan.equals(other.kdPerkiraan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fladeoapp.data.DataPerkiraan[ kdPerkiraan=" + kdPerkiraan + " ]";
    }
    
}
