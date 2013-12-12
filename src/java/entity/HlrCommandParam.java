/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "HLR_COMMAND_PARAM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HlrCommandParam.findAll", query = "SELECT h FROM HlrCommandParam h"),
    @NamedQuery(name = "HlrCommandParam.findByCmdId", query = "SELECT h FROM HlrCommandParam h WHERE h.hlrCommandParamPK.cmdId = :cmdId"),
    @NamedQuery(name = "HlrCommandParam.findByCmdIndex", query = "SELECT h FROM HlrCommandParam h WHERE h.hlrCommandParamPK.cmdIndex = :cmdIndex"),
    @NamedQuery(name = "HlrCommandParam.findByParamName", query = "SELECT h FROM HlrCommandParam h WHERE h.hlrCommandParamPK.paramName = :paramName"),
    @NamedQuery(name = "HlrCommandParam.findByParamValue", query = "SELECT h FROM HlrCommandParam h WHERE h.paramValue = :paramValue")})
public class HlrCommandParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HlrCommandParamPK hlrCommandParamPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PARAM_VALUE")
    private String paramValue;
    @JoinColumns({
        @JoinColumn(name = "CMD_ID", referencedColumnName = "CMD_ID", insertable = false, updatable = false),
        @JoinColumn(name = "CMD_INDEX", referencedColumnName = "CMD_INDEX", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private HlrCommandDetail hlrCommandDetail;

    public HlrCommandParam() {
    }

    public HlrCommandParam(HlrCommandParamPK hlrCommandParamPK) {
        this.hlrCommandParamPK = hlrCommandParamPK;
    }

    public HlrCommandParam(HlrCommandParamPK hlrCommandParamPK, String paramValue) {
        this.hlrCommandParamPK = hlrCommandParamPK;
        this.paramValue = paramValue;
    }

    public HlrCommandParam(short cmdId, short cmdIndex, String paramName) {
        this.hlrCommandParamPK = new HlrCommandParamPK(cmdId, cmdIndex, paramName);
    }

    public HlrCommandParamPK getHlrCommandParamPK() {
        return hlrCommandParamPK;
    }

    public void setHlrCommandParamPK(HlrCommandParamPK hlrCommandParamPK) {
        this.hlrCommandParamPK = hlrCommandParamPK;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public HlrCommandDetail getHlrCommandDetail() {
        return hlrCommandDetail;
    }

    public void setHlrCommandDetail(HlrCommandDetail hlrCommandDetail) {
        this.hlrCommandDetail = hlrCommandDetail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hlrCommandParamPK != null ? hlrCommandParamPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HlrCommandParam)) {
            return false;
        }
        HlrCommandParam other = (HlrCommandParam) object;
        if ((this.hlrCommandParamPK == null && other.hlrCommandParamPK != null) || (this.hlrCommandParamPK != null && !this.hlrCommandParamPK.equals(other.hlrCommandParamPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HlrCommandParam[ hlrCommandParamPK=" + hlrCommandParamPK + " ]";
    }
    
}
