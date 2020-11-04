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

/**
 *
 * @author bmora
 */
@Stateless
public class AcordeonesFacade extends AbstractFacade<Acordeones> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcordeonesFacade() {
        super(Acordeones.class);
    }

    public List<Acordeones> ListaActivos() {
        Query consulta = em.createNamedQuery("Acordeones.findByActivos", Acordeones.class);
        List<Acordeones> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Acordeones> ListaBaja() {
        Query consulta = em.createNamedQuery("Acordeones.findByBaja", Acordeones.class);
        List<Acordeones> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }

}
