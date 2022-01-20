package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.AutorDao;
import com.example.LibreriaWeb.domain.Autor;
import com.example.LibreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorServiceImpl implements IAutorService {

    @Autowired
    private AutorDao autorDao;


    @Override
    @Transactional(readOnly = true)
    public List<Autor> listar() {
        return autorDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Autor autor) {
        autorDao.save(autor);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        autorDao.delete(encontrar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Autor encontrar(Integer id) throws ErrorServicio {
        Autor autor = autorDao.findById(id).orElse(null);
        if(autor == null){
            throw new ErrorServicio("No se encontro Autor");
        }
        return autor;
    }

    @Override
    public Autor buscarPorNombre(String nombre) throws ErrorServicio {
        Autor autor = autorDao.findByNombreLike(nombre).orElse(null);
        if(autor == null){
            throw new ErrorServicio("No se encotro un Autor con el nombre "+nombre);
        }
        return autor;
    }

}
