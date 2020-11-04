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
import modelo.Categorias;

/**
 *
 * @author bmora
 */
@Stateless
public class CategoriasFacade extends AbstractFacade<Categorias> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriasFacade() {
        super(Categorias.class);
    }
    public List<Categorias> ListaActivos() {
        Query consulta = em.createNamedQuery("Categorias.findByActivos", Categorias.class);
        List<Categorias> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Categorias> ListaBaja() {
        Query consulta = em.createNamedQuery("Categorias.findByBaja", Categorias.class);
        List<Categorias> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
