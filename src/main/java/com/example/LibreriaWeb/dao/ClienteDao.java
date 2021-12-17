package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ClienteDao extends JpaRepository<Cliente, Integer> {
}
