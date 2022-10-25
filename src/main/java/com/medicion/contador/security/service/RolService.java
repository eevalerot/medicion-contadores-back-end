package com.medicion.contador.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicion.contador.security.entity.Rol;
import com.medicion.contador.security.enums.RolNombre;
import com.medicion.contador.security.repository.UsuarioRolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	UsuarioRolRepository usuarioRolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre) {
		
		return usuarioRolRepository.findByRolNombre(rolNombre);
		
	}
}
