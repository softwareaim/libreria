package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.EditorialDao;
import com.example.LibreriaWeb.domain.Editorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditorialServiceImpl implements IdaoService<Editorial,Integer>{

    @Autowired
    private EditorialDao editorialDao;

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> litar() {
        return editorialDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Editorial editorial) {
        editorialDao.save(editorial);
    }

    @Override
    @Transactional
    public void eliminar(Editorial editorial) {
        editorialDao.delete(editorial);
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial encontrar(Integer id) {
        return editorialDao.findById(id).orElse(null);
    }
}
