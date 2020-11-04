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
import modelo.Canciones;

/**
 *
 * @author bmora
 */
@Stateless
public class CancionesFacade extends AbstractFacade<Canciones> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CancionesFacade() {
        super(Canciones.class);
    }
    
    public List<Canciones> ListaActivos() {
        Query consulta = em.createNamedQuery("Canciones.findByActivos", Canciones.class);
        List<Canciones> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Canciones> ListaBaja() {
        Query consulta = em.createNamedQuery("Canciones.findByBaja", Canciones.class);
        List<Canciones> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
