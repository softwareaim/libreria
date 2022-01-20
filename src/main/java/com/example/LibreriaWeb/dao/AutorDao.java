package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface AutorDao extends JpaRepository<Autor, Integer> {

   @Query("SELECT a FROM Autor a WHERE UPPER(a.nombre) LIKE CONCAT('%',UPPER(:nombre),'%')")
   Optional<Autor> findByNombreLike(@Param("nombre") String nombre);

   Optional<Autor> findByNombre(String nombre);


}
