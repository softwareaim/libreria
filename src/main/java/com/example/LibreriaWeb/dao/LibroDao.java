package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroDao extends JpaRepository<Libro, Integer> {
    @Query("SELECT l FROM Libro l WHERE l.titulo LIKE :q or l.titulo LIKE :q ")
    List<Libro> findAllByQ(@Param("q")String q);
}
