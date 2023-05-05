package it.unibs.Arnaldo.Tamagolem;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Partita {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private List<Coppia> sacchetto;
    private int nPietre;
    private Equilibrio equilibrio;
    
    
    
    public Partita(String nomeG1, String nomeG2, int nElementi)  {
        this.nPietre = Math.ceilDiv((nElementi + 1),3) + 1;
        int nGolem = Math.ceilDiv((nElementi - 1)*(nElementi - 2),(nPietre * 2));
        this.giocatore1 = new Giocatore(nomeG1, nGolem);
        this.giocatore2 = new Giocatore(nomeG2, nGolem);
        this.equilibrio = new Equilibrio(nElementi);
        int scortaComune = Math.ceilDiv(2 * nGolem * this.nPietre, nElementi) * nElementi; //formula data dalle slides
        this.caricaSacchetto(nElementi, scortaComune);
    }
    
    public Equilibrio getEquilibrio() {
        return this.equilibrio;
    }

    /**
     * riempie il sacchetto con le pietre distribuite equamente fra i vari elementi
     * @param nElementi elementi usati nella partita
     * @param nscortaComune la quantità massima di pietre nel sacchetto comune
     * @return il sacchetto pieno di pietre
     */
    private void caricaSacchetto(int nElementi, int nscortaComune) {
        List<Coppia> nuovoSacchetto = new ArrayList<>();
        Elemento.TipoElemento[] tipi = Elemento.TipoElemento.values();
        for (int i=0; i<nElementi; i++) {
            nuovoSacchetto.add(new Coppia(tipi[i], Math.ceilDiv(nscortaComune, nElementi)));
        }
        this.sacchetto = nuovoSacchetto;
    }

    public void scontro() {
        if(this.giocatore1.getGolemAttivo() == null)
            this.creaGolem(giocatore1);
        if(this.giocatore2.getGolemAttivo() == null)
            this.creaGolem(giocatore2);
        Elemento.TipoElemento pietraG1 = this.giocatore1.getGolemAttivo().scagliaPietra();
        Elemento.TipoElemento pietraG2 = this.giocatore2.getGolemAttivo().scagliaPietra();
        int danno = this.equilibrio.getDannoSubito(pietraG1.toString(), pietraG2.toString());
        IOStream.mostraDanni(this.giocatore1.getNome(), this.giocatore2.getNome(), pietraG1.toString(), pietraG2.toString(), danno);
        if(danno > 0) {
            if(!this.giocatore2.getGolemAttivo().subisciDanno(danno)) {
                giocatore2.uccidiGolem();
                IOStream.mostraGolemMorto(giocatore2.getNome());
            }
        }
        else if(danno < 0) {
            if(!this.giocatore1.getGolemAttivo().subisciDanno(danno)) {
                giocatore1.uccidiGolem();
                IOStream.mostraGolemMorto(giocatore1.getNome());
            }
        }
    }

    /**
     * Evoca un nuovo golem, chiamando la funzione di input caricaSlotPietre
     * @param giocatore giocatore a cui appartiene il golem
     */
    private void creaGolem(Giocatore giocatore) {
        Queue<Elemento.TipoElemento> pietre = IOStream.caricaSlotPietre(this.sacchetto, this.equilibrio.getNumElementiUsati(), this.nPietre, giocatore.getNome());
        Tamagolem golem = new Tamagolem(pietre);
        giocatore.setGolemAttivo(golem);
    }

    /**
     * Controlla se lo scontro è finito e scrive il messaggio
     * @return true se uno dei due giocatori ha finito i golem
     */
    public boolean fineScontro() {
        if(!this.giocatore1.hasGolem()) {
            IOStream.mostraVincitore(this.giocatore2.getNome());
            return true;
        }
        else if(!this.giocatore2.hasGolem()) {
            IOStream.mostraVincitore(this.giocatore1.getNome());
            return true;
        }
        return false;
    }
    	
    
}

