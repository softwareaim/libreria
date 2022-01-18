
package com.example.LibreriaWeb.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "dni"))
public class Cliente implements Serializable {
    private final static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="dni")
    @NotNull(message = "Debe ingrasar un dni")
    private Long dni;

    @Column(name="nombre")
    @NotEmpty(message = "Debe ingresar un nombre")
    @Size(min = 3, max = 8, message="El código del producto tiene que tener entre 3 y 8 caracteres")
    @Pattern(regexp = "[A-Za-z]+", message="El código del producto solo puede tener letras ")
    private String nombre;

    @Column(name="apellido")
    @NotEmpty(message = "Debe ingresar un apellido")
    private String apellido;

    @Column(name="telefono")
    private String telefono;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamo;

    
}
