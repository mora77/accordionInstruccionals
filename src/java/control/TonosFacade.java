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
import modelo.Tonos;

/**
 *
 * @author bmora
 */
@Stateless
public class TonosFacade extends AbstractFacade<Tonos> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TonosFacade() {
        super(Tonos.class);
    }
    
    public List<Tonos> ListaActivos() {
        Query consulta = em.createNamedQuery("Tonos.findByActivos", Tonos.class);
        List<Tonos> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Tonos> ListaBaja() {
        Query consulta = em.createNamedQuery("Tonos.findByBaja", Tonos.class);
        List<Tonos> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
