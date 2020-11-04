/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "instruccionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Instruccionales.findAll", query = "SELECT i FROM Instruccionales i")
    , @NamedQuery(name = "Instruccionales.findById", query = "SELECT i FROM Instruccionales i WHERE i.id = :id")
    , @NamedQuery(name = "Instruccionales.findByActivos", query = "SELECT i FROM Instruccionales i WHERE i.status = 1")
    , @NamedQuery(name = "Instruccionales.findByBaja", query = "SELECT i FROM Instruccionales i WHERE i.status = 0")
    , @NamedQuery(name = "Instruccionales.findByNombre", query = "SELECT i FROM Instruccionales i WHERE i.nombre = :nombre")
    , @NamedQuery(name = "Instruccionales.findByDescripcion", query = "SELECT i FROM Instruccionales i WHERE i.descripcion = :descripcion")
    , @NamedQuery(name = "Instruccionales.findByExistencia", query = "SELECT i FROM Instruccionales i WHERE i.existencia = :existencia")
    , @NamedQuery(name = "Instruccionales.findByPrecioVenta", query = "SELECT i FROM Instruccionales i WHERE i.precioVenta = :precioVenta")
    , @NamedQuery(name = "Instruccionales.findByStatus", query = "SELECT i FROM Instruccionales i WHERE i.status = :status")})
public class Instruccionales implements Serializable {

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
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "existencia")
    private int existencia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private double precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @OneToMany(mappedBy = "idInstruccional")
    private Collection<VideoPuente> videoPuenteCollection;
    @OneToMany(mappedBy = "idInstruccional")
    private Collection<DetalleVenta> detalleVentaCollection;
    @JoinColumn(name = "id_tipo_venta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TiposVenta idTipoVenta;
    @JoinColumn(name = "id_cancion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Canciones idCancion;
    @JoinColumn(name = "id_tono_instruccional", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Tonos idTonoInstruccional;
    @JoinColumn(name = "id_acordeon", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Acordeones idAcordeon;
    @OneToMany(mappedBy = "idInstruccional")
    private Collection<VideoAdornos> videoAdornosCollection;

    public Instruccionales() {
    }

    public Instruccionales(Integer id) {
        this.id = id;
    }

    public Instruccionales(Integer id, String nombre, String descripcion, int existencia, double precioVenta, int status) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.existencia = existencia;
        this.precioVenta = precioVenta;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<VideoPuente> getVideoPuenteCollection() {
        return videoPuenteCollection;
    }

    public void setVideoPuenteCollection(Collection<VideoPuente> videoPuenteCollection) {
        this.videoPuenteCollection = videoPuenteCollection;
    }

    @XmlTransient
    public Collection<DetalleVenta> getDetalleVentaCollection() {
        return detalleVentaCollection;
    }

    public void setDetalleVentaCollection(Collection<DetalleVenta> detalleVentaCollection) {
        this.detalleVentaCollection = detalleVentaCollection;
    }

    public TiposVenta getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(TiposVenta idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }

    public Canciones getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(Canciones idCancion) {
        this.idCancion = idCancion;
    }

    public Tonos getIdTonoInstruccional() {
        return idTonoInstruccional;
    }

    public void setIdTonoInstruccional(Tonos idTonoInstruccional) {
        this.idTonoInstruccional = idTonoInstruccional;
    }

    public Acordeones getIdAcordeon() {
        return idAcordeon;
    }

    public void setIdAcordeon(Acordeones idAcordeon) {
        this.idAcordeon = idAcordeon;
    }

    @XmlTransient
    public Collection<VideoAdornos> getVideoAdornosCollection() {
        return videoAdornosCollection;
    }

    public void setVideoAdornosCollection(Collection<VideoAdornos> videoAdornosCollection) {
        this.videoAdornosCollection = videoAdornosCollection;
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
        if (!(object instanceof Instruccionales)) {
            return false;
        }
        Instruccionales other = (Instruccionales) object;
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
