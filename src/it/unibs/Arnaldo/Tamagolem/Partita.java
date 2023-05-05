package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayList;
import java.util.List;


public class Partita {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private List<Coppia> sacchetto;
    private int dimensioneMaxList;
    private Equilibrio eq;
    
    /**
     * riempie il sacchetto con le pietre distribuite equamente fra i vari elementi
     * @param nElementi elementi usati nella partita
     * @param nscortaComune la quantità massima di pietre nel sacchetto comune
     * @return il sacchetto pieno di pietre
     */
    public static List<Coppia> caricaSacchetto(int nElementi, int nscortaComune) {
		List<Coppia> nuovoSacchetto = new ArrayList<>();
		Elemento.TipoElemento[] tipi = Elemento.TipoElemento.values();
		for (int i=0; i<nElementi; i++) {
			nuovoSacchetto.add(new Coppia(tipi[i], Math.ceilDiv(nscortaComune, nElementi)));
		}
		return nuovoSacchetto;
    }
    
    
    public Partita(Giocatore giocatore1, Giocatore giocatore2, Equilibrio eq, int dimensioneMaxList)  {
        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;
        this.eq = eq;
        this.dimensioneMaxList = dimensioneMaxList;
    }

    public void scontro() {

    }

    public String fineScontro() {
        return null;
    }
    	
    
}

