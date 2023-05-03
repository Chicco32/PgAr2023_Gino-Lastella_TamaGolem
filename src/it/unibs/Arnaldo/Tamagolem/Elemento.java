package it.unibs.Arnaldo.Tamagolem;

import java.util.Random;

public class Elemento {

	private int[][] equilibrio = new int[NUM_ELEMENTI_MAX][NUM_ELEMENTI_MAX];
	private int numElementiUsati;
	//setting della classe
	public enum tipoElemento {Terra, Aria, Fuoco, Acqua, Etere, Fisico, Veleno, Psiche, Magia, Oscuro};
	public static final int NUM_ELEMENTI_MAX = 10;
	public static final int MARGINE_DATO = 10;
	
	
	public Elemento(int _NnumElementiUsati) {
		this.numElementiUsati = _NnumElementiUsati;
		this.generaEquilibrio();
	}
	
	/**
	 * la funzione che effettivamente ti dice il danno subito dal golem difendente.
	 * Se i due golem usano pari elemento darà 0.
	 * Se il difende usa un elemento forte e quello che attacca un elemento debole darà 0.
	 * 
	 * @param elementoAttaccante tipoElemento.valueof() dell'elemento del golem che attacca nel turno
	 * @param elementoDifendente tipoElemento.valueof() dell'elemento del golem che difende nel turno
	 * @return il valore del danno subito come intero
	 */
	public int getDannoSubito(int elementoAttaccante, int elementoDifendente) {
		int danno = this.equilibrio[elementoAttaccante][elementoDifendente];
		if (danno > 0) return danno;
		else return 0;
	}
	
	private void generaEquilibrio () {
		do {
			for (int numRiga = 0; numRiga <this.numElementiUsati - 2; numRiga ++) { //per ogni ciclo genera una riga e la copia nella rispettiva colonna per calcolare le successive
				this.setRiga(numRiga);
				this.setLastElement(numRiga);
				this.setColonna(numRiga);
			}
			//rimane la penultima riga e colonna in cui non ci sono piu elementi random percio non vale avviare un ciclo
			this.setLastElement(this.numElementiUsati - 2);
			this.setColonna(this.numElementiUsati - 2);
			this.equilibrio[this.numElementiUsati - 1][this.numElementiUsati - 1] = 0; //rimane solo l'ultimo elemento da settare che dovra valere 0
		} while (!this.checkFinale());
	}
	
	/**
	 * setta la trinangolare superiore esclusa l'ultima colonna
	 * @param riga: inidce della riga da settare
	 */
	private void setRiga(int riga) {
			this.equilibrio [riga][riga] = 0;
			Random ran = new Random();
			int i= riga + 1;
			while(i < this.numElementiUsati - 1) {
				do {
					this.equilibrio[riga][i] = ran.nextInt(((-1) * MARGINE_DATO), MARGINE_DATO);
				} while(equilibrio[riga][i] == 0); //controlla che non setti scontri a 0
				i++;
			}
	}
	
	/**
	 * crea ad hoc lultimo elemento della riga
	 * @param riga:inidce della riga da settare
	 */
	private void setLastElement(int riga) {
		int somma = 0;
		for (int i = 0; i<this.numElementiUsati - 1; i++) {
			somma = somma +this.equilibrio[riga][i];
		}
		this.equilibrio[riga][this.numElementiUsati - 1] = somma * (-1);
	}
	
	/**
	 * copia dalla riga immessa come parametro alla rispettiva colonna tutti i valori
	 * @param colonna: inidce della riga da settare
	 */
	private void setColonna(int colonna) {
		for (int i = colonna + 1; i < this.numElementiUsati; i ++) {
			this.equilibrio[i][colonna] = (-1) * this.equilibrio[colonna][i];
		}
	}
	
	/**
	 * Controlla che gli elementi finali siano in regola con il valore
	 * @return false se la matrice non va bene, true se è utilizzaile 
	 */
	private boolean checkFinale() {
		for (int i =0; i < this.numElementiUsati - 1; i++) { //siccome controlla anche che non facciano zero gli scontri finali l'ulitmo elemento non deve vederlo (è ovvio che sia zero)
			if (Math.abs(this.equilibrio[i][this.numElementiUsati -1]) > Elemento.MARGINE_DATO || this.equilibrio[i][this.numElementiUsati -1] == 0) return false;
		}
		return true;
	}
	
	/**
	 * Stampa l'equilibrio come matrice a fine partita. un valore negativo significa che la freccia ideale punta nella direzione opposta leggendo riga attacca colonna
	 * @param il numero di elementi usati nella partita in caso di nume
	 */
	public void printMatrice(int dimensione) {
		tipoElemento[] tipi = tipoElemento.values();
		System.out.print("\t");
		for (int i=0; i < dimensione; i++) System.out.print(String.format("%s \t", tipi[i]));
		System.out.print("\n");
		for (int i=0; i < dimensione; i++) {
			System.out.print(String.format("%s \t", tipi[i]));
			System.out.print("|");
			for (int j=0; j<dimensione; j++) {
				System.out.print(String.format("%d \t", this.equilibrio[i][j]));
			}
			System.out.println("|");
		}
	}
	
}
