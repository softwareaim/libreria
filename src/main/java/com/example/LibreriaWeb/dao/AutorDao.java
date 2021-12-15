package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorDao extends JpaRepository<Autor, String> {
}
