package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.DetalleOrden;
import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Producto;
import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.service.IDetalleOrdenService;
import com.springecommerce.ecommerce.service.IOrdenService;
import com.springecommerce.ecommerce.service.IProductoService;
import com.springecommerce.ecommerce.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private IProductoService IProductoService;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private IOrdenService iOrdenService;
    @Autowired
    private IDetalleOrdenService iDetalleOrdenService;

    //Para el carrito
    List<DetalleOrden> detalles = new ArrayList<DetalleOrden>();

    Orden orden = new Orden();

    @GetMapping("")
    public String Home(Model model){
        model.addAttribute("productos", IProductoService.finAll());
        return "usuario/home";
    }

    @GetMapping("/productohome/{id}")
    public String productoHome(Model model ,@PathVariable Integer id){
        Producto producto = new Producto();
        producto = IProductoService.get(id).get();
        model.addAttribute("producto", producto);
        return  "usuario/productohome";
    }

    @PostMapping("/carrito")
    public String addCart(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad, Model model){

        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = new Producto();
        double total = 0;

        producto = IProductoService.get(id).get();
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

        return "usuario/carrito";
    }

    @GetMapping("/orden")
    public String orden(Model model) {

        Usuario usuario = iUsuarioService.findById(1).get();

        model.addAttribute("carrito", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "usuario/resumenorden";
    }

    @GetMapping("/saveorden")
    public  String saveOrden(){

        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(iOrdenService.generarNumeroOrden());
        //Usuario que hace la orden
        orden.setUsuario(iUsuarioService.findById(1).get());
        //Guardar Orden
        iOrdenService.save(orden);
        //Guardar Detalles
        for (DetalleOrden dt:detalles){
            dt.setOrden(orden);
            iDetalleOrdenService.save(dt);
        }
        //Limpiar lo ya guardado
        orden = new Orden();
        detalles.clear();
        return "redirect:/";
    }

    @PostMapping("/search")
    public String search(@RequestParam String search, Model model){
        model.addAttribute("productos", IProductoService.finAll().stream().filter(p -> p.getNombre().contains(search)).collect(Collectors.toList()));
        return "usuario/home";
    }
}
