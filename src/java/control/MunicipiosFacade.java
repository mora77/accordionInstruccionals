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
import modelo.Municipios;

/**
 *
 * @author bmora
 */
@Stateless
public class MunicipiosFacade extends AbstractFacade<Municipios> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipiosFacade() {
        super(Municipios.class);
    }
    
    public List<Municipios> ListaActivos() {
        Query consulta = em.createNamedQuery("Municipios.findByActivos", Municipios.class);
        List<Municipios> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Municipios> ListaBaja() {
        Query consulta = em.createNamedQuery("Municipios.findByBaja", Municipios.class);
        List<Municipios> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Municipios> Buscar(int id_entidad) {
        Query consulta= em.createNamedQuery("Municipios.Buscar",Municipios.class).setParameter("identi", id_entidad);
        List<Municipios> lista= consulta.getResultList();
        return lista; 
    }
    
}
