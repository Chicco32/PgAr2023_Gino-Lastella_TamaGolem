package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayList;

public class Tamagolem {

	public static final int VITA_INIZIALE = Elemento.MARGINE_DATO * 5;
	private int vita;
	private ArrayList<Pietra> slotPietre;
	private boolean sulCampo;
	
	public Tamagolem() {
		this.vita = Tamagolem.VITA_INIZIALE;
		this.slotPietre = new ArrayList<>();
		this.sulCampo = false;
	}

	public int getVita() {
		return vita;
	}

	public void setVita(int vita) {
		this.vita = vita;
	}

	public ArrayList<Pietra> getSlotPietre() {
		return slotPietre;
	}

	public void setSlotPietre(ArrayList<Pietra> slotPietre) {
		this.slotPietre = slotPietre;
	}

	public boolean isSulCampo() {
		return sulCampo;
	}

	public void setSulCampo(boolean sulCampo) {
		this.sulCampo = sulCampo;
	}
	
	
}
