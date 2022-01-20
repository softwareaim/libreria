package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.PrestamoDao;
import com.example.LibreriaWeb.domain.Prestamo;
import com.example.LibreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl implements IdaoService<Prestamo,Integer>{

    @Autowired
    private PrestamoDao prestamoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> listar() {
        return prestamoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Prestamo prestamo) {
        prestamoDao.save(prestamo);
    }

    @Override
    @Transactional
    public void eliminar(Integer id) throws ErrorServicio {
        prestamoDao.delete(encontrar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Prestamo encontrar(Integer id) throws ErrorServicio {
        Prestamo prestamo = prestamoDao.findById(id).orElse(null);
        if (prestamo == null){
            throw new ErrorServicio("No se encontro Prestamo");
        }
        return prestamo;
    }


}
