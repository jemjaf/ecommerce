package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IOrdenService {

    List<Orden> findAll();
    Optional<Orden> findById(Integer id);
    Orden save(Orden orden);
    String generarNumeroOrden();

    List<Orden> findByUsuario(Usuario usuario);
}
