package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.errores.ErrorServicio;
import com.example.LibreriaWeb.service.AutorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
        model.addAttribute("listaAutores",autorService.listar());

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
    public String elimarAutor(@PathVariable("id")Integer id, ModelMap model){

        try {
            autorService.eliminar(id);
        } catch (ErrorServicio e) {
            model.put("errorAutor",e.getMessage());
            return "redirect:/autor/listar";
        }finally {
            return "redirect:/autor/listar";
        }

    }

    @RequestMapping("/editar/{id}")
    public String editarAutor(@PathVariable("id")Integer id,Model model){
        try {
            model.addAttribute("autor", autorService.encontrar(id));
            model.addAttribute("titulo","Fromulario de Autor");
            return "form-autor";
        } catch (ErrorServicio e) {
            model.addAttribute("errorAutor",e.getMessage());
            return "redirect:/autor/listar";
        }

    }
}
