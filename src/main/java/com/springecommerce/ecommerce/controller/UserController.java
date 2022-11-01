package com.springecommerce.ecommerce.controller;

import com.springecommerce.ecommerce.model.Usuario;
import com.springecommerce.ecommerce.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUsuarioService iUsuarioService;

    //Mostrar Vista Registarse
    @GetMapping("/register")
    public String register(){
        return "usuario/registro";
    }

    //Registrar Usuario
    @PostMapping("/save")
    public String save(Usuario usuario){
        usuario.setTipo("USER");
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
    public String login(Usuario usuario, HttpSession httpSession){
        Optional<Usuario> user = iUsuarioService.findByEmail(usuario.getEmail());
        //LOGGER.info("XDDDD {}", user.get());

        if (user.isPresent()){
            httpSession.setAttribute("idUsuario", user.get().getId());
            if (user.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }else{
                return "redirect:/";
            }
        }else{
            LOGGER.info("F mano, no estás en lista");
        }

        return "redirect:/";
    }

    @GetMapping("/compras")
    public String compras(Model model, HttpSession httpSession){
        model.addAttribute("idUsuario", httpSession.getAttribute("idUsuario"));
        return "usuario/compras";
    }
}
