
package com.example.LibreriaWeb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario",uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class Usuario implements Serializable{
    private final static Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty
    private String username;
    
    @NotEmpty
    private String password;

    private Boolean enabled;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles",joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","roles_id"})})

    private List<Rol> roles = new ArrayList<>();
    
}
