package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.ClienteDao;
import com.example.LibreriaWeb.domain.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ClienteServiceImpl implements IdaoService<Cliente>{

    @Autowired
    private ClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> litar() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Cliente cliente) {
        clienteDao.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrar(Cliente cliente) {
        return clienteDao.findById(cliente.getId()).orElse(null);
    }
}
