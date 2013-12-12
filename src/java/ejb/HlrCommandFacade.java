/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.HlrCommand;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Riad
 */
@Stateless
public class HlrCommandFacade extends AbstractFacade<HlrCommand> {
    @PersistenceContext(unitName = "RoamtestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HlrCommandFacade() {
        super(HlrCommand.class);
    }
    
public List<String> getHLRCmdList()
  {
    TypedQuery q = this.em.createNamedQuery("HlrCommand.findAll", HlrCommand.class);
    List cmdList = q.getResultList();

    Iterator cmdListIter = cmdList.iterator();
    List cmdDescList = new ArrayList();
    while (cmdListIter.hasNext()) {
      cmdDescList.add(((HlrCommand)cmdListIter.next()).getCmdDescription());
    }
    return cmdDescList;
  }

  public Short getCmdId(String HLRCmdDescription)
  {
    TypedQuery q = this.em.createNamedQuery("HlrCommand.findByCmdDescription", HlrCommand.class);
    q.setParameter("cmdDescription", HLRCmdDescription);
    List hlrCmdIdList = q.getResultList();
    if (!hlrCmdIdList.isEmpty()) {
      return ((HlrCommand)hlrCmdIdList.get(0)).getCmdId();
    }
    return null;
  }

  public String getCmdDescription(Short cmdId)
  {
    TypedQuery q = this.em.createNamedQuery("HlrCommand.findByCmdId", HlrCommand.class);
    q.setParameter("cmdId", cmdId);
    return ((HlrCommand)q.getSingleResult()).getCmdDescription();
  }
}
