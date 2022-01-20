package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.domain.Editorial;
import com.example.LibreriaWeb.errores.ErrorServicio;

import java.util.List;

public interface IEditorialService {
    List<Editorial> listar();

    void guardar(Editorial editorial);

    void eliminar(Integer id)throws ErrorServicio;;

    Editorial encontrar(Integer id) throws ErrorServicio;

    Editorial buscarPorNombre(String nombre) throws ErrorServicio;
}
