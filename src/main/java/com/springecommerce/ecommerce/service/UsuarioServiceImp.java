package com.springecommerce.ecommerce.service;

import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements IUsuarioService {

    @Autowired
    private IUsuarioRepository IUsuarioRepository;

    @Override
    public Optional<Usuario> findById(Integer Id) {
        return IUsuarioRepository.findById(Id);
    }
}
