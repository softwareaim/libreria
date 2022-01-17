package com.example.LibreriaWeb.service;

import java.util.List;

public interface IdaoService<T> {

    List<T> litar();

    void guardar(T t);

    void eliminar(T t);

    T encontrar(T t);
}
