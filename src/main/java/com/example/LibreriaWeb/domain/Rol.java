package com.example.LibreriaWeb.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Rol implements Serializable {

    private static final long serialVersionUID = -3476807453846568901L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rol;
   
 
}
