package com.example.LibreriaWeb.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "libros")
public class Libro implements Serializable {
    private final static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "isbn")
    private Long isbn;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "ejemplares")
    private Integer ejemplares;
    @Column(name = "prestados")
    private Integer ejemplaresPrestados;
    @Column(name = "restantes")
    private Integer ejemplaresRestantes;
    @Column(name = "alta")
    private Boolean alta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_autor")
    private Autor autor;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_editorial", referencedColumnName = "id")
    private Editorial editorial;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo>  prestamo;

}
