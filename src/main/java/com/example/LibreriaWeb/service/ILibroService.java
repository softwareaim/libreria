package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.errores.ErrorServicio;

import java.util.List;

public interface ILibroService {
    List<Libro> listar();

    void guardar(Libro libro);

    void eliminar(Integer id)throws ErrorServicio;;

    Libro encontrar(Integer id) throws ErrorServicio;

    List<Libro> listarPorBusqueda(String q) throws ErrorServicio;
}
