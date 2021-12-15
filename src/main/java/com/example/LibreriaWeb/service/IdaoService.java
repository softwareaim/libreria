package com.example.LibreriaWeb.service;

import java.util.List;

public interface IdaoService<T> {

    public List<T> litar();

    public void guardar(T t);

    public void eliminar(T t);

    public T encontrar(T t);
}
