
package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.UsuarioDao;
import com.example.LibreriaWeb.domain.Rol;
import com.example.LibreriaWeb.domain.Usuario;
import com.example.LibreriaWeb.errores.ErrorServicio;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioDao usuarioDao;
    
    @Transactional(readOnly = true)
     public List<Usuario> listarUsuario(){
       return usuarioDao.findAll();
}
    
    @Transactional 
    public void guardar(String username, String password, String password2) throws ErrorServicio{
        
        if(usuarioDao.findByUsername(username) == null){
        throw new ErrorServicio("Nombre de usuario no disponible");
        }
        
        if(!password.equals(password2)){
            throw new ErrorServicio("Las contraseñas no coinciden");
        }
        
        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        
        usuarioDao.save(usuario);
    }
    
     @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer id){
        
        return usuarioDao.findById(id).orElse(null);
    }
    
    @Transactional
    public void eliminar(Integer id){
        
        usuarioDao.delete(buscarUsuarioPorId(id));
    
    };
    
}
