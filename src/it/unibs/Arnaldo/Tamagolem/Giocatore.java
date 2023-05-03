package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayList;

public class Giocatore {

	private String nome;
	private ArrayList<Tamagolem> compagnia;
	
	/**
	 * istanzia un novo giocatore alla partita
	 * @param nome nome del giocatore
	 * @param nGolem numero di golem che ogni giocatore possiede
	 */
	public Giocatore(String nome, int nGolem) {
		super();
		this.nome = nome;
		this.compagnia = new ArrayList<>();
		for (int i = 0; i < nGolem; i++) { //per ogni giocatore crea una nuova compagnia e la carica gia col numero di golems
			this.compagnia.add(new Tamagolem());
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Tamagolem> getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(ArrayList<Tamagolem> compagnia) {
		this.compagnia = compagnia;
	}
	
	
	
}
