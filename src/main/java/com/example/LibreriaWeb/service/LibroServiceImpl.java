package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.LibroDao;
import com.example.LibreriaWeb.domain.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements IdaoService<Libro,Integer>{

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

        libro.setAlta(true);
        //libro.set


        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void eliminar(Libro libro) {
        libroDao.delete(libro);

    }

    @Override
    @Transactional(readOnly = true)
    public Libro encontrar(Integer id) {
      return libroDao.findById(id).orElse(null);
    }

}
