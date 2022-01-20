package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.errores.ErrorServicio;

import java.util.List;

public interface IdaoService<T,K> {

    List<T> listar();

    void guardar(T t);

    void eliminar(K k)throws ErrorServicio;;

    T encontrar(K k) throws ErrorServicio;
}
