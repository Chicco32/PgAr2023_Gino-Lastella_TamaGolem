package it.unibs.Arnaldo.Tamagolem;

import it.unibs.fp.mylib.InputDati;

public class MainGolem {

	public static void main(String[] args) {
		int n = InputDati.leggiIntero("Con quanti elementi volete giocare?", 3, 10);
		Elemento eq1 = new Elemento(n);
		eq1.printMatrice(n);

	}

}
