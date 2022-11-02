package com.springecommerce.ecommerce.service;


import com.springecommerce.ecommerce.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    HttpSession httpSession;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = iUsuarioService.findByEmail(username);

        if (optionalUsuario.isPresent()){
            Usuario user = optionalUsuario.get();
            httpSession.setAttribute("idUsuario", user.getId());

            return User.builder().username(user.getEmail()).password(user.getPassword())
                    .roles(user.getTipo()).build();
        }else{
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }
}
