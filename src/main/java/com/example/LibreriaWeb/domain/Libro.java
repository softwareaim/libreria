package com.example.LibreriaWeb.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "libros",uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Libro implements Serializable {
    private static final long serialVersionUID = -5998739394586975167L;
    //private final static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "isbn")
    private Long isbn;

    @NotEmpty
    @Column(name = "titulo")
    private String titulo;

    @Column(name = "anio")
    private Integer anio;

    @NotNull(message = "Alexis ejemplares")
    @Column(name = "ejemplares")
    private Integer ejemplares;

    @Column(name = "prestados")
    private Integer ejemplaresPrestados;

    @Column(name = "restantes")
    private Integer ejemplaresRestantes;

    @Column(name = "alta")
    private Boolean alta;

    //@NotNull(message = "Alexis autor")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="id_autor")
    private Autor autor;

   // @NotNull(message = "Alexis editorial")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_editorial")
    private Editorial editorial;

    @OneToMany(mappedBy = "libro")
    private List<Prestamo>  prestamo;

    public Libro(){

    }

    public Libro(Integer id){
        this.id = id;
    }

}
