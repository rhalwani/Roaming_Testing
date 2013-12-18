/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "GPRSTestingMSISDN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GPRSTestingMSISDN.findAll", query = "SELECT g FROM GPRSTestingMSISDN g"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByMsisdn", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.gPRSTestingMSISDNPK.msisdn = :msisdn"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByCountry", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.country = :country"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByOperator", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.operator = :operator"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByTAPCode", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.gPRSTestingMSISDNPK.tAPCode = :tAPCode"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByMsisdnAndTAPCode", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.gPRSTestingMSISDNPK.msisdn = :msisdn and g.gPRSTestingMSISDNPK.tAPCode = :tAPCode"),
    @NamedQuery(name = "GPRSTestingMSISDN.findByDescription", query = "SELECT g FROM GPRSTestingMSISDN g WHERE g.description = :description")})
public class GPRSTestingMSISDN implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GPRSTestingMSISDNPK gPRSTestingMSISDNPK;
    @Size(max = 35)
    @Column(name = "Country")
    private String country;
    @Size(max = 50)
    @Column(name = "Operator")
    private String operator;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;

    public GPRSTestingMSISDN() {
    }

    public GPRSTestingMSISDN(GPRSTestingMSISDNPK gPRSTestingMSISDNPK) {
        this.gPRSTestingMSISDNPK = gPRSTestingMSISDNPK;
    }

    public GPRSTestingMSISDN(String msisdn, String tAPCode) {
        this.gPRSTestingMSISDNPK = new GPRSTestingMSISDNPK(msisdn, tAPCode);
    }

    public GPRSTestingMSISDNPK getGPRSTestingMSISDNPK() {
        return gPRSTestingMSISDNPK;
    }

    public void setGPRSTestingMSISDNPK(GPRSTestingMSISDNPK gPRSTestingMSISDNPK) {
        this.gPRSTestingMSISDNPK = gPRSTestingMSISDNPK;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gPRSTestingMSISDNPK != null ? gPRSTestingMSISDNPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GPRSTestingMSISDN)) {
            return false;
        }
        GPRSTestingMSISDN other = (GPRSTestingMSISDN) object;
        if ((this.gPRSTestingMSISDNPK == null && other.gPRSTestingMSISDNPK != null) || (this.gPRSTestingMSISDNPK != null && !this.gPRSTestingMSISDNPK.equals(other.gPRSTestingMSISDNPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GPRSTestingMSISDN[ gPRSTestingMSISDNPK=" + gPRSTestingMSISDNPK + " ]";
    }
    
}
