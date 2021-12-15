package com.example.LibreriaWeb.domain;

import lombok.Data;

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

@Data
@Entity
@Table(name = "prestamos")
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private String id;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_prestamo")
    private Date fechaPrestamo;
    @Temporal(TemporalType.DATE)
    @Column(name="fecha_devolucion")
    private Date fechaDevolucion;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="libro_id",referencedColumnName = "id")
    private Libro libro;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="cliente_id",referencedColumnName = "id")
    private Cliente cliente;

    
}