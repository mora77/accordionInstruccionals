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
import modelo.TiposPago;

/**
 *
 * @author bmora
 */
@Stateless
public class TiposPagoFacade extends AbstractFacade<TiposPago> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposPagoFacade() {
        super(TiposPago.class);
    }
    
    public List<TiposPago> ListaActivos() {
        Query consulta = em.createNamedQuery("TiposPago.findByActivos", TiposPago.class);
        List<TiposPago> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<TiposPago> ListaBaja() {
        Query consulta = em.createNamedQuery("TiposPago.findByBaja", TiposPago.class);
        List<TiposPago> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
