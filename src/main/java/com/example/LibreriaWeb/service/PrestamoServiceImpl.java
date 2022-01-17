package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.PrestamoDao;
import com.example.LibreriaWeb.domain.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PrestamoServiceImpl implements IdaoService<Prestamo>{

    @Autowired
    private PrestamoDao prestamoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Prestamo> litar() {
        return prestamoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Prestamo prestamo) {
        prestamoDao.save(prestamo);
    }

    @Override
    @Transactional
    public void eliminar(Prestamo prestamo) {
        prestamoDao.delete(prestamo);
    }

    @Override
    @Transactional(readOnly = true)
    public Prestamo encontrar(Prestamo prestamo) {
        return prestamoDao.findById(prestamo.getId()).orElse(null);
    }


}
