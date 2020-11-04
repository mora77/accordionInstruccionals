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
import modelo.TiposVenta;

/**
 *
 * @author bmora
 */
@Stateless
public class TiposVentaFacade extends AbstractFacade<TiposVenta> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposVentaFacade() {
        super(TiposVenta.class);
    }
    
    public List<TiposVenta> ListaActivos() {
        Query consulta = em.createNamedQuery("TiposVenta.findByActivos", TiposVenta.class);
        List<TiposVenta> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<TiposVenta> ListaBaja() {
        Query consulta = em.createNamedQuery("TiposVenta.findByBaja", TiposVenta.class);
        List<TiposVenta> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
