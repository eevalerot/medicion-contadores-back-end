package com.medicion.contador.security.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicion.contador.security.dto.JwtDto;
import com.medicion.contador.security.dto.LoginUsuario;
import com.medicion.contador.security.dto.Mensaje;
import com.medicion.contador.security.dto.NuevoUsuario;
import com.medicion.contador.security.entity.Rol;
import com.medicion.contador.security.entity.Usuario;
import com.medicion.contador.security.enums.RolNombre;
import com.medicion.contador.security.jwt.JwtProvider;
import com.medicion.contador.security.service.RolService;
import com.medicion.contador.security.service.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class authController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping("/nuevo")
	@SuppressWarnings("unchecked")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bilBindingResult) {
		
		if (bilBindingResult.hasErrors()) 
			return new ResponseEntity<Object>(new Mensaje("campos mal puestos o email inválido"), HttpStatus.BAD_REQUEST);
		
		if(usuarioService.existsByLogin(nuevoUsuario.getLogin()))
			return new ResponseEntity<Object>(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
		
		/*Usuario usuario = new Usuario(nuevoUsuario.getFullName(), nuevoUsuario.getLogin(), nuevoUsuario.getEmail(), 
				passwordEncoder.encode(nuevoUsuario.getPass()));*/
		
		Usuario usuario = new Usuario();
		
		
		Set<Rol> roles = new HashSet<>();
		
		if(nuevoUsuario.getRoles().contains("user"))
		roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
		
		if(nuevoUsuario.getRoles().contains("admin"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
		
		if(nuevoUsuario.getRoles().contains("super"))
			roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUPER).get());
		
		usuario.setFullName(nuevoUsuario.getFullName());
		usuario.setLogin(nuevoUsuario.getLogin());
		usuario.setEmail(nuevoUsuario.getEmail());
		usuario.setPass(passwordEncoder.encode(nuevoUsuario.getPass()));
		usuario.setRoles(roles);
		
		usuarioService.save(usuario);
		
		return new ResponseEntity<Object>(new Mensaje("usuario guardado"), HttpStatus.CREATED);

	}
	
	@PostMapping("/login")
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bilBindingResult) {
		if(bilBindingResult.hasErrors()) 
			return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
		
		org.springframework.security.core.Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUusario(), loginUsuario.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto, HttpStatus.OK);
	}
}
