package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IOrdenService {

    List<Orden> findAll();
    Orden save(Orden orden);
    String generarNumeroOrden();

    List<Orden> findByUsuario(Usuario usuario);
}
