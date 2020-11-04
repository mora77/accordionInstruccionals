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
import modelo.TiposUsuario;

/**
 *
 * @author bmora
 */
@Stateless
public class TiposUsuarioFacade extends AbstractFacade<TiposUsuario> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TiposUsuarioFacade() {
        super(TiposUsuario.class);
    }
    
    public List<TiposUsuario> ListaActivos() {
        Query consulta = em.createNamedQuery("TiposUsuario.findByActivos", TiposUsuario.class);
        List<TiposUsuario> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<TiposUsuario> ListaBaja() {
        Query consulta = em.createNamedQuery("TiposUsuario.findByIdBaja", TiposUsuario.class);
        List<TiposUsuario> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
