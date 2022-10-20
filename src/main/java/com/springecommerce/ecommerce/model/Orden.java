package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Orden {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String numero;
    @Getter @Setter
    private Date fechaCreacion;
    @Getter @Setter
    private Date fechaRecibida;
    @Getter @Setter
    private Double total;

    public Orden(){}

    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, Double total) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Orden{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaRecibida=" + fechaRecibida +
                ", total=" + total +
                '}';
    }
}
