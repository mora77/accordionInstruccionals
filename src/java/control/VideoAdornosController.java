package control;

import modelo.VideoAdornos;
import control.util.JsfUtil;
import control.util.JsfUtil.PersistAction;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.UploadedFile;

@Named("videoAdornosController")
@SessionScoped
public class VideoAdornosController implements Serializable {

    @EJB
    private control.VideoAdornosFacade ejbFacade;
    private List<VideoAdornos> items = null;
    private List<VideoAdornos> items2 = null;
    private VideoAdornos selected;
    private UploadedFile video;
    private String aux;

    public UploadedFile getVideo() {
        return video;
    }

    public void setVideo(UploadedFile video) {
        this.video = video;
    }

    public String getAux() {
        return aux;
    }

    public void setAux(String aux) {
        this.aux = aux;
    }

    public List<VideoAdornos> getItems2() {
        if (items2 == null) {
            //items = getFacade().findAll();
            items2 = ejbFacade.ListaBaja();
        }
        return items2;
    }

    public void setItems2(List<VideoAdornos> items2) {
        this.items2 = items2;
    }

    public VideoAdornosController() {
    }

    public VideoAdornos getSelected() {
        return selected;
    }

    public void setSelected(VideoAdornos selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private VideoAdornosFacade getFacade() {
        return ejbFacade;
    }

    public VideoAdornos prepareCreate() {
        selected = new VideoAdornos();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        selected.setStatus(1);
        selected.setRuta(aux);
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void Almacenavideo() {
        System.out.println("MIME TIPE: " + getVideo().getContentType());
        System.out.println("Tamaño: " + getVideo().getSize());
        System.out.println("Extensión mov: " + getVideo().getFileName().endsWith(".mov"));
        System.out.println("Extensión jpg: " + getVideo().getFileName().endsWith(".jpg"));
        System.out.println("Extensión mp4: " + getVideo().getFileName().endsWith(".mp4"));

        if (getVideo().getFileName().endsWith(".mov") || getVideo().getFileName().endsWith(".jpg") || getVideo().getFileName().endsWith(".mp4")) {
            //insertar
            if(SubirArchivo()){
                create();
                aux="";
                
            }
        } else {
            //error
            FacesMessage mensaje = new FacesMessage("El archivo no es video");
            FacesContext.getCurrentInstance().addMessage(null, mensaje);
            selected = null;
        }
    }
    
    public Boolean SubirArchivo(){
        try{
            aux="resources/videoAdornos";
            File archivo= new File(JsfUtil.getPath()+aux);
            if(!archivo.exists()){
                archivo.mkdirs();
            }
            copiar_archivo(getVideo().getFileName(),getVideo().getInputstream());
            return true;
        }catch(Exception e){
            return false;
             
        }
    }
    
    public void copiar_archivo(String nombre, InputStream in) throws FileNotFoundException{
        aux=aux+"/"+nombre;
        OutputStream out = new FileOutputStream(new File(JsfUtil.getPath()+aux));
        int read=0;
        byte[] bytes= new byte[1024];
        try {
            while((read=in.read(bytes))!=-1){
                out.write(bytes,0,read);
                
            }
            System.out.println(aux);
            aux=aux.substring(9);
            in.close();
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(VideoAdornosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosUpdated"));
        items = null;
    }

    public void destroy() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosDeleted"));
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void restaurar() {
        //persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosDeleted"));
        selected.setStatus(0);
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("VideoAdornosUpdated"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
            items2 = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<VideoAdornos> getItems() {
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

    public VideoAdornos getVideoAdornos(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<VideoAdornos> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<VideoAdornos> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = VideoAdornos.class)
    public static class VideoAdornosControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VideoAdornosController controller = (VideoAdornosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "videoAdornosController");
            return controller.getVideoAdornos(getKey(value));
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
            if (object instanceof VideoAdornos) {
                VideoAdornos o = (VideoAdornos) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), VideoAdornos.class.getName()});
                return null;
            }
        }

    }

}
