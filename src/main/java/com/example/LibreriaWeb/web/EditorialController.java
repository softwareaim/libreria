package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Editorial;
import com.example.LibreriaWeb.errores.ErrorServicio;
import com.example.LibreriaWeb.service.EditorialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("editorial")
@RequestMapping("/editorial")
public class EditorialController {

    @Autowired
    private EditorialServiceImpl editorialService;

    @RequestMapping("/listar")
    public String listarEditoriales(Model model) {
        model.addAttribute("editoriales", editorialService.listar());
        model.addAttribute("titulo", "Lista de Editoriales");
        return "listar-editoriales";
    }

    @RequestMapping("/form")
    public String formEditorial(Model model) {
        model.addAttribute("titulo", "Formulario Editorial");
        model.addAttribute("editorial", new Editorial());
        return "form-editorial";
    }

    @PostMapping("/form")
    public String guardarEditorial(Editorial editorial, SessionStatus status) { // sessionstatus mantiene el valor para editar el mismo id
        status.setComplete();
        editorialService.guardar(editorial);
        return "redirect:/editorial/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editarEditorial(@PathVariable("id")Integer id, Model model){

        try {
            model.addAttribute("editorial", editorialService.encontrar(id));
            model.addAttribute("titulo", "Fromulario de Editorial");
            return "form-editorial";
        } catch (ErrorServicio e) {
           model.addAttribute("errorEditorial",e.getMessage());
            return "redirect:/editorial/listar";
        }

    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarEditorial(@PathVariable("id")Integer id, Model model){
        try {
            editorialService.eliminar(id);
        } catch (ErrorServicio e) {
            model.addAttribute("errorEditorial",e.getMessage());
        }finally {
            return "redirect:/editorial/listar";
        }

    }
}
