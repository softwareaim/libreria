package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.ClienteDao;
import com.example.LibreriaWeb.domain.Cliente;
import com.example.LibreriaWeb.errores.ErrorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class ClienteServiceImpl implements IdaoService<Cliente,Integer>{

    @Autowired
    private ClienteDao clienteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return clienteDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Cliente cliente) {
        clienteDao.save(cliente);
    }

    @Override
    @Transactional
    public void eliminar(Integer id)throws ErrorServicio {
        clienteDao.delete(encontrar(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente encontrar(Integer id) throws ErrorServicio {
        Cliente cliente = clienteDao.findById(id).orElse(null);
        if(cliente == null){
            throw new ErrorServicio("No se encotro Cliente");
        }
        return cliente;
    }
}
