package com.api.produc.sup.security.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.api.produc.sup.security.enums.TipoRol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "roles")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "roles", nullable=false, unique = true , length=50)
    private TipoRol rol;

    /**
     * Constructor parametrico sin ID
     * @param rol
     */
    public Rol(@NotNull TipoRol rol) {
        this.rol = rol;
    }

    }
