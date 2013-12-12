/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Riad
 */
@Entity
@Table(name = "HLR_COMMAND_DETAIL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HlrCommandDetail.findAll", query = "SELECT h FROM HlrCommandDetail h"),
    @NamedQuery(name = "HlrCommandDetail.findByCmdId", query = "SELECT h FROM HlrCommandDetail h WHERE h.hlrCommandDetailPK.cmdId = :cmdId"),
    @NamedQuery(name = "HlrCommandDetail.findByCmdIndex", query = "SELECT h FROM HlrCommandDetail h WHERE h.hlrCommandDetailPK.cmdIndex = :cmdIndex"),
    @NamedQuery(name = "HlrCommandDetail.findByHlrCmd", query = "SELECT h FROM HlrCommandDetail h WHERE h.hlrCmd = :hlrCmd")})
@NamedNativeQuery(name="HlrCommandParam.distinctInputParams", query="select distinct PARAM_NAME from HLR_COMMAND_PARAM WHERE CMD_ID = ? AND PARAM_VALUE = ''")
public class HlrCommandDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected HlrCommandDetailPK hlrCommandDetailPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "HLR_CMD")
    private String hlrCmd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hlrCommandDetail")
    private Collection<HlrCommandParam> hlrCommandParamCollection;
    @JoinColumn(name = "CMD_ID", referencedColumnName = "CMD_ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private HlrCommand hlrCommand;

    public HlrCommandDetail() {
    }

    public HlrCommandDetail(HlrCommandDetailPK hlrCommandDetailPK) {
        this.hlrCommandDetailPK = hlrCommandDetailPK;
    }

    public HlrCommandDetail(HlrCommandDetailPK hlrCommandDetailPK, String hlrCmd) {
        this.hlrCommandDetailPK = hlrCommandDetailPK;
        this.hlrCmd = hlrCmd;
    }

    public HlrCommandDetail(short cmdId, short cmdIndex) {
        this.hlrCommandDetailPK = new HlrCommandDetailPK(cmdId, cmdIndex);
    }

    public HlrCommandDetailPK getHlrCommandDetailPK() {
        return hlrCommandDetailPK;
    }

    public void setHlrCommandDetailPK(HlrCommandDetailPK hlrCommandDetailPK) {
        this.hlrCommandDetailPK = hlrCommandDetailPK;
    }

    public String getHlrCmd() {
        return hlrCmd;
    }

    public void setHlrCmd(String hlrCmd) {
        this.hlrCmd = hlrCmd;
    }

    @XmlTransient
    public Collection<HlrCommandParam> getHlrCommandParamCollection() {
        return hlrCommandParamCollection;
    }

    public void setHlrCommandParamCollection(Collection<HlrCommandParam> hlrCommandParamCollection) {
        this.hlrCommandParamCollection = hlrCommandParamCollection;
    }

    public HlrCommand getHlrCommand() {
        return hlrCommand;
    }

    public void setHlrCommand(HlrCommand hlrCommand) {
        this.hlrCommand = hlrCommand;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hlrCommandDetailPK != null ? hlrCommandDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HlrCommandDetail)) {
            return false;
        }
        HlrCommandDetail other = (HlrCommandDetail) object;
        if ((this.hlrCommandDetailPK == null && other.hlrCommandDetailPK != null) || (this.hlrCommandDetailPK != null && !this.hlrCommandDetailPK.equals(other.hlrCommandDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HlrCommandDetail[ hlrCommandDetailPK=" + hlrCommandDetailPK + " ]";
    }
    
}
