/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author bmora
 */
@Named(value = "ajax1")
@RequestScoped
public class ajax1 {
    
    @EJB
    private control.PaisesFacade ejbFacade;
    @EJB
    private control.UsuariosFacade usuariofacade;
    
    @EJB
    private control.UsuariosFacade ejbFacade1;
    private String nombre;
    private String nombre2;
    private int nuevo;
    private String mensaje;
    private String mensajeN;
    private String mensajeAP;
    private String mensajeAM;
    private String mensajeT;
    
    private String nuevoN;
    
    private String nuevoT;
    
    private String user;
    private String nuevoap;
    private String nuevoam;
    private String pass;
    private String sms;
    
    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
    
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMensajeN() {
        return mensajeN;
    }

    public void setMensajeN(String mensajeN) {
        this.mensajeN = mensajeN;
    }

    public String getMensajeAP() {
        return mensajeAP;
    }

    public void setMensajeAP(String mensajeAP) {
        this.mensajeAP = mensajeAP;
    }

    public String getMensajeAM() {
        return mensajeAM;
    }

    public void setMensajeAM(String mensajeAM) {
        this.mensajeAM = mensajeAM;
    }

    public String getMensajeT() {
        return mensajeT;
    }

    public void setMensajeT(String mensajeT) {
        this.mensajeT = mensajeT;
    }

    public String getNuevoN() {
        return nuevoN;
    }

    public void setNuevoN(String nuevoN) {
        this.nuevoN = nuevoN;
    }

    public String getNuevoap() {
        return nuevoap;
    }

    public void setNuevoap(String nuevoAP) {
        this.nuevoap = nuevoAP;
    }

    public String getNuevoam() {
        return nuevoam;
    }

    public void setNuevoam(String nuevoAM) {
        this.nuevoam = nuevoAM;
    }

    public String getNuevoT() {
        return nuevoT;
    }

    public void setNuevoT(String nuevoT) {
        this.nuevoT = nuevoT;
    }
    
    

    public int getNuevo() {
        return nuevo;
    }

    public void setNuevo(int nuevo) {
        this.nuevo = nuevo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
     public void CambiaNameU(int id){
        if (usuariofacade.Cambia_user(user,id).equals("ok")) {
            sms = "¡Actualizado con exito! cierra e incia sesión para ver reflejados los cambios";
        }else{
            sms = "Error al actualizar";
        }
    }
     
     public void CambiaAPU(int id){
        if (usuariofacade.Cambia_apu(nuevoap,id).equals("ok")) {
            sms = "¡Actualizado con exito! cierra e incia sesión para ver reflejados los cambios";
        }else{
            sms = "Error al actualizar";
        }
    }
     
     public void CambiaAMU(int id){
        if (usuariofacade.Cambia_amu(nuevoam,id).equals("ok")) {
            sms = "¡Actualizado con exito!cierra e incia sesión para ver reflejados los cambios";
        }else{
            sms = "Error al actualizar";
        }
    }
     
    
    public void CambiaStatus(){
       if (ejbFacade.CambiarStatus(nuevo,1).equals("OK")){
           mensaje = "STATUS ACTUALIZADO";
       }else{
           mensaje="ERROR AL ACTUALIZAR"; 
       }
        
    }
    
    public void CambiaStatusAP(){
       if (ejbFacade1.CambiarStatusAP(nuevoap,3).equals("OK")){
           mensaje = "Apellidp Paterno actualizadoo";
       }else{
           mensaje="ERROR AL ACTUALIZAR"; 
       }
        
    }
    
    public void CambiaStatusAM(){
       if (ejbFacade1.CambiarStatusAM(nuevoam,3).equals("OK")){
           mensaje = "Apellidp MAterno actualizadoo";
       }else{
           mensaje="ERROR AL ACTUALIZAR"; 
       }
        
    }
    
    public void CambiaStatusT(){
       if (ejbFacade1.CambiarStatusT(nuevoT,3).equals("OK")){
           mensaje = "Telefono actualizadoo";
       }else{
           mensaje="ERROR AL ACTUALIZAR"; 
       }
        
    }
    
    
    public void CambiaStatusN(){
       if (ejbFacade1.CambiarStatusN(nuevoN).equals("OK")){
           mensaje = "Nombre actualizadoo";
       }else{
           mensaje="ERROR AL ACTUALIZAR"; 
       }
        
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    public void CambiaPassUser(){
        if (usuariofacade.Cambia_pass(pass,3).equals("ok")) {
            sms = "Password Cambiado";
        }else{
            sms = "Error al cambiar password";
        }
    }
    
    public void CambiaNombreUser(){
        if (usuariofacade.Cambia_nombre(user,3).equals("ok")) {
            sms = "Password Cambiado";
        }else{
            sms = "Error al cambiar password";
        }
    }
    

    /**
     * Creates a new instance of ajax1
     */
    public ajax1() {
    }
    
}
