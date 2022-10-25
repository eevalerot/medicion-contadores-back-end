package com.medicion.contador.security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {
	
	@NotBlank
	private String nombreUusario;
	
	@NotBlank
	private String password;

	public String getNombreUusario() {
		return nombreUusario;
	}

	public void setNombreUusario(String nombreUusario) {
		this.nombreUusario = nombreUusario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
