package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.domain.Editorial;
import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.errores.ErrorServicio;
import com.example.LibreriaWeb.service.AutorServiceImpl;
import com.example.LibreriaWeb.service.EditorialServiceImpl;
import com.example.LibreriaWeb.service.LibroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Controller
@SessionAttributes("libro")
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServiceImpl libroService;

    @Autowired
    private AutorServiceImpl autorService;

    @Autowired
    private EditorialServiceImpl editorialService;

    @GetMapping("/listar")
    public String listarLibros(Model model, @RequestParam(required = false) String q) {
        try{
        if(q != null && !q.isEmpty()){
            model.addAttribute("titulo", "Lista de Libros");
            model.addAttribute("libros", libroService.listarPorBusqueda(q));
            model.addAttribute("libro", new Libro());
        }else {
            model.addAttribute("titulo", "Lista de Libros");
            model.addAttribute("libros", libroService.listar());
            model.addAttribute("libro", new Libro());
        }
        }catch (ErrorServicio e){
            model.addAttribute("titulo", "Lista de Libros");
            model.addAttribute("errorLibro", e.getMessage());
        }
        return "listar-libros";
    }

    @PostMapping("/form")
    public String guardar(@Valid Libro libro, Errors errores, ModelMap model, SessionStatus status){

        if(errores.hasErrors()){
            model.put("mensaje","ERROR");
            return "form-libro";
        }
        try {
//            Autor autor = autorService.buscarPorNombre(libro.getAutor().getNombre());
//            Editorial editorial = editorialService.buscarPorNombre(libro.getEditorial().getNombre());
//
//            libro.setAutor(autor);
//            libro.setEditorial(editorial);
            status.setComplete();
            libroService.guardar(libro);
        }catch (Exception ex){
            model.put("error",ex.getMessage());
            return "form-libro";
        }
        return "redirect:/libro/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        try {
            model.addAttribute("libro", libroService.encontrar(id));
            model.addAttribute("titulo", "Fromulario de Cliente");
            return "form-libro";
        }catch (ErrorServicio ex){
            model.addAttribute("error",ex.getMessage());
            model.addAttribute("msj", "Fromulario de libros");
            return "redirect:/libro/listar";
        }
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable("id")Integer id, Model model) {
        try {
            libroService.eliminar(id);
        } catch (ErrorServicio e) {
            model.addAttribute("errorLibro", e.getMessage());
        } finally {
            return "redirect:/libro/listar";
        }
    }

}
