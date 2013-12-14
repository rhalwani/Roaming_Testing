/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Riad
 */
@Embeddable
public class GPRSTestingMSISDNPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MSISDN")
    private String msisdn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "TAPCode")
    private String tAPCode;

    public GPRSTestingMSISDNPK() {
    }

    public GPRSTestingMSISDNPK(String msisdn, String tAPCode) {
        this.msisdn = msisdn;
        this.tAPCode = tAPCode;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTAPCode() {
        return tAPCode;
    }

    public void setTAPCode(String tAPCode) {
        this.tAPCode = tAPCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (msisdn != null ? msisdn.hashCode() : 0);
        hash += (tAPCode != null ? tAPCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GPRSTestingMSISDNPK)) {
            return false;
        }
        GPRSTestingMSISDNPK other = (GPRSTestingMSISDNPK) object;
        if ((this.msisdn == null && other.msisdn != null) || (this.msisdn != null && !this.msisdn.equals(other.msisdn))) {
            return false;
        }
        if ((this.tAPCode == null && other.tAPCode != null) || (this.tAPCode != null && !this.tAPCode.equals(other.tAPCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GPRSTestingMSISDNPK[ msisdn=" + msisdn + ", tAPCode=" + tAPCode + " ]";
    }
    
}
