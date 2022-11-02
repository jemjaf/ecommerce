package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Orden;
import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.service.IOrdenService;
import com.springecommerce.ecommerce.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUsuarioService iUsuarioService;

    @Autowired
    private IOrdenService iOrdenService;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    //Mostrar Vista Registarse
    @GetMapping("/register")
    public String register(){
        return "usuario/registro";
    }

    //Registrar Usuario
    @PostMapping("/save")
    public String save(Usuario usuario){
        usuario.setTipo("USER");
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        iUsuarioService.save(usuario);
        return "redirect:/";
    }

    //Mostrar Vista Login
    @GetMapping("/viewlogin")
    public String viewlogin(){
        return "usuario/login";
    }

    //Iniciar sesión
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, HttpSession httpSession){
        Optional<Usuario> user = iUsuarioService.findByEmail(username);
        //LOGGER.info("XDDDD {}", user.get());

        if (user.isPresent()){
            httpSession.setAttribute("idUsuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            return "redirect:/user/viewlogin";
        }
    }

    @GetMapping("/compras")
    public String compras(Model model, HttpSession httpSession){
        Usuario user = iUsuarioService.findById(Integer.parseInt(httpSession.getAttribute("idUsuario").toString())).get();
        List<Orden> ordenes =  iOrdenService.findByUsuario(user);

        model.addAttribute("ordenes", ordenes);
        model.addAttribute("idUsuario", httpSession.getAttribute("idUsuario"));
        return "usuario/compras";
    }

    @GetMapping("/detalle/{idOrden}")
    public String detalle(@PathVariable Integer idOrden, Model model, HttpSession httpSession){
        Orden orden = iOrdenService.findById(idOrden).get();

        model.addAttribute("detalles", orden.getDetalle());
        model.addAttribute("idUsuario", httpSession.getAttribute("idUsuario"));

        return "usuario/detallecompra";

    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("idUsuario");
        return "redirect:/";
    }
}
