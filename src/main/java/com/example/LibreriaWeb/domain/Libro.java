package com.example.LibreriaWeb.domain;

import lombok.Data;

import java.util.List;
import javax.persistence.*;

@Data
@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;
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
    @ManyToMany(mappedBy = "libros", cascade = CascadeType.ALL)
   // @JoinColumn(name="autor_id",referencedColumnName = "id")
    private List<Autor> autores;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_editorial",referencedColumnName = "id")
    private Editorial editorial;
    @OneToMany(mappedBy = "libro")
    private List<Prestamo>  prestamo;

}
