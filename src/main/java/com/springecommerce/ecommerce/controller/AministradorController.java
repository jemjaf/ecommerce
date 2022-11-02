package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Producto;
import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.service.IOrdenService;
import com.springecommerce.ecommerce.service.IProductoService;
import com.springecommerce.ecommerce.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller @RequestMapping("/administrador")
public class AministradorController {
    @Autowired
    private IProductoService iProductoService;

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private IOrdenService iOrdenService;

    //Vista de los productos
    @GetMapping("")
    public String productos(Model model){
        List<Producto> productos = iProductoService.findAll();

        model.addAttribute("productos", productos);
        return "administrador/home";
    }

    //Muestra el mantenedor de los usuarios
    @GetMapping("/users")
    public String users(Model model){
        List<Usuario> usuarios = iUsuarioService.findAll();

        model.addAttribute("usuarios", usuarios);
        return "administrador/usuarios";
    }

    //Muestra el mantenedor de las ordenes
    @GetMapping("/orders")
    public String orders(Model model){

        model.addAttribute("ordenes", iOrdenService.findAll());
        return "administrador/ordenes";
    }

    //Muestra el detalle de alguna orden en especifico
    @GetMapping("/detalle/{idOrden}")
    public String detalle(@PathVariable Integer idOrden, Model model){
        Orden orden = iOrdenService.findById(idOrden).get();

        model.addAttribute("detalles", orden.getDetalle());

        return "administrador/detalleorden";

    }
}
