package control;

import modelo.Acordeones;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("acordeonesController")
@SessionScoped
public class AcordeonesController implements Serializable {

    @EJB
    private control.AcordeonesFacade ejbFacade;
    private List<Acordeones> items = null;
    private List<Acordeones> items2 = null;
    private Acordeones selected;

    public List<Acordeones> getItems2() {
        if (items2 == null) {
            //items = getFacade().findAll();
            items2 = ejbFacade.ListaBaja();
        }
        return items2;
    }

    public void setItems2(List<Acordeones> items2) {
        this.items2 = items2;
    }
    

    public AcordeonesController() {
    }

    public Acordeones getSelected() {
        return selected;
    }

    public void setSelected(Acordeones selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AcordeonesFacade getFacade() {
        return ejbFacade;
    }

    public Acordeones prepareCreate() {
        selected = new Acordeones();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesUpdated"));
        items=null;
        
    }

    public void destroy() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesDeleted"));
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    
    public void restaurar() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesDeleted"));
        selected.setStatus(1);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AcordeonesUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;
            items2 = null;// Invalidate list of items to trigger re-query.
        }
    }

    public List<Acordeones> getItems() {
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

    public Acordeones getAcordeones(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<Acordeones> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Acordeones> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Acordeones.class)
    public static class AcordeonesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AcordeonesController controller = (AcordeonesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "acordeonesController");
            return controller.getAcordeones(getKey(value));
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
            if (object instanceof Acordeones) {
                Acordeones o = (Acordeones) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Acordeones.class.getName()});
                return null;
            }
        }

    }

}
