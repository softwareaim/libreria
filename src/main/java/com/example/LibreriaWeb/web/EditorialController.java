package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Editorial;
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
        model.addAttribute("editoriales", editorialService.litar());
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
        Editorial editorial = new Editorial();
        editorial.setId(id);
        if (editorialService.encontrar(editorial) != null) {
            model.addAttribute("editorial", editorialService.encontrar(editorial));
            model.addAttribute("titulo", "Fromulario de Editorial");
            return "form-editorial";
        }
        return "redirect:/editorial/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminarEditorial(@PathVariable("id")Integer id){
        Editorial editorial = new Editorial();
        editorial.setId(id);
        if (editorialService.encontrar(editorial) != null) {
            editorialService.eliminar(editorial);
        }
        return "redirect:/editorial/listar";
    }
}
