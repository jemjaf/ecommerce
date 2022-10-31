package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/registro")
    public String register(){
        return "usuario/registro";
    }
}
