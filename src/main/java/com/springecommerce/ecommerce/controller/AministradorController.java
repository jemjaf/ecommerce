package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Producto;
import com.springecommerce.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller @RequestMapping("/administrador")
public class AministradorController {
    @Autowired
    private ProductoService productoService;
    @GetMapping("")
    public String Home(Model model){
        List<Producto> productos = productoService.finAll();

        model.addAttribute("productos", productos);
        return "administrador/home";
    }
}
