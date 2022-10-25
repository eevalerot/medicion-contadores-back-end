package com.medicion.contador.dispositivo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.medicion.contador.commons.Base;


@Entity
@Table(name = "medicion_automatica")
public class MedicionAutomatica extends Base{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6013129584419782544L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ususarioIdGenerator")
	@SequenceGenerator(name = "usuarioIdGenerator", initialValue = 1, allocationSize = 1, sequenceName = "usuario_id_seq")
	private int id;
	
	private String intencidad;
	
	private String potencia;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIntencidad() {
		return intencidad;
	}

	public void setIntencidad(String intencidad) {
		this.intencidad = intencidad;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}
	
	
	

}
