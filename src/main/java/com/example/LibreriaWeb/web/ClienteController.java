package com.example.LibreriaWeb.web;


import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.domain.Cliente;
import com.example.LibreriaWeb.service.ClienteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("cliente")
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteServiceImpl clienteService;

    @GetMapping("/listar")
    public String agregarCliente(Model model) {
        model.addAttribute("titulo", "Lista de Clientes");
        model.addAttribute("listaClientes", clienteService.litar());
        return "listar-clientes";
    }

    @RequestMapping("/form")
    public String crearCliente(Model model) {
        model.addAttribute("titulo", "Fromulario de Cliente");
        model.addAttribute("cliente", new Cliente());
        return "form-cliente";
    }

    @PostMapping("/form")
    public String guardar(Cliente cliente, SessionStatus status) {
        status.setComplete();
        clienteService.guardar(cliente);
        return "redirect:/cliente/listar";
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
 ;
        if (clienteService.encontrar(id) != null) {
            clienteService.eliminar(clienteService.encontrar(id));
        }
        return "redirect:/cliente/listar";
    }

    @RequestMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        if (clienteService.encontrar(id) != null) {
            model.addAttribute("cliente", clienteService.encontrar(id));
            model.addAttribute("titulo", "Fromulario de Cliente");
            return "form-cliente";
        }
        return "redirect:/cliente/listar";
    }
}
