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

        //No repetir productos
        Integer idProducto=producto.getId();
        boolean existeProducto = detalles.stream().anyMatch(p -> p.getProducto().getId()==idProducto);

        if (!existeProducto) {
            detalles.add(detalleOrden);
        }


        total = detalles.stream().mapToDouble(dt-> dt.getTotal()).sum();
        orden.setTotal(total);

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/putcart/{id}")
    public String putCart(@PathVariable Integer id, Model model){

        List<DetalleOrden> ordenesNuevas = new ArrayList<DetalleOrden>();

        for (DetalleOrden detalleOrden : detalles){
            if (detalleOrden.getProducto().getId()!=id){
                ordenesNuevas.add(detalleOrden);
            }
        }
        detalles = ordenesNuevas;

        double total =0;
        total = detalles.stream().mapToDouble(dt -> dt.getTotal()).sum();
        orden.setTotal(total);

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("/getCarrito")
    public String getCarrito(Model model) {

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);

        //sesion
        //model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/carrito";
    }

    @GetMapping("/orden")
    public String orden() {



        return "usuario/resumenorden";
    }
//
//    // guardar la orden
//    @GetMapping("/saveOrder")
//    public String saveOrder(HttpSession session ) {
//        Date fechaCreacion = new Date();
//        orden.setFechaCreacion(fechaCreacion);
//        orden.setNumero(ordenService.generarNumeroOrden());
//
//        //usuario
//        Usuario usuario =usuarioService.findById( Integer.parseInt(session.getAttribute("idusuario").toString())  ).get();
//
//        orden.setUsuario(usuario);
//        ordenService.save(orden);
//
//        //guardar detalles
//        for (DetalleOrden dt:detalles) {
//            dt.setOrden(orden);
//            detalleOrdenService.save(dt);
//        }
//
//        ///limpiar lista y orden
//        orden = new Orden();
//        detalles.clear();
//
//        return "redirect:/";
//    }
//
//    @PostMapping("/search")
//    public String searchProduct(@RequestParam String nombre, Model model) {
//        log.info("Nombre del producto: {}", nombre);
//        List<Producto> productos= productoService.findAll().stream().filter( p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
//        model.addAttribute("productos", productos);
//        return "usuario/home";
//    }

}
