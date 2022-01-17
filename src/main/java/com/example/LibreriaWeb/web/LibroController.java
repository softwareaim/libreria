package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Cliente;
import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.domain.Prestamo;
import com.example.LibreriaWeb.service.LibroServiceImpl;
import com.example.LibreriaWeb.service.PrestamoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("libro")
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServiceImpl libroService;

    @GetMapping("/listar")
    public String listarLibros(Model model) {
        model.addAttribute("titulo","Lista de Libros");
        model.addAttribute("libros", libroService.litar());
        model.addAttribute("libro",new Libro());
        return "listar-libros";
    }

    @PostMapping("/form")
    public String guardar(Libro libro, SessionStatus status){
        status.setComplete();

        libroService.guardar(libro);
        return "redirect:/libro/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        Libro libro = new Libro();
        libro.setId(id);
        if (libroService.encontrar(libro) != null) {
            model.addAttribute("libro", libroService.encontrar(libro));
            model.addAttribute("msj", "Fromulario de libros");
            return "form-libro";
        }
        return "redirect:/libro/listar";
    }


}
