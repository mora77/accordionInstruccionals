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
import modelo.Paises;

/**
 *
 * @author bmora
 */
@Stateless
public class PaisesFacade extends AbstractFacade<Paises> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaisesFacade() {
        super(Paises.class);
    }
    
    public List<Paises> ListaActivos() {
        Query consulta = em.createNamedQuery("Paises.findByActivos", Paises.class);
        List<Paises> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Paises> ListaBaja() {
        Query consulta = em.createNamedQuery("Paises.findByBaja", Paises.class);
        List<Paises> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public String CambiarStatus(int nuevo, int id){
        Query consulta= em.createNamedQuery("Paises.Cambia",Paises.class).setParameter("nvo", nuevo).setParameter("idpais", id);
        consulta.executeUpdate();
        return "OK";
    }
    
}
