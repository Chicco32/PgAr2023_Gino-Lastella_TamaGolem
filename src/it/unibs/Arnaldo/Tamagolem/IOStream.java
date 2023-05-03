package it.unibs.Arnaldo.Tamagolem;

import it.unibs.Arnaldo.Tamagolem.Elemento.tipoElemento;

public class IOStream {
	static Elemento.tipoElemento[] tipi = tipoElemento.values();
	
	public static void showListaElementi (int nUsati) {
		System.out.println("La lista di possibili elementi in gioco:");
		for (int i=0; i<nUsati; i++) {
			System.out.println(tipi[i]);
		}
	}
}
