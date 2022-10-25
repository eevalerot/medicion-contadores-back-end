package com.medicion.contador.security.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.medicion.contador.security.enums.RolNombre;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ususarioIdGenerator")
	@SequenceGenerator(name = "usuarioIdGenerator", initialValue = 1, allocationSize = 1, sequenceName = "usuario_id_seq")
	private int id;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RolNombre rolNombre;

	public Rol() {
		super();
	}

	public Rol(@NotNull RolNombre rolNombre) {
		super();
		this.rolNombre = rolNombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public RolNombre getRolNombre() {
		return rolNombre;
	}

	public void setRolNombre(RolNombre rolNombre) {
		this.rolNombre = rolNombre;
	}

	
}
