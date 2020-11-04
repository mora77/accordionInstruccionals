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
@Table(name = "acordeones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acordeones.findAll", query = "SELECT a FROM Acordeones a")
    , @NamedQuery(name = "Acordeones.findByActivos", query = "SELECT a FROM Acordeones a WHERE a.status = 1")
    , @NamedQuery(name = "Acordeones.findByBaja", query = "SELECT a FROM Acordeones a WHERE a.status = 0")
    , @NamedQuery(name = "Acordeones.findById", query = "SELECT a FROM Acordeones a WHERE a.id = :id")
    , @NamedQuery(name = "Acordeones.findByNombre", query = "SELECT a FROM Acordeones a WHERE a.nombre = :nombre")
    , @NamedQuery(name = "Acordeones.findByMarca", query = "SELECT a FROM Acordeones a WHERE a.marca = :marca")
    , @NamedQuery(name = "Acordeones.findByStatus", query = "SELECT a FROM Acordeones a WHERE a.status = :status")})
public class Acordeones implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_tono", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tonos idTono;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAcordeon")
    private Collection<Instruccionales> instruccionalesCollection;

    public Acordeones() {
    }

    public Acordeones(Integer id) {
        this.id = id;
    }

    public Acordeones(Integer id, String nombre, String marca, int status) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
        if (!(object instanceof Acordeones)) {
            return false;
        }
        Acordeones other = (Acordeones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre+" De la marca "+marca;
    }
    
}
