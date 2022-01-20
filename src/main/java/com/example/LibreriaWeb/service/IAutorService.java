package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.errores.ErrorServicio;

import java.util.List;
import java.util.Optional;

public interface IAutorService {
    List<Autor> listar();

    void guardar(Autor autor);

    void eliminar(Integer id)throws ErrorServicio;;

    Autor encontrar(Integer id) throws ErrorServicio;

    Autor buscarPorNombre(String nombre) throws ErrorServicio;

}
