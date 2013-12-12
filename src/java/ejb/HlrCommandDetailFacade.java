/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.HlrCommandDetail;
import javax.persistence.TypedQuery;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author Riad
 */
@Stateless
public class HlrCommandDetailFacade extends AbstractFacade<HlrCommandDetail> {
    @PersistenceContext(unitName = "RoamtestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HlrCommandDetailFacade() {
        super(HlrCommandDetail.class);
    }
    
public ArrayList[] getHLRCmdDetail(Short cmdId) {
    TypedQuery q = this.em.createNamedQuery("HlrCommandDetail.findByCmdId", HlrCommandDetail.class);
    q.setParameter("cmdId", cmdId);
    List hList = q.getResultList();

    if (!hList.isEmpty()) {
      Iterator resIter = hList.iterator();
      ArrayList[] hlrMMLCmd = new ArrayList[2];
      hlrMMLCmd[0] = new ArrayList(hList.size());
      hlrMMLCmd[1] = new ArrayList(hList.size());
      while (resIter.hasNext()) {
        HlrCommandDetail hDet = (HlrCommandDetail)resIter.next();

        hlrMMLCmd[0].add(Short.valueOf(hDet.getHlrCommandDetailPK().getCmdIndex()));

        hlrMMLCmd[1].add(hDet.getHlrCmd());
      }
      return hlrMMLCmd;
    }
    return null;
  }
}
