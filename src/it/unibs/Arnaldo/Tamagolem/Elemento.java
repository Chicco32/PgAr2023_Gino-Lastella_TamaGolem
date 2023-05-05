package it.unibs.Arnaldo.Tamagolem;


public class Elemento {
	public static enum TipoElemento {Terra, Aria, Fuoco, Acqua, Etere, Fisico, Veleno, Psiche, Magia, Oscuro};
	public static int NUM_MAX_ELEMENTI = 10;
	private static String[] ListaDiTipi = {"Terra", "Aria", "Fuoco", "Acqua", "Etere", "Fisico", "Veleno", "Psiche", "Magia", "Oscuro"};
	
	public static int indiceElemento(String nomeElemento) {
		for (int i=0; i<Elemento.NUM_MAX_ELEMENTI; i++) {
			if (ListaDiTipi[i].equalsIgnoreCase(nomeElemento)) return i;
		}
		return Elemento.NUM_MAX_ELEMENTI;
	}
}
