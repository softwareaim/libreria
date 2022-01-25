
package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Usuario;
import com.example.LibreriaWeb.errores.ErrorServicio;
import com.example.LibreriaWeb.service.UsuarioService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @GetMapping("/listar")
    public String listarUsuarios(Model model){
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuarios", usuarioService.listarUsuario());
    
        return"listar-usuarios";
    }
    
    @GetMapping("/registro")
    public String registro(){
    return "form-registro";
    }
    
    
    @GetMapping("/guardar")
    public String guardarUsuario(Model model,@RequestParam(required = true) String username,
            @RequestParam(required = true) String password,@RequestParam(required = true) String password2){
        try {
            usuarioService.guardar(username, password, password2);
            model.addAttribute("succes", "Usuario guardado exitosamente");
            return"redirect:/usuario/listar";
        } catch (ErrorServicio ex) {
            model.addAttribute("error", ex.getMessage());
            return "form-registro";
        }
    }
    
}
