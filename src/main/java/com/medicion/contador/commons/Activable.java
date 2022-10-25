package com.medicion.contador.commons;

public interface Activable {
	
	public boolean isEnabled();
	public boolean isDeleted();
	public void setDeleted(Boolean deleted);

}
