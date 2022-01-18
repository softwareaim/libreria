package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorDao extends JpaRepository<Autor, Integer> {

   // @Query("SELECT a FROM Autor a WHERE a.nombre = :nombre")
  //  public List<Autor> buscarAutorPorNombre(@Param("nombre") String nombre);

    public List<Autor>findByNombre (String nombre);

}
