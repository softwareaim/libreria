package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, String> {
}