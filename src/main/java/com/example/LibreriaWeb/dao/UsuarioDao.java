
package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Editorial;
import com.example.LibreriaWeb.domain.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao extends JpaRepository<Usuario, Integer>{
    
    Optional<Usuario> findByUsername(String nombre);
    
}
