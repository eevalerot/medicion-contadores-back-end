package com.medicion.contador.dispositivo.service;

import org.springframework.stereotype.Component;

@Component
public interface MedidorAutomaticoService {

	Boolean saveMedidas(String intensidad, String potencia);
	
}
