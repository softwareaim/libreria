package com.example.LibreriaWeb.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "editoriales")
public class Editorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "alta")
    private Boolean alta;
    @OneToOne(mappedBy = "editorial")
    private Libro libro;
}
