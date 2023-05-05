package it.unibs.Arnaldo.Tamagolem;

import java.util.List;


public class Partita {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private List<Coppia> sacchetto;
    private Equilibrio eq;
    public Partita(Giocatore giocatore1, Giocatore giocatore2, Equilibrio eq)  {
        this.giocatore1 = giocatore1;
        this.giocatore2 = giocatore2;
        this.eq = eq;
    }

    public void scontro() {

    }

    public String fineScontro() {
        return null;
    }
    	
    
    
}

