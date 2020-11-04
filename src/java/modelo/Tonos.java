/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author bmora
 */
@Entity
@Table(name = "tonos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tonos.findAll", query = "SELECT t FROM Tonos t")
    , @NamedQuery(name = "Tonos.findById", query = "SELECT t FROM Tonos t WHERE t.id = :id")
    , @NamedQuery(name = "Tonos.findByActivos", query = "SELECT t FROM Tonos t WHERE t.status = 1")
    , @NamedQuery(name = "Tonos.findByBaja", query = "SELECT t FROM Tonos t WHERE t.status = 0")
    , @NamedQuery(name = "Tonos.findByNombreLatino", query = "SELECT t FROM Tonos t WHERE t.nombreLatino = :nombreLatino")
    , @NamedQuery(name = "Tonos.findByNombreIngles", query = "SELECT t FROM Tonos t WHERE t.nombreIngles = :nombreIngles")
    , @NamedQuery(name = "Tonos.findByStatus", query = "SELECT t FROM Tonos t WHERE t.status = :status")})
public class Tonos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre_latino")
    private String nombreLatino;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre_ingles")
    private String nombreIngles;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTono")
    private Collection<Canciones> cancionesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTono")
    private Collection<Acordeones> acordeonesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTonoInstruccional")
    private Collection<Instruccionales> instruccionalesCollection;

    public Tonos() {
    }

    public Tonos(Integer id) {
        this.id = id;
    }

    public Tonos(Integer id, String nombreLatino, String nombreIngles, int status) {
        this.id = id;
        this.nombreLatino = nombreLatino;
        this.nombreIngles = nombreIngles;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreLatino() {
        return nombreLatino;
    }

    public void setNombreLatino(String nombreLatino) {
        this.nombreLatino = nombreLatino;
    }

    public String getNombreIngles() {
        return nombreIngles;
    }

    public void setNombreIngles(String nombreIngles) {
        this.nombreIngles = nombreIngles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Canciones> getCancionesCollection() {
        return cancionesCollection;
    }

    public void setCancionesCollection(Collection<Canciones> cancionesCollection) {
        this.cancionesCollection = cancionesCollection;
    }

    @XmlTransient
    public Collection<Acordeones> getAcordeonesCollection() {
        return acordeonesCollection;
    }

    public void setAcordeonesCollection(Collection<Acordeones> acordeonesCollection) {
        this.acordeonesCollection = acordeonesCollection;
    }

    @XmlTransient
    public Collection<Instruccionales> getInstruccionalesCollection() {
        return instruccionalesCollection;
    }

    public void setInstruccionalesCollection(Collection<Instruccionales> instruccionalesCollection) {
        this.instruccionalesCollection = instruccionalesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tonos)) {
            return false;
        }
        Tonos other = (Tonos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreLatino+"/"+nombreIngles;
    }
    
}
