/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.Anotaciones;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * @author Italo
 */
@Stateless
public class AnotacionesSB extends AbstractFacade<Anotaciones> implements AnotacionesSBLocal {
    @PersistenceContext(unitName = "AppSolutions_ProyectoPINGESO-ejb_ejb_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnotacionesSB() {
        super(Anotaciones.class);
    }
    /**
     * @author Kevin
     * @param nombreGen 
     * @return Lista
     */
    
    @Override
    public List<Anotaciones> encontrarTerminos(String nombreGen) {
        Query query;
        query = em.createNamedQuery("Anotaciones.findByGen")
                .setParameter("gen", nombreGen);
        return query.getResultList();
    }
    
    
}
