package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.EditorialDao;
import com.example.LibreriaWeb.domain.Editorial;
import com.example.LibreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EditorialServiceImpl implements IEditorialService{

    @Autowired
    private EditorialDao editorialDao;

    @Override
    @Transactional(readOnly = true)
    public List<Editorial> listar() {
        return editorialDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Editorial editorial) {
        editorialDao.save(editorial);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        editorialDao.delete(encontrar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Editorial encontrar(Integer id) throws ErrorServicio {
        Editorial editorial = editorialDao.findById(id).orElse(null);
        if(editorial == null){
            throw new ErrorServicio("No se encontro Editorial");
        }
        return editorial;
    }

    @Override
    public Editorial buscarPorNombre(String nombre) throws ErrorServicio {
        Editorial editorial = editorialDao.findByNombreLike(nombre).orElse(null);
        if(editorial == null){
            throw new ErrorServicio("No se econtro una editorial con el nombre "+nombre);
        }
        return editorial;
    }
}
