package control;

import modelo.Usuarios;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;
import java.io.IOException;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.AjaxBehaviorEvent;
import modelo.Entidades;
import modelo.Municipios;
import modelo.Paises;
import modelo.TiposUsuario;
import org.apache.commons.codec.digest.DigestUtils;

@Named("usuariosController")
@SessionScoped
public class UsuariosController implements Serializable {

    @EJB
    private control.UsuariosFacade ejbFacade;
    private List<Usuarios> items = null;
    private List<Usuarios> items2 = null;
    private Usuarios selected;
    private Usuarios usu_nvo;
    
    @EJB
    private control.PaisesFacade paisesFacade;

    @EJB
    private control.EntidadesFacade entidadesFacade;

    @EJB
    private control.MunicipiosFacade municipiosFacade;

    private List<Paises> listpaises1;
    private List<Entidades> listaentidades1;
    private List<Municipios> listamunicipios1;

    public List<Paises> getListpaises1() {
        return listpaises1;
    }

    public void setListpaises1(List<Paises> listpaises1) {
        this.listpaises1 = listpaises1;
    }

    public List<Entidades> getListaentidades1() {
        return listaentidades1;
    }

    public void setListaentidades1(List<Entidades> listaentidades1) {
        this.listaentidades1 = listaentidades1;
    }

    public List<Municipios> getListamunicipios1() {
        return listamunicipios1;
    }

    public void setListamunicipios1(List<Municipios> listamunicipios1) {
        this.listamunicipios1 = listamunicipios1;
    }
    
    

    public List<Usuarios> getItems2() {
        if (items2 == null) {
            //items = getFacade().findAll();
            items2 = ejbFacade.ListaBaja();
        }
        return items2;
    }

    public void setItems2(List<Usuarios> items2) {
        this.items2 = items2;
    }
    
    

    public UsuariosController() {
    }

    public Usuarios getSelected() {
        if(selected==null){
            selected=new Usuarios();
            initializeEmbeddableKey();
        }
        return selected;
    }

    public void setSelected(Usuarios selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UsuariosFacade getFacade() {
        return ejbFacade;
    }

    public Usuarios prepareCreate() {
        selected = new Usuarios();
        initializeEmbeddableKey();
        return selected;
    }
    
    
    public void buscaentidades(final AjaxBehaviorEvent event){
        listaentidades1 = entidadesFacade.Buscar(selected.getIdPais().getId()); 
        listamunicipios1 = municipiosFacade.Buscar(0);
    }
    
    public void buscamunicipios(final AjaxBehaviorEvent event){
        listamunicipios1 = municipiosFacade.findAll();
    }
    @PostConstruct
    private void initialize(){
        listpaises1 = paisesFacade.findAll();        
    }
    
    
    public void create_new() {
        String pass = selected.getPassword();
        //mecanismo de encriptación
        String pass_encriptado = DigestUtils.sha1Hex(pass);
        selected.setPassword(pass_encriptado);
        
        selected.setStatus(1);
        
        TiposUsuario dem= new TiposUsuario();
        dem.setId(4);
        selected.setIdTipoUsu(dem);
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));
        listaentidades1 = entidadesFacade.Buscar(0); 
        listamunicipios1 = municipiosFacade.Buscar(0);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(UsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public void create() {
        String pass = selected.getPassword();
        //mecanismo de encriptación
        String pass_encriptado = DigestUtils.sha1Hex(pass);
        selected.setPassword(pass_encriptado);
        
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosCreated"));
        listaentidades1 = entidadesFacade.Buscar(0); 
        listamunicipios1 = municipiosFacade.Buscar(0);
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
        items=null;
    }

    public void destroy() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void restaurar() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UsuariosDeleted"));
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UsuariosUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Usuarios> getItems() {
        if (items == null) {
            //items = getFacade().findAll();
            items = ejbFacade.ListaActivos();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public Usuarios getUsuarios(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Usuarios> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Usuarios> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Usuarios.class)
    public static class UsuariosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UsuariosController controller = (UsuariosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "usuariosController");
            return controller.getUsuarios(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Usuarios) {
                Usuarios o = (Usuarios) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Usuarios.class.getName()});
                return null;
            }
        }

    }

}
