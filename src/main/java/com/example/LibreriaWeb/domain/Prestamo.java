package com.example.LibreriaWeb.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo implements Serializable {
    private static final long serialVersionUID = 6837648999326959085L;
    //private final static Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  // @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="fecha_prestamo")
    private Date fechaPrestamo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_libro",referencedColumnName = "id")
    private Libro libro;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_cliente",referencedColumnName = "id")
    private Cliente cliente;

    public Prestamo(){

    }

    
}