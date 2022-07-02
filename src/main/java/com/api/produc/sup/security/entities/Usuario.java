package com.api.produc.sup.security.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.api.produc.sup.security.enums.TipoRol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table(name = "usuarios")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private long id;
    
    
    @Column(name = "nombre", nullable=false,unique = false , length=200)
    private String nombre;
    
    
    @Column(name = "username", nullable=false, unique = true ,length=100)
    private String username;
    

    @Column(name = "password", nullable=false, unique = false ,length=255)
    private String password;
    
    @Column(name = "email", nullable=false, unique = true ,length=255)
    private String email;
    
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuarios_id"),
    inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Rol> roles = new HashSet<>();

  

    public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    
}
