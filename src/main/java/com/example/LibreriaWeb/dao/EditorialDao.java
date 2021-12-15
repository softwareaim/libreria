package com.example.LibreriaWeb.dao;

import com.example.LibreriaWeb.domain.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialDao extends JpaRepository<Editorial, String> {
}
