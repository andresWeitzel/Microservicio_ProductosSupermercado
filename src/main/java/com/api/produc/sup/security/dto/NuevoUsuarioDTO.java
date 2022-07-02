package com.api.produc.sup.security.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NuevoUsuarioDTO {
	 	
		@NotBlank
	    private String nombre;

		@Email
	    private String email;
	    
		@NotBlank
	    private String username;

		@NotBlank
	    private String password;
	    
		private Set<String> roles = new HashSet<>();

	    
}
