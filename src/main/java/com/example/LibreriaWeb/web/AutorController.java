package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.service.AutorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("autor")
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorServiceImpl autorService;

    @GetMapping("/listar")
    public String listarAutores(Model model){
        model.addAttribute("titulo","Lista De Autores");
        model.addAttribute("listaAutores",autorService.litar());
        return "listar-autores";
    }

    @GetMapping("/form")
    public String crearAutor(Model model){
        model.addAttribute("titulo","Fromulario de Autor");
        model.addAttribute("autor", new Autor());

        return "form-autor";
    }

    @PostMapping("/form")
    public String guardar(Autor autor, SessionStatus status){
        status.setComplete();
        autorService.guardar(autor);
        return "redirect:/autor/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String elimarAutor(@PathVariable("id")Integer id){
            Autor autor = new Autor();
            autor.setId(id);
           if(autorService.encontrar(autor) != null){
               autorService.eliminar(autor);
           }
        return "redirect:/autor/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editarAutor(@PathVariable("id")Integer id,Model model){
        Autor autor = new Autor();
        autor.setId(id);
        if(autorService.encontrar(autor) != null){
            model.addAttribute("autor", autorService.encontrar(autor));
            model.addAttribute("titulo","Fromulario de Autor");
            return "form-autor";
        }
        return "redirect:/autor/listar";
    }
}
