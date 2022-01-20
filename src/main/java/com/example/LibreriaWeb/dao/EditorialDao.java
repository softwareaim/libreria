package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EditorialDao extends JpaRepository<Editorial, Integer> {
    @Query("SELECT e FROM Editorial e WHERE UPPER(e.nombre) LIKE CONCAT('%',UPPER(:nombre),'%')")
    Optional<Editorial> findByNombreLike(@Param("nombre") String nombre);

    Optional<Editorial> findByNombre(String nombre);
}
