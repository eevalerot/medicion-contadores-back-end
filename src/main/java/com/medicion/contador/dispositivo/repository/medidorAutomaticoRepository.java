package com.medicion.contador.dispositivo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.medicion.contador.dispositivo.entities.MedicionAutomatica;

@Repository
public interface medidorAutomaticoRepository extends JpaRepository<MedicionAutomatica, Long>{

}
