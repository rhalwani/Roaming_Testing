/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import entity.GPRSTestingMSISDN;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Riad
 */
@Stateless
public class GPRSTestingMSISDNFacade extends AbstractFacade<GPRSTestingMSISDN> {
    @PersistenceContext(unitName = "RoamtestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GPRSTestingMSISDNFacade() {
        super(GPRSTestingMSISDN.class);
    }
    
}
