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
import modelo.VideoAdornos;

/**
 *
 * @author bmora
 */
@Stateless
public class VideoAdornosFacade extends AbstractFacade<VideoAdornos> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VideoAdornosFacade() {
        super(VideoAdornos.class);
    }
    
    public List<VideoAdornos> ListaActivos() {
        Query consulta = em.createNamedQuery("VideoAdornos.findByActivos", VideoAdornos.class);
        List<VideoAdornos> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<VideoAdornos> ListaBaja() {
        Query consulta = em.createNamedQuery("VideoAdornos.findByBaja", VideoAdornos.class);
        List<VideoAdornos> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
}
