package com.springecommerce.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

public class Usuario {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String username;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String direccion;
    @Getter @Setter
    private String telefono;
    @Getter @Setter
    private String tipo;
    @Getter @Setter
    private String password;

    public Usuario(){}

    public Usuario(Integer id, String nombre, String username,
                   String email, String direccion, String telefono,
                   String tipo, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.email = email;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipo = tipo;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", tipo='" + tipo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
