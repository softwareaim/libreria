package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.AutorDao;
import com.example.LibreriaWeb.domain.Autor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorServiceImpl implements IdaoService<Autor,Integer> {

    @Autowired
    private AutorDao autorDao;

    @Override
    @Transactional(readOnly = true)
    public List<Autor> litar() {
        return autorDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Autor autor) {
        autorDao.save(autor);
    }

    @Override
    @Transactional
    public void eliminar(Autor autor) {
        autorDao.delete(autor);
    }

    @Override
    @Transactional(readOnly = true)
    public Autor encontrar(Integer id) {
        return autorDao.findById(id).orElse(null);
    }
}
