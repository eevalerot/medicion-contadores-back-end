package com.medicion.contador.security.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6985063881331848978L;
	private String fullName;
	private String documento;
	private String direccion;
	private String telefonoFijo;
	private String telefonoCelular;
	private String loing;
	private String pass;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UsuarioPrincipal(String fullName, String documento, String direccion, String telefonoFijo,
			String telefonoCelular, String loing, String pass, String email,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.fullName = fullName;
		this.documento = documento;
		this.direccion = direccion;
		this.telefonoFijo = telefonoFijo;
		this.telefonoCelular = telefonoCelular;
		this.loing = loing;
		this.pass = pass;
		this.email = email;
		this.authorities = authorities;
	}
	
	public static UsuarioPrincipal build(Usuario usuario) {
		List<GrantedAuthority> authorities = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
		return new UsuarioPrincipal(usuario.getFullName(), usuario.getDocumento(), usuario.getDireccion(), usuario.getTelefonoFijo(), usuario.getTelefonoCelular(), usuario.getLogin(), usuario.getPass(), usuario.getEmail(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return pass;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return loing;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
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
	public String getLoing() {
		return loing;
	}
	public void setLoing(String loing) {
		this.loing = loing;
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
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
}
