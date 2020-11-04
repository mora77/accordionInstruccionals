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
import modelo.Instruccionales;

/**
 *
 * @author bmora
 */
@Stateless
public class InstruccionalesFacade extends AbstractFacade<Instruccionales> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstruccionalesFacade() {
        super(Instruccionales.class);
    }
    
    public List<Instruccionales> ListaActivos() {
        Query consulta = em.createNamedQuery("Instruccionales.findByActivos", Instruccionales.class);
        List<Instruccionales> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Instruccionales> ListaBaja() {
        Query consulta = em.createNamedQuery("Instruccionales.findByBaja", Instruccionales.class);
        List<Instruccionales> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
