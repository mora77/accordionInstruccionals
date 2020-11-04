/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import modelo.Usuarios;

/**
 *
 * @author bmora
 */
@Named(value = "login")
@SessionScoped
public class login implements Serializable {

    @EJB
    private control.UsuariosFacade ejbFacade;
    private HttpServletRequest httpservlet;
    private String username;
    private String password;
    private Usuarios usuarioautenticado;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuarios getUsuarioautenticado() {
        return usuarioautenticado;
    }

    public void setUsuarioautenticado(Usuarios usuarioautenticado) {
        this.usuarioautenticado = usuarioautenticado;
    }

    /**
     * Creates a new instance of login
     */
    public login() {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
    public int sacarId(int idU){
        idU= usuarioautenticado.getId(); 
        return idU;
    }

    public void Acceder() throws IOException {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        usuarioautenticado = ejbFacade.Buscar(username, password);
        if (usuarioautenticado != null) {
            httpservlet.getSession().setAttribute("username", usuarioautenticado.getUsername());
            httpservlet.getSession().setAttribute("nom_completo", usuarioautenticado.getNombre() + " " + usuarioautenticado.getApPat() + " " + usuarioautenticado.getApMat());
            httpservlet.getSession().setAttribute("nivel_usuario", usuarioautenticado.getIdTipoUsu().getNivel());
            httpservlet.getSession().setAttribute("usuario", usuarioautenticado);
            switch (usuarioautenticado.getIdTipoUsu().getNivel()) {
                case 1:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("admin.xhtml");
                    break;
                case 2:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("super.xhtml");
                    break;
                case 3:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("cliente.xhtml");
                    break;
                case 4:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("cliente.xhtml");
                    break;
                default:
                    FacesContext.getCurrentInstance().getExternalContext().redirect("autenticado.xhtml");
                    break;

            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o password incorrecto", null));
        }
    }

    public void cerrarsesion() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/venta_instruccionales_acordeon/faces/bienvenida.xhtml");

        } catch (Exception e) {
            System.out.println("Error" + e.toString());
        }
    }

    public void verificanivel(int nivel) throws IOException {
        httpservlet = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        Usuarios usu = (Usuarios) httpservlet.getSession().getAttribute("usuario");
        if (usu != null) {
            if (usu.getIdTipoUsu().getNivel() != nivel) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/venta_instruccionales_acordeon/faces/sinprivilegios.xhtml");

            }
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesContext.getCurrentInstance().getExternalContext().redirect("/venta_instruccionales_acordeon/faces/login.xhtml");
        }
    }

}
