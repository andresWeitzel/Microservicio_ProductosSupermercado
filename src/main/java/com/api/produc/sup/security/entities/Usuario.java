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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @NotNull(message="El Nombre no puede ser null")
	@NotEmpty(message="El Nombre no puede ser vacío")
    @NotBlank(message="El Nombre no puede estar en blanco")
	@Size(min = 4, max = 200, message = "El Nombre debe ser mayor o igual a 4 caracteres y menor o igual a 200 caracteres")
    private String nombre;
    
    
    @Column(name = "username", nullable=false, unique = true ,length=100)
    @NotNull(message="El Username no puede ser null")
  	@NotEmpty(message="El Username no puede ser vacío")
    @NotBlank(message="El Username no puede estar en blanco")
  	@Size(min = 4, max = 100, message = "El Username debe ser mayor o igual a 4 caracteres y menor o igual a 100 caracteres") 
    private String username;
    

    @Column(name = "password", nullable=false, unique = false ,length=255)
    @NotNull(message="El Password no puede ser null")
  	@NotEmpty(message="El Password no puede ser vacío")
    @NotBlank(message="El Password no puede estar en blanco")
  	@Size(min = 4, max = 255, message = "El Password debe ser mayor o igual a 4 caracteres y menor o igual a 255 caracteres")
    private String password;
    
    @Column(name = "email", nullable=false, unique = true ,length=255)
    @NotNull(message="El Email no puede ser null")
   	@NotEmpty(message="El Email no puede ser vacío")
    @NotBlank(message="El Email no puede estar en blanco")
   	@Size(min = 4, max = 255, message = "El Email debe ser mayor o igual a 4 caracteres y menor o igual a 255 caracteres")
    private String email;
    
    
    @NotNull
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles = new HashSet<>();


    

    public Usuario(@NotNull long id,@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email) {
    	this.id=id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Usuario(@NotNull String nombre, @NotNull String username, @NotNull String password
    		, @NotNull String email) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    


    
}
