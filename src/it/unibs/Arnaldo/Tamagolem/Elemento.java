package it.unibs.Arnaldo.Tamagolem;

import java.util.Random;

public class Elemento {

	public enum tipoElemento {Terra, Aria, Fuoco, Acqua, Etere};
	public static final int NUM_ELEMENTI = 10;
	private int[][] equilibrio = new int[NUM_ELEMENTI][NUM_ELEMENTI];
	public static final int MARGINE_DATO = 10;
	public static final int LOWER_BOUND = (-1) * (MARGINE_DATO)/2;
	public static final int UPPER_BOUND = (MARGINE_DATO)/2;
	
	
	public Elemento () {
		this.generaEquilibrio();
	}
	public int[][] getEquilibrio() {
		return equilibrio;
	}
	
	private void generaEquilibrio () {
		do {
			for (int numRiga = 0; numRiga <NUM_ELEMENTI - 2; numRiga ++) { //per ogni ciclo genera una riga e la copia nella rispettiva colonna per calcolare le successive
				this.setRiga(numRiga);
				this.setLastElement(numRiga);
				this.setColonna(numRiga);
			}
			this.setRiga(NUM_ELEMENTI - 2); //rimane la penultima riga e colonna in cui non ci sono piu elementi random
			this.setLastElement(NUM_ELEMENTI - 2);
			this.equilibrio[NUM_ELEMENTI - 1][NUM_ELEMENTI - 1] = 0; //rimane solo l'ultimo elemento da settare ed è inutile avviare un ciclo sapendo che dovra valere 0
		}while (!this.checkFinale());
	}
	
	private void setRiga(int riga) {
			this.equilibrio [riga][riga] = 0;
			Random ran = new Random();
			int i= riga + 1;
			while(i < NUM_ELEMENTI - 1) { //setta la trinagolare superiore esclusa l'ultima colonna}
				this.equilibrio[riga][i] = ran.nextInt(LOWER_BOUND, UPPER_BOUND);
				i++;
			}
	}
	
	private void setLastElement(int riga) {
		int somma = 0;
		for (int i = 0; i<NUM_ELEMENTI - 1; i++) {
			somma = somma +this.equilibrio[riga][i];
		}
		this.equilibrio[riga][NUM_ELEMENTI - 1] = somma * (-1);
	}
	
	private void setColonna(int colonna) {
		for (int i = colonna + 1; i < NUM_ELEMENTI; i ++) {
			this.equilibrio[i][colonna] = this.equilibrio[colonna][i]; //copia dalla riga immessa come parametro alla rispettiva colonna tutti i valori
		}
	}
	
	private boolean checkFinale() {
		for (int i =0; i < NUM_ELEMENTI; i++) {
			if (this.equilibrio[i][NUM_ELEMENTI -1] > Elemento.MARGINE_DATO) return false;
		}
		return true;
	}
	
	public void printMatrice(int dimensione) {
		for (int i=0; i < dimensione; i++) {
			System.out.print("|");
			for (int j=0; j<dimensione; j++) {
				System.out.print(String.format("%d \t", this.equilibrio[i][j]));
			}
			System.out.println("|");
		}
	}
	
}
