
package com.example.LibreriaWeb.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "autores")
 public class  Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "alta")
    private Boolean alta;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="autorLibro", joinColumns={@JoinColumn(name="id_autor")}, inverseJoinColumns={@JoinColumn(name="id_libro")})
    private List<Libro> libros;
    
}
