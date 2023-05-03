package it.unibs.Arnaldo.Tamagolem;

import it.unibs.Arnaldo.Tamagolem.Elemento.tipoElemento;

public class Pietra {
	static Elemento.tipoElemento[] tipi = tipoElemento.values();
	
	private tipoElemento elemento;
	
	public Pietra(int numeroDiCreazione) {
		this.elemento = tipi[numeroDiCreazione];
	}

	public tipoElemento getElemento() {
		return elemento;
	}
	
}
