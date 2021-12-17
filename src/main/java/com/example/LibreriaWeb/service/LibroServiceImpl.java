package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.LibroDao;
import com.example.LibreriaWeb.domain.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImpl implements IdaoService<Libro>{

    @Autowired
    private LibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public List<Libro> litar() {
        return libroDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Libro libro) {
        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void eliminar(Libro libro) {
        libroDao.delete(libro);

    }

    @Override
    @Transactional(readOnly = true)
    public Libro encontrar(Libro libro) {
      return libroDao.findById(libro.getId()).orElse(null);
    }
}
