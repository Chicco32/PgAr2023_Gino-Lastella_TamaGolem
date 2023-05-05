package it.unibs.Arnaldo.Tamagolem;

import java.util.Random;

public class Equilibrio {
	private int[][] equilibrio;
	private int numElementiUsati;
	
	//setting della classe
	public static final int NUM_ELEMENTI_MAX = 10;
	private int MARGINE_DATO = Tamagolem.VITA_MAX;
	
	/**
	 * Il costruttore di Equilibrio genera un nuovo equilibrio attraverso la chiamata alla funzione generaEquilibrio.
	 * @param numElementiUsati il numero di elementi che l'utente ha selezionato per questa partita
	 */
	public Equilibrio(int numElementiUsati) {
		this.numElementiUsati = numElementiUsati;
		this.equilibrio = new int[this.numElementiUsati][this.numElementiUsati];
		this.generaEquilibrio();
	}
	
	public int getNumElementiUsati() {
		return this.numElementiUsati;
	}
	
	/**
	 * La funzione che effettivamente ti dice il danno subito da uno dei due golem, esso può essere un numero positivo, negativo o zero.
	 * Se i due golem usano le stesse pietre la funzione restisce zero.
	 * Se il golem del giocatore uno lancia un elemento forte, essa restitutisce un valore positivo.
	 * Se il golem del giocatore due lancia un elemento forte, essa restitutisce un valore negativo (il cui danno è il modulo).
	 * @param elementoAttaccante il nome dell'elemento del golem che attacca nel turno
	 * @param elementoDifendente il nome dell'elemento del golem che difende nel turno
	 * @return il valore del danno subito come intero
	 */
	public int getDannoSubito(String elementoAttaccante, String elementoDifendente) {
		int elemento1 = Elemento.indiceElemento(elementoAttaccante); 
		int elemento2 = Elemento.indiceElemento(elementoDifendente);
		int danno = this.equilibrio[elemento1][elemento2];
		return danno;
	}
	
	/**
	 * La funzione che effettivamente riempie la matrice vuota in maniera pseudo-casuale.
	 * La funzione adopera un procedimento ad "L" alternando il setting di righe e colonne partendo dalla prima riga e colonna in alto a sinistra fino ad arrivare all'ultimo elemento in fondo a destra.
	 * Finita la generazione evoca la funzione checkFinale la quale controlla che la matrice rispetti i requisiti adatti ad essere usata.
	 * Nel caso in cui la matrice non fosse adatta, checkFinale riavvia il processo sovrascrivendo la matrice attuale fino a quando non ne genera una adatta. 
	 */
	private void generaEquilibrio() {
		do {
			for (int numRiga = 0; numRiga <this.numElementiUsati - 2; numRiga ++) { //per ogni ciclo genera una riga e la copia nella rispettiva colonna per calcolare le successive
				this.setRiga(numRiga);
				this.setLastElement(numRiga);
				this.setColonna(numRiga);
			}
			//rimane la penultima riga e colonna in cui non ci sono piu elementi random percio' non vale avviare un ciclo
			this.setLastElement(this.numElementiUsati - 2);
			this.setColonna(this.numElementiUsati - 2);
			this.equilibrio[this.numElementiUsati - 1][this.numElementiUsati - 1] = 0; //rimane solo l'ultimo elemento da settare che dovra valere 0
		} while (!this.checkFinale());
	}
	
	/**
	 * Setta la triangolare superiore esclusa l'ultima colonna.
	 * @param riga inidce della riga da settare
	 */
	private void setRiga(int riga) {
		this.equilibrio [riga][riga] = 0;
		Random ran = new Random();
		int i= riga + 1;
		while(i < this.numElementiUsati - 1) {
			do {
				this.equilibrio[riga][i] = ran.nextInt(((-1) * MARGINE_DATO), MARGINE_DATO + 1);
			} while(equilibrio[riga][i] == 0); //controlla che non setti scontri a 0
			i++;
		}
	}

	/**
	 * Crea ad hoc l'ultimo elemento della riga.
	 * @param riga inidce della riga da settare
	 */
	private void setLastElement(int riga) {
		int somma = 0;
		for (int i = 0; i<this.numElementiUsati - 1; i++) {
			somma = somma +this.equilibrio[riga][i];
		}
		this.equilibrio[riga][this.numElementiUsati - 1] = somma * (-1);
	}
	
	/**
	 * Copia dalla riga immessa come parametro alla rispettiva colonna tutti i valori.
	 * @param colonna inidce della riga da copiare nella rispettiva colonna
	 */
	private void setColonna(int colonna) {
		for (int i = colonna + 1; i < this.numElementiUsati; i ++) {
			this.equilibrio[i][colonna] = (-1) * this.equilibrio[colonna][i];
		}
	}
	
	/**
	 * Controlla che gli elementi finali siano in regola con il valore.
	 * @return false se la matrice non va bene, true se è utilizzaile 
	 */
	private boolean checkFinale() {
		for (int i = 0; i < this.numElementiUsati - 1; i++) { //siccome controlla anche che non facciano zero gli scontri finali l'ulitmo elemento non deve vederlo (è ovvio che sia zero)
			if (Math.abs(this.equilibrio[i][this.numElementiUsati -1]) > MARGINE_DATO || this.equilibrio[i][this.numElementiUsati -1] == 0) return false;
		}
		return true;
	}
	
	/**
	 * Rivela l'equilibrio come matrice a fine partita. Un valore negativo significa che la freccia ideale punta nella direzione opposta leggendo colonna attacca riga.
	 */
	public void printMatrice() {
		Elemento.TipoElemento[] tipi = Elemento.TipoElemento.values();
		System.out.print("\t");
		for (int i=0; i < this.numElementiUsati; i++) System.out.print(String.format("%s \t", tipi[i]));
		System.out.print("\n");
		for (int i=0; i < this.numElementiUsati; i++) {
			System.out.print(String.format("%s \t", tipi[i]));
			System.out.print("|");
			for (int j=0; j < this.numElementiUsati; j++) {
				System.out.print(String.format("%d \t", this.equilibrio[i][j]));
			}
			System.out.println("|");
		}
	}
	
}
