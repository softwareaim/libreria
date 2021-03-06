package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.LibroDao;
import com.example.LibreriaWeb.domain.Libro;
import com.example.LibreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService{

    @Autowired
    private LibroDao libroDao;

    @Override
    @Transactional(readOnly = true)
    public List<Libro> listar() {
        return libroDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Libro libro) {

        libro.setAlta(true);
        libroDao.save(libro);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        libroDao.delete(encontrar(id));

    }

    @Override
    @Transactional(readOnly = true)
    public Libro encontrar(Integer id) throws ErrorServicio {
      Libro libro = libroDao.findById(id).orElse(null);
            if (libro == null){
                throw new ErrorServicio("No se encontro Libro");
            }
        return libro;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> listarPorBusqueda(String q) throws ErrorServicio {
        List<Libro> libros = libroDao.findAllByQ("%"+q+"%");
        if(libros.isEmpty()){
            throw new ErrorServicio("No se econtraron resultados con la palabra : "+q);
        }
        return libros;
    }

}
