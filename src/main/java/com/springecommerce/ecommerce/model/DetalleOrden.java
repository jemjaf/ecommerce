package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "detalles")
public class DetalleOrden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private Integer cantidad;
    @Getter @Setter
    private double precio;
    @Getter @Setter
    private double total;
    @Getter @Setter @ManyToOne
    private Orden orden;
    @Getter @Setter @ManyToOne
    private Producto producto;
    public DetalleOrden(){}

    public DetalleOrden(Integer id, String nombre, Integer cantidad,
                        Double precio, Double total) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleOrden{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", precio=" + precio +
                ", total=" + total +
                '}';
    }
}
