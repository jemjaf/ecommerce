package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ordenes")
public class Orden {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Getter @Setter @ManyToOne
    private Usuario usuario;
    @OneToOne(mappedBy = "orden")
    @Getter @Setter
    private DetalleOrden detalle;

    public Orden(){}

    public Orden(Integer id, String numero, Date fechaCreacion, Date fechaRecibida, Double total, Usuario usuario) {
        this.id = id;
        this.numero = numero;
        this.fechaCreacion = fechaCreacion;
        this.fechaRecibida = fechaRecibida;
        this.total = total;
        this.usuario = usuario;
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
