package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestamoDao extends JpaRepository<Prestamo, Integer> {
}
