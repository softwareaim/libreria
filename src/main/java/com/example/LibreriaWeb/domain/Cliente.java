package com.example.LibreriaWeb.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "clientes", uniqueConstraints = @UniqueConstraint(columnNames = "dni"))
public class Cliente implements Serializable {

    private final static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dni")
    @NotNull(message = "Debe ingrasar un dni")
    private Long dni;

    @Column(name = "nombre")
    @NotEmpty(message = "Debe ingresar un nombre")
    @Size(min = 3, max = 8, message = "El nombre tiene que tener entre 3 y 8 caracteres")
//    @Pattern(regexp = "[A-Za-z]+", message = "El nombre solo puede tener letras ")
    private String nombre;

    @Column(name = "apellido")
    @NotEmpty(message = "Debe ingresar un apellido")
    private String apellido;

    @Column(name = "telefono")
    private String telefono;
    
    @NotEmpty(message = "Debe ingresar un mail")
    @Email
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamo;

    private Boolean alta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Prestamo> getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(List<Prestamo> prestamo) {
        this.prestamo = prestamo;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    

}
