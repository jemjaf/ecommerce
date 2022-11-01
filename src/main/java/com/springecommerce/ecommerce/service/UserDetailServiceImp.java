package com.springecommerce.ecommerce.service;


import com.springecommerce.ecommerce.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private IUsuarioService iUsuarioService;

//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    HttpSession httpSession;

    private Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImp.class);

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> optionalUsuario = iUsuarioService.findByEmail(email);

        if (optionalUsuario.isPresent()){
            httpSession.setAttribute("idUsuario", optionalUsuario.get().getId());
            Usuario user = optionalUsuario.get();
            return User.builder().username(user.getNombre()).password(bCryptPasswordEncoder.encode(user.getPassword())).roles(user.getTipo()).build();
        }
        return null;
    }
}
