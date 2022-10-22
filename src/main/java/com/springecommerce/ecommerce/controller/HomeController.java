package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.DetalleOrden;
import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Producto;
import com.springecommerce.ecommerce.service.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private ProductoService productoService;

    //Para el carrito
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

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

    @PostMapping("/carrito")
    public String addCart(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad, Model model){

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double total = 0;

        producto = productoService.get(id).get();
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(producto.getPrecio()*cantidad);
        detalleOrden.setProducto(producto);

        detalles.add(detalleOrden);

        total = detalles.stream().mapToDouble(dt-> dt.getTotal()).sum();
        orden.setTotal(total);

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

}
