package com.medicion.contador.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicion.contador.security.entity.Rol;
import com.medicion.contador.security.enums.RolNombre;

@Repository
public interface UsuarioRolRepository extends JpaRepository<Rol, Long>{

	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
