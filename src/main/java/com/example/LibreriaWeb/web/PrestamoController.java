package com.example.LibreriaWeb.web;

import com.example.LibreriaWeb.dao.PrestamoDao;
import com.example.LibreriaWeb.domain.Cliente;
import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.domain.Prestamo;
import com.example.LibreriaWeb.service.ClienteServiceImpl;
import com.example.LibreriaWeb.service.IdaoService;
import com.example.LibreriaWeb.service.LibroServiceImpl;
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
    private PrestamoServiceImpl prestamoService;
    @Autowired
    private ClienteServiceImpl clienteIdaoService;
    @Autowired
    private LibroServiceImpl libroIdaoService;

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
         Libro libro = libroIdaoService.encontrar(id_libro);

        if(libro.getAlta()){
            libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
            libroIdaoService.guardar(libro);
            prestamo.setLibro(libro);
        }


        prestamo.setCliente(clienteIdaoService.encontrar(id_cliente));
        prestamoService.guardar(prestamo);
        return "redirect:/prestamo/listar";
    }



}
