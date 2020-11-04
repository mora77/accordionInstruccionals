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
import modelo.Entidades;

/**
 *
 * @author bmora
 */
@Stateless
public class EntidadesFacade extends AbstractFacade<Entidades> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntidadesFacade() {
        super(Entidades.class);
    }

    public List<Entidades> ListaActivos() {
        Query consulta = em.createNamedQuery("Entidades.findByActivos", Entidades.class);
        List<Entidades> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista;
        }
        return null;
    }

    public List<Entidades> ListaBaja() {
        Query consulta = em.createNamedQuery("Entidades.findByBaja", Entidades.class);
        List<Entidades> lista = consulta.getResultList();
        if (!lista.isEmpty()) {
            return lista;
        }
        return null;
    }

    public List<Entidades> Buscar(int id_pais) {
        Query consulta= em.createNamedQuery("Entidades.Buscar",Entidades.class).setParameter("idpais", id_pais);
        List<Entidades> lista= consulta.getResultList();
        return lista; 
    }
}
