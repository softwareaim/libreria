package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.service.AutorServiceImpl;
import com.example.LibreriaWeb.service.EditorialServiceImpl;
import com.example.LibreriaWeb.service.LibroServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Controller
@Slf4j
@RequestMapping("/libro")
public class LibroController {

    @Autowired
    private LibroServiceImpl libroService;

    @Autowired
    private AutorServiceImpl autorService;

    @Autowired
    private EditorialServiceImpl editorialService;

    @GetMapping("/listar")
    public String listarLibros(Model model) {
        model.addAttribute("titulo","Lista de Libros");
        model.addAttribute("libros", libroService.litar());
        model.addAttribute("libro",new Libro());
        return "listar-libros";
    }

    @PostMapping("/form")
    public String guardar(@Valid Libro libro, Errors errores,ModelMap model, Integer idAutor,Integer idEditorial){

        if(idAutor == null )
        {
            model.put("errorAutor"," id autor No puede estar vacio");
            return "form-libro";
        }
        if(idEditorial == null )
        {
            model.put("errorEditorial","id editor No puede estar vacio");
            return "form-libro";
        }
        if(errores.hasErrors()){
            model.put("mensaje","ERROR");
            return "form-libro";
        }
        if(autorService.encontrar(idAutor)==null){

            model.put("errorAutor","no se encontro un autor");
            return "form-libro";
        }

        libro.setAutor(autorService.encontrar(idAutor));
        libro.setEditorial(editorialService.encontrar(idEditorial));


        libroService.guardar(libro);
        return "redirect:/libro/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (libroService.encontrar(id) != null) {
            model.addAttribute("libro", libroService.encontrar(id));
            model.addAttribute("msj", "Fromulario de libros");
            return "form-libro";
        }
        return "redirect:/libro/listar";
    }


}
