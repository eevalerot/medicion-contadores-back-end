package com.medicion.contador.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicion.contador.security.entity.Usuario;
import com.medicion.contador.security.repository.UsuarioRepositoriy;


@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepositoriy usuarioRepositoriy;
	
	public Optional<Usuario> getByLogin(String login) {
		
		return usuarioRepositoriy.findByLogin(login);
		
	}
	
	public boolean existsByLogin(String login) {
		
		return usuarioRepositoriy.existsByLogin(login);
		
	}
	
	public void save(Usuario usuario) {
		
		usuarioRepositoriy.save(usuario);
		
	}
}
