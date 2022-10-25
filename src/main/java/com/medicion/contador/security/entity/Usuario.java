package com.medicion.contador.security.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.medicion.contador.commons.Base;

@Entity
@Table(name = "usuario")
public class Usuario extends Base{
	
	/**
	 * Entidad parametros de la aplicación
	 * 
	 * @author eevalero
	 * @version 1.0
	 */
	private static final long serialVersionUID = 3054672124798720556L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ususarioIdGenerator")
	@SequenceGenerator(name = "usuarioIdGenerator", initialValue = 1, allocationSize = 1, sequenceName = "usuario_id_seq")
	private int id;
	
	@NotNull(message = "no puede ser nulo")
	@Size(min = 4, max = 255, message = "debe estar entre 4 y 255 caracteres")
	@Column(name = "full_name", nullable = false, length = 255)
	private String fullName;
	

	@Size(min = 4, max = 20, message = "debe estar entre 4 y 20 caracteres")
	private String documento;
	
	private String direccion;
	
	private String telefonoFijo;
	
	private String telefonoCelular;
	
	@Column(nullable = true, length = 50, unique = true)
	private String login;
	
	@NotNull(message = "la contraseña no puede ser null")
	private String pass;
	
	@NotNull(message = "no puede ser nulo")
	@Size(min = 4, max = 20, message = "debe estar entre 4 y 20 caracteres")
	@Column(nullable = false, length = 20)
	private String email;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_Rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
	private Set<Rol> roles = new HashSet<>();
	
	
	/**
	 * Constructor de la clase usuario
	 */
	public Usuario() {
		super();
	}

	public Usuario(String nombre, String nombreUusario, String email2, String encode) {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Getters & Setters variables de usuario
	 * @return
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}
	
}
