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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "canciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Canciones.findAll", query = "SELECT c FROM Canciones c")
    , @NamedQuery(name = "Canciones.findById", query = "SELECT c FROM Canciones c WHERE c.id = :id")
    , @NamedQuery(name = "Canciones.findByActivos", query = "SELECT c FROM Canciones c WHERE c.status = 1")
    , @NamedQuery(name = "Canciones.findByBaja", query = "SELECT c FROM Canciones c WHERE c.status = 0")
    , @NamedQuery(name = "Canciones.findByNombre", query = "SELECT c FROM Canciones c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Canciones.findByArtista", query = "SELECT c FROM Canciones c WHERE c.artista = :artista")
    , @NamedQuery(name = "Canciones.findByStatus", query = "SELECT c FROM Canciones c WHERE c.status = :status")})
public class Canciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 80)
    @Column(name = "artista")
    private String artista;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_tono", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tonos idTono;
    @JoinColumn(name = "id_categoria", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Categorias idCategoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCancion")
    private Collection<Instruccionales> instruccionalesCollection;

    public Canciones() {
    }

    public Canciones(Integer id) {
        this.id = id;
    }

    public Canciones(Integer id, String nombre, int status) {
        this.id = id;
        this.nombre = nombre;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Tonos getIdTono() {
        return idTono;
    }

    public void setIdTono(Tonos idTono) {
        this.idTono = idTono;
    }

    public Categorias getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
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
        if (!(object instanceof Canciones)) {
            return false;
        }
        Canciones other = (Canciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre +" del artista " + artista;
    }
    
}
