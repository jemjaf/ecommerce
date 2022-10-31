package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/register")
    public String register(){
        return "usuario/registro";
    }

    @PostMapping("/save")
    public String save(Usuario usuario){
        usuario.setTipo("USER");
        iUsuarioService.save(usuario);
        return "redirect:/";
    }
}
