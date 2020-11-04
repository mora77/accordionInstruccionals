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
@Table(name = "tipos_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposVenta.findAll", query = "SELECT t FROM TiposVenta t")
    , @NamedQuery(name = "TiposVenta.findById", query = "SELECT t FROM TiposVenta t WHERE t.id = :id")
    , @NamedQuery(name = "TiposVenta.findByActivos", query = "SELECT t FROM TiposVenta t WHERE t.status = 1")
    , @NamedQuery(name = "TiposVenta.findByBaja", query = "SELECT t FROM TiposVenta t WHERE t.status = 0")
    , @NamedQuery(name = "TiposVenta.findByNombre", query = "SELECT t FROM TiposVenta t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "TiposVenta.findByStatus", query = "SELECT t FROM TiposVenta t WHERE t.status = :status")})
public class TiposVenta implements Serializable {

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
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoVenta")
    private Collection<Instruccionales> instruccionalesCollection;

    public TiposVenta() {
    }

    public TiposVenta(Integer id) {
        this.id = id;
    }

    public TiposVenta(Integer id, String nombre, int status) {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        if (!(object instanceof TiposVenta)) {
            return false;
        }
        TiposVenta other = (TiposVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
