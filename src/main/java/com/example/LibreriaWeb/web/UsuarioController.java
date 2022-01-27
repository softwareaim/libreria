package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Usuario;
import com.example.LibreriaWeb.errores.ErrorServicio;
import com.example.LibreriaWeb.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("titulo", "Lista de Usuarios");
        model.addAttribute("usuarios", usuarioService.listarUsuario());
        model.addAttribute("usuario", new Usuario());
        return "listar-usuarios";
    }

    @GetMapping("/registro")
    public String registro(Usuario usuario) {
        return "form-registro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(Model model, Usuario usuario, @RequestParam(required = true) String password2) {

        try {
            usuarioService.guardar(usuario, password2);
            model.addAttribute("succes", "Usuario guardado exitosamente");
            return "redirect:/usuario/listar";
        } catch (ErrorServicio ex) {
            model.addAttribute("error", ex.getMessage());
            return "form-registro";
        }
    }

    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") Integer id, Model model) {

        model.addAttribute("usuario", usuarioService.buscarUsuarioPorId(id));
        model.addAttribute("titulo", "Fromulario de Usuario");
        return "form-registro";

    }

  //  @PreAuthorize("hasAuthority('CACA')")
    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {

        usuarioService.eliminar(id);
        return "redirect:/usuario/listar";

    }

}
