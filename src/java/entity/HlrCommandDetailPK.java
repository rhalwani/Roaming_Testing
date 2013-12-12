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

/**
 *
 * @author Riad
 */
@Embeddable
public class HlrCommandDetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_ID")
    private short cmdId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_INDEX")
    private short cmdIndex;

    public HlrCommandDetailPK() {
    }

    public HlrCommandDetailPK(short cmdId, short cmdIndex) {
        this.cmdId = cmdId;
        this.cmdIndex = cmdIndex;
    }

    public short getCmdId() {
        return cmdId;
    }

    public void setCmdId(short cmdId) {
        this.cmdId = cmdId;
    }

    public short getCmdIndex() {
        return cmdIndex;
    }

    public void setCmdIndex(short cmdIndex) {
        this.cmdIndex = cmdIndex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cmdId;
        hash += (int) cmdIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HlrCommandDetailPK)) {
            return false;
        }
        HlrCommandDetailPK other = (HlrCommandDetailPK) object;
        if (this.cmdId != other.cmdId) {
            return false;
        }
        if (this.cmdIndex != other.cmdIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HlrCommandDetailPK[ cmdId=" + cmdId + ", cmdIndex=" + cmdIndex + " ]";
    }
    
}
