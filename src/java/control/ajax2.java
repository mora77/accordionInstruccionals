/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import modelo.Entidades;
import modelo.Municipios;
import modelo.Paises;

/**
 *
 * @author bmora
 */
@Named(value = "ajax2")
@RequestScoped
public class ajax2 {

    @EJB
    private control.PaisesFacade paisesFacade;

    @EJB
    private control.EntidadesFacade entidadesFacade;

    @EJB
    private control.MunicipiosFacade municipiosFacade;

    private int id_pais;
    private int id_entidad;
    private int id_municipio;

    private List<Paises> listaPaises;
    private List<SelectItem> listPaises;
    private List<SelectItem> listaEntidades;
    private List<SelectItem> listaMunicipios;

    public List<SelectItem> getListPaises() {
        return listPaises;
    }

    public void setListPaises(List<SelectItem> listPaises) {
        this.listPaises = listPaises;
    }

    
    
    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public int getId_entidad() {
        return id_entidad;
    }

    public void setId_entidad(int id_entidad) {
        this.id_entidad = id_entidad;
    }

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public List<Paises> getListaPaises() {
        return listaPaises;
    }

    public void setListaPaises(List<Paises> listaPaises) {
        this.listaPaises = listaPaises;
    }

    public List<SelectItem> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<SelectItem> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }

    public List<SelectItem> getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List<SelectItem> listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }
    
    
    public List<SelectItem> buscaEntidades(final AjaxBehaviorEvent event){
        listaEntidades=new ArrayList<SelectItem>();
        List<Entidades> le = entidadesFacade.Buscar(id_pais);
        listaEntidades.clear();
        for(Entidades e: le){
            SelectItem entidad= new SelectItem(e.getId(),e.getNombre());
            listaEntidades.add(entidad);
        }
        FacesContext.getCurrentInstance().renderResponse();
        return listaEntidades;
    }
    
    public List<SelectItem> buscaMunicipios(final AjaxBehaviorEvent event){
        listaMunicipios=new ArrayList<SelectItem>();
        List<Municipios> lm = municipiosFacade.Buscar(id_entidad);
        listaMunicipios.clear();
        for(Municipios m: lm){
            SelectItem municipio= new SelectItem(m.getId(),m.getNombre());
            listaMunicipios.add(municipio);
        }
        FacesContext.getCurrentInstance().renderResponse();
        return listaMunicipios;
    }
    
    public List<SelectItem> buscaMunicipios2(final AjaxBehaviorEvent event){
        listaMunicipios=new ArrayList<SelectItem>();
        List<Municipios> lm = municipiosFacade.Buscar(id_entidad);
        listaMunicipios.clear();
        for(Municipios m: lm){
            SelectItem municipio= new SelectItem(m.getId(),m.getNombre());
            listaMunicipios.add(municipio);
        }
        FacesContext.getCurrentInstance().renderResponse();
        return listaMunicipios;
    }
    

    /**
     * Creates a new instance of ajax2
     */
    public ajax2() {
    }

    @PostConstruct
    private void initializate() {
       /* List<Paises> lp = paisesFacade.findAll();
        listaPaises.clear();
        for (Paises p:lp ) {
            listaPaises.add(p);
        }
        */
       
        listPaises = new ArrayList<SelectItem>();
        List<Paises> lp = paisesFacade.findAll();
        listPaises.clear();
        for(Paises p: lp){
            SelectItem pais= new SelectItem(p.getId(),p.getNombre());
            listPaises.add(pais);
        }
        
        listaEntidades = new ArrayList<SelectItem>();
        List<Entidades> le = entidadesFacade.findAll();
        listaEntidades.clear();
        for(Entidades e: le){
            SelectItem entidad= new SelectItem(e.getId(),e.getNombre());
            listaEntidades.add(entidad);
        }
    }

}
