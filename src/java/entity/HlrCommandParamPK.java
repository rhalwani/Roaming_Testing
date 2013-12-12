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
public class HlrCommandParamPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_ID")
    private short cmdId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_INDEX")
    private short cmdIndex;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "PARAM_NAME")
    private String paramName;

    public HlrCommandParamPK() {
    }

    public HlrCommandParamPK(short cmdId, short cmdIndex, String paramName) {
        this.cmdId = cmdId;
        this.cmdIndex = cmdIndex;
        this.paramName = paramName;
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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) cmdId;
        hash += (int) cmdIndex;
        hash += (paramName != null ? paramName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HlrCommandParamPK)) {
            return false;
        }
        HlrCommandParamPK other = (HlrCommandParamPK) object;
        if (this.cmdId != other.cmdId) {
            return false;
        }
        if (this.cmdIndex != other.cmdIndex) {
            return false;
        }
        if ((this.paramName == null && other.paramName != null) || (this.paramName != null && !this.paramName.equals(other.paramName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HlrCommandParamPK[ cmdId=" + cmdId + ", cmdIndex=" + cmdIndex + ", paramName=" + paramName + " ]";
    }
    
}
