package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Usuario;

import java.util.Optional;

public interface IUsuarioService {

    Optional<Usuario> findById(Integer Id);

    Usuario save(Usuario usuario);
}
