package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Producto;
import com.springecommerce.ecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String Home(Model model){
        model.addAttribute("productos", productoService.finAll());
        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(Model model ,@PathVariable Integer id){
        Producto producto = new Producto();
        producto = productoService.get(id).get();
        model.addAttribute("producto", producto);
        return  "usuario/productohome";
    }


}
