package com.example.LibreriaWeb.service;

import com.example.LibreriaWeb.dao.UsuarioDao;
import com.example.LibreriaWeb.domain.Rol;
import com.example.LibreriaWeb.domain.Usuario;
import com.example.LibreriaWeb.errores.ErrorServicio;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Transactional(readOnly = true)
    public List<Usuario> listarUsuario() {
        return usuarioDao.findAll();
    }

    @Transactional
    public void guardar(Usuario usuario, String password2) throws ErrorServicio {

        if (!usuario.getPassword().equals(password2)) {
            throw new ErrorServicio("Las contraseñas no coinciden");
        }
        
        if (usuarioDao.findByUsername(usuario.getUsername()).isPresent()) {
            throw new ErrorServicio("Nombre de usuario no disponible");
        }
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encriptada = passwordEncoder.encode(usuario.getPassword());

        Boolean flag = passwordEncoder.matches(usuario.getPassword(),encriptada);
        if (!flag){
            throw new ErrorServicio("Error de encriptacion");
        }

        usuario.setPassword(encriptada);

        usuarioDao.save(usuario);
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer id) {

        return usuarioDao.findById(id).orElse(null);
    }

    @Transactional
    public void eliminar(Integer id) {
        usuarioDao.delete(buscarUsuarioPorId(id));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         Usuario usuario = usuarioDao.findByUsername(username).get();
         if(usuario == null){
             throw new UsernameNotFoundException("No se encotro el usuario");
         }
         List<GrantedAuthority> roles = new ArrayList<>();

        for (Rol rol: usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getRol()));
        }

        return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(),usuario.getEnabled(),usuario.getEnabled(),usuario.getEnabled(), roles);
    }
}
