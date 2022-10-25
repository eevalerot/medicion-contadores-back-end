package com.medicion.contador.dispositivo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.medicion.contador.dispositivo.service.implement.MedidorImpl;
import com.medicion.contador.security.dto.Mensaje;

@RestController
@RequestMapping("/medicion")
@CrossOrigin
public class MedicionAutomaticaApi {

	@Autowired
	private MedidorImpl medidorImpl;
	
	
	@GetMapping(value =  "/automatico/{intensidad}/{potencia}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> nuevaMedicion(@PathVariable("intensidad") String intensidad, @PathVariable("potencia") String potencia) {
		
		
		if(medidorImpl.saveMedidas(intensidad, potencia)) 
			return new ResponseEntity<Object>(new Mensaje("mediciòn guardada"), HttpStatus.CREATED);
		
		
		return new ResponseEntity<Object>(new Mensaje("mediciòn No guardada"), HttpStatus.BAD_REQUEST);
	}
}
