package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private String imagen;
    @Getter @Setter
    private Double precio;
    @Getter @Setter
    private int cantidad;
    @ManyToOne
    @Getter @Setter
    private Usuario usuario;

    public Producto() {}

    public Producto(Integer id, String nombre, String descripcion, String imagen,
                    Double precio, int cantidad, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
