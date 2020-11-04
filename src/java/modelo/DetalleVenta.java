/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bmora
 */
@Entity
@Table(name = "detalle_venta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetalleVenta.findAll", query = "SELECT d FROM DetalleVenta d")
    , @NamedQuery(name = "DetalleVenta.findById", query = "SELECT d FROM DetalleVenta d WHERE d.id = :id")
    , @NamedQuery(name = "DetalleVenta.findByActivos", query = "SELECT d FROM DetalleVenta d WHERE d.status = 1")
    , @NamedQuery(name = "DetalleVenta.findByBAja", query = "SELECT d FROM DetalleVenta d WHERE d.status = 0")
    , @NamedQuery(name = "DetalleVenta.findByCantidad", query = "SELECT d FROM DetalleVenta d WHERE d.cantidad = :cantidad")
    , @NamedQuery(name = "DetalleVenta.findByPrecioVenta", query = "SELECT d FROM DetalleVenta d WHERE d.precioVenta = :precioVenta")
    , @NamedQuery(name = "DetalleVenta.findByStatus", query = "SELECT d FROM DetalleVenta d WHERE d.status = :status")})
public class DetalleVenta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidad")
    private int cantidad;
    @Basic(optional = false)
    @NotNull
    @Column(name = "precio_venta")
    private double precioVenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "id_venta", referencedColumnName = "id")
    @ManyToOne
    private Ventas idVenta;
    @JoinColumn(name = "id_instruccional", referencedColumnName = "id")
    @ManyToOne
    private Instruccionales idInstruccional;

    public DetalleVenta() {
    }

    public DetalleVenta(Integer id) {
        this.id = id;
    }

    public DetalleVenta(Integer id, int cantidad, double precioVenta, int status) {
        this.id = id;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public Ventas getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Ventas idVenta) {
        this.idVenta = idVenta;
    }

    public Instruccionales getIdInstruccional() {
        return idInstruccional;
    }

    public void setIdInstruccional(Instruccionales idInstruccional) {
        this.idInstruccional = idInstruccional;
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
        if (!(object instanceof DetalleVenta)) {
            return false;
        }
        DetalleVenta other = (DetalleVenta) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return cantidad + " por " + precioVenta;
    }
    
}
