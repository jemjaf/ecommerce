package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

public class Producto {
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

    public Producto() {}

    public Producto(Integer id, String nombre, String descripcion, String imagen,
                    Double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio = precio;
        this.cantidad = cantidad;
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
