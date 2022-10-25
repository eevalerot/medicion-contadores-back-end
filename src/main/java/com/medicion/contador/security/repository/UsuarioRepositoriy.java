package com.medicion.contador.security.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.medicion.contador.security.entity.Usuario;

@Repository
public interface UsuarioRepositoriy extends JpaRepository<Usuario, Long>{
	
	
	@Query(value = "SELECT u.* FROM usuario u WHERE upper(login) like upper(%:login%)",
		      nativeQuery = true)
	List<Usuario> findByLoginUser(String login);
	
	Optional<Usuario> findByLogin(String login);
	
	boolean existsByLogin(String Login);
	
	
}
