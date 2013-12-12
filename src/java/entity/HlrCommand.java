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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "HLR_COMMAND")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HlrCommand.findAll", query = "SELECT h FROM HlrCommand h ORDER BY h.cmdDescription"),
    @NamedQuery(name = "HlrCommand.findByCmdId", query = "SELECT h FROM HlrCommand h WHERE h.cmdId = :cmdId"),
    @NamedQuery(name = "HlrCommand.findByCmdDescription", query = "SELECT h FROM HlrCommand h WHERE h.cmdDescription = :cmdDescription")})
public class HlrCommand implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CMD_ID")
    private Short cmdId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "CMD_DESCRIPTION")
    private String cmdDescription;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hlrCommand")
    private Collection<HlrCommandDetail> hlrCommandDetailCollection;

    public HlrCommand() {
    }

    public HlrCommand(Short cmdId) {
        this.cmdId = cmdId;
    }

    public HlrCommand(Short cmdId, String cmdDescription) {
        this.cmdId = cmdId;
        this.cmdDescription = cmdDescription;
    }

    public Short getCmdId() {
        return cmdId;
    }

    public void setCmdId(Short cmdId) {
        this.cmdId = cmdId;
    }

    public String getCmdDescription() {
        return cmdDescription;
    }

    public void setCmdDescription(String cmdDescription) {
        this.cmdDescription = cmdDescription;
    }

    @XmlTransient
    public Collection<HlrCommandDetail> getHlrCommandDetailCollection() {
        return hlrCommandDetailCollection;
    }

    public void setHlrCommandDetailCollection(Collection<HlrCommandDetail> hlrCommandDetailCollection) {
        this.hlrCommandDetailCollection = hlrCommandDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cmdId != null ? cmdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HlrCommand)) {
            return false;
        }
        HlrCommand other = (HlrCommand) object;
        if ((this.cmdId == null && other.cmdId != null) || (this.cmdId != null && !this.cmdId.equals(other.cmdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HlrCommand[ cmdId=" + cmdId + " ]";
    }
    
}
