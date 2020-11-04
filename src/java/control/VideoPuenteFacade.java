/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import modelo.Acordeones;
import modelo.VideoPuente;

/**
 *
 * @author bmora
 */
@Stateless
public class VideoPuenteFacade extends AbstractFacade<VideoPuente> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideoPuenteFacade() {
        super(VideoPuente.class);
    }
    
    public List<VideoPuente> ListaActivos() {
        Query consulta = em.createNamedQuery("VideoPuente.findByActivos", VideoPuente.class);
        List<VideoPuente> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<VideoPuente> ListaBaja() {
        Query consulta = em.createNamedQuery("VideoPuente.findByBaja", VideoPuente.class);
        List<VideoPuente> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
