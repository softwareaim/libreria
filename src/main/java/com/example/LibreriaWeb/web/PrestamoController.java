package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.domain.Cliente;
import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.domain.Prestamo;
import com.example.LibreriaWeb.service.IdaoService;
import com.example.LibreriaWeb.service.PrestamoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/prestamo")
public class PrestamoController {

    @Autowired
    private IdaoService<Prestamo> prestamoService;
    @Autowired
    private IdaoService<Cliente> clienteIdaoService;
    @Autowired
    private IdaoService<Libro> libroIdaoService;

    @GetMapping("/listar")
    public String listarPrestamos(Model model) {
        model.addAttribute("titulo","Lista de Prestamos");
        model.addAttribute("prestamos", prestamoService.litar());
        model.addAttribute("prestamo",new Prestamo());
        return "listar-prestamos";
    }

    @PostMapping("/form")
    public String guardar(Prestamo prestamo, Integer id_libro, Integer id_cliente, SessionStatus status){
        status.setComplete();

        Cliente cliente = new Cliente();
        cliente.setId(id_cliente);

         Libro libro = libroIdaoService.encontrar(new Libro(id_libro));

        if(libro.getAlta()){
            libro.setPrestados(libro.getPrestados()+1);
            libroIdaoService.guardar(libro);
            prestamo.setLibro(libro);
        }


        prestamo.setCliente(clienteIdaoService.encontrar(cliente));
        prestamoService.guardar(prestamo);
        return "redirect:/prestamo/listar";
    }



}
