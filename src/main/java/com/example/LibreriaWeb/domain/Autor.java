
package com.example.LibreriaWeb.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "autores")
 public class  Autor implements Serializable {
    private final static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "alta")
    private Boolean alta;

    @OneToMany(mappedBy = "autor")
    private List<Libro> libros;
    
}
