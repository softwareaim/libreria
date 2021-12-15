package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroDao extends JpaRepository<Libro, String> {
}
