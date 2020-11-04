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
import modelo.Usuarios;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author bmora
 */
@Stateless
public class UsuariosFacade extends AbstractFacade<Usuarios> {

    @PersistenceContext(unitName = "venta_instruccionales_acordeonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuariosFacade() {
        super(Usuarios.class);
    }
    
    public List<Usuarios> ListaActivos() {
        Query consulta = em.createNamedQuery("Usuarios.findByActivos", Usuarios.class);
        List<Usuarios> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public List<Usuarios> ListaBaja() {
        Query consulta = em.createNamedQuery("Usuarios.findByBaja", Usuarios.class);
        List<Usuarios> lista = consulta.getResultList();
        if(!lista.isEmpty())
            return lista;
        return null;
    }
    
    public Usuarios Buscar(String username,String password){
        
        String pass_encriptado = DigestUtils.sha1Hex(password);
        Query consulta= em.createNamedQuery("Usuarios.Buscar",Usuarios.class).setParameter("usu", username).setParameter("pas", pass_encriptado);
        
        List<Usuarios> lista= consulta.getResultList();
        
        if(!lista.isEmpty())
            return lista.get(0);
        return null;
    }
    
    public String CambiarStatusN(String nuevo){
        Query consulta= em.createNamedQuery("Usuarios.CambiaN",Usuarios.class).setParameter("nvon", nuevo);
        consulta.executeUpdate();
        return "OK";
    }
    
    public String CambiarStatusAP(String nuevo, int id){
        Query consulta= em.createNamedQuery("Usuarios.CambiaAP",Usuarios.class).setParameter("nvoap", nuevo).setParameter("iduser", id);
        consulta.executeUpdate();
        return "OK";
    }
    
    public String CambiarStatusAM(String nuevo, int id){
        Query consulta= em.createNamedQuery("Usuarios.CambiaAM",Usuarios.class).setParameter("nvoam", nuevo).setParameter("iduser", id);
        consulta.executeUpdate();
        return "OK";
    }
    
    public String CambiarStatusT(String nuevo, int id){
        Query consulta= em.createNamedQuery("Usuarios.CambiaT", Usuarios.class).setParameter("nvot", nuevo).setParameter("iduser", id);
        consulta.executeUpdate();
        return "OK";
    }
    
    public String Cambia_user(String user, int idUsuario){
        Query consulta = em.createNamedQuery("Usuarios.cambia1",Usuarios.class)
                .setParameter("user_u", user)
                .setParameter("id_u", idUsuario);
        consulta.executeUpdate();
        return "ok";
    }
    
    public String Cambia_apu(String user, int idUsuario){
        Query consulta = em.createNamedQuery("Usuarios.cambiaa",Usuarios.class)
                .setParameter("user_u", user)
                .setParameter("id_u", idUsuario);
        consulta.executeUpdate();
        return "ok";
    }
    
    public String Cambia_amu(String user, int idUsuario){
        Query consulta = em.createNamedQuery("Usuarios.cambiaam",Usuarios.class)
                .setParameter("user_u", user)
                .setParameter("id_u", idUsuario);
        consulta.executeUpdate();
        return "ok";
    }
    
    public String Cambia_pass(String pass, int idUsuario){
        Query consulta = em.createNamedQuery("Usuarios.cambia2",Usuarios.class)
                .setParameter("pass_u", pass)
                .setParameter("id_u", idUsuario);
        consulta.executeUpdate();
        return "ok";
    }
    
    public String Cambia_nombre(String pass, int idUsuario){
        Query consulta = em.createNamedQuery("Usuarios.cambia2",Usuarios.class)
                .setParameter("pass_u", pass)
                .setParameter("id_u", idUsuario);
        consulta.executeUpdate();
        return "ok";
    }
    
    
    
}
