/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.HlrCommandParam;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
 *
 * @author Riad
 */
@Stateless
public class HlrCommandParamFacade extends AbstractFacade<HlrCommandParam> {
    @PersistenceContext(unitName = "RoamtestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HlrCommandParamFacade() {
        super(HlrCommandParam.class);
    }
    
  public ArrayList[] getCmdParams(Short cmdId) {
    TypedQuery q = this.em.createNamedQuery("HlrCommandParam.findByCmdId", HlrCommandParam.class);
    q.setParameter("cmdId", cmdId);
    List cmdParamList = q.getResultList();
    if (!cmdParamList.isEmpty()) {
      Iterator cmdParamListIter = cmdParamList.iterator();
      ArrayList[] cmdParamAndVals = new ArrayList[3];
      cmdParamAndVals[0] = new ArrayList(cmdParamList.size());
      cmdParamAndVals[1] = new ArrayList(cmdParamList.size());
      cmdParamAndVals[2] = new ArrayList(cmdParamList.size());

      while (cmdParamListIter.hasNext()) {
        HlrCommandParam hlrParam = (HlrCommandParam)cmdParamListIter.next();

        cmdParamAndVals[0].add(Short.valueOf(hlrParam.getHlrCommandParamPK().getCmdIndex()));

        cmdParamAndVals[1].add(hlrParam.getHlrCommandParamPK().getParamName());

        cmdParamAndVals[2].add(hlrParam.getParamValue());
      }
      return cmdParamAndVals;
    }
    return null;
  }

  public List<String> getCmdInputParams(Short cmdId)
  {
    return this.em.createNamedQuery("HlrCommandParam.distinctInputParams").setParameter(1, cmdId).getResultList();
  }
}
