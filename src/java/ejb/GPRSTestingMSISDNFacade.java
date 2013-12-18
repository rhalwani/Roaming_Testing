/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.GPRSTestingMSISDN;
import entity.GPRSTestingMSISDNPK;
import javax.ejb.Stateless;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Riad
 */
@Stateless
public class GPRSTestingMSISDNFacade extends AbstractFacade<GPRSTestingMSISDN> {
    @PersistenceContext(unitName = "RoamTestNumPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GPRSTestingMSISDNFacade() {
        super(GPRSTestingMSISDN.class);
    }
    
    public List<GPRSTestingMSISDN> getRoamTestNumbers() {
        
        TypedQuery query = em.createNamedQuery("GPRSTestingMSISDN.findAll", GPRSTestingMSISDN.class);
        return query.getResultList();
    }
    
    public boolean deleteTestNumber(String TapCode, String Msisdn) {
        
        //TypedQuery query = em.createNamedQuery("GPRSTestingMSISDN.findByMsisdnAndTAPCode");
        GPRSTestingMSISDN roamMs = em.find(GPRSTestingMSISDN.class, new GPRSTestingMSISDNPK(Msisdn, TapCode));
        if (roamMs != null) {
            em.remove(roamMs);
            return true;
        } else
            return false;
    }
    
    public void addTestNumber(String TapCode, String Msisdn, String Operator, String Description) {
        
        GPRSTestingMSISDN roamMs = new GPRSTestingMSISDN(Msisdn, TapCode);
        roamMs.setOperator(Operator);
        roamMs.setDescription(Description);
        em.persist(roamMs);
    }
    
    public void updateTestNumber(String Msisdn, String TapCode, String Operator, String Description) {
        
        
    }
}
