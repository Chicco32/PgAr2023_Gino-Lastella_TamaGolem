package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class Partita {
    
    private Giocatore giocatore1;
    private Giocatore giocatore2;
    private List<Coppia> sacchetto;
    private int nPietre;
    private Equilibrio equilibrio;
    
    public Equilibrio getEquilibrio() {
        return this.equilibrio;
    }
    
    /**
     * Il costruttore di partita comprende in se a prima fase dello scontro in quanto setta tutte le dinamiche fra equilibrio, giocatori e golem.
     * Prima si calcola tutti i parametri variabili, poi crea i golem dei nuovi giocatori settanto Giocatore e infine genera un nuovo equilibrio col costruttore della classe Equilibrio.
     * @param nomeG1 String nome del giocatore 1
     * @param nomeG2 String nome del giocatore 1
     * @param nElementi il numero di elementi selezionati per quella partita
     */
    public Partita(String nomeG1, String nomeG2, int nElementi)  {
        this.nPietre = Math.ceilDiv((nElementi + 1),3) + 1;
        int nGolem = Math.ceilDiv((nElementi - 1)*(nElementi - 2),(nPietre * 2));
        int scortaComune = Math.ceilDiv(2 * nGolem * this.nPietre, nElementi) * nElementi; //formule date dalle slides
        this.giocatore1 = new Giocatore(nomeG1, nGolem);
        this.giocatore2 = new Giocatore(nomeG2, nGolem);
        this.equilibrio = new Equilibrio(nElementi);        
        this.caricaSacchetto(nElementi, scortaComune);
    }

    /**
     * Riempie il sacchetto con le pietre distribuite equamente fra i vari elementi.
     * @param nElementi elementi usati nella partita
     * @param nscortaComune la quantità massima S di pietre nel sacchetto comune
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
    /**
     * La funzione scontro regola tutto cio che effettivamente avviene dietro le quinte in uno scontro nella seconda fase, risettando i golems e gestendo i danni e gli attacchi.
     * Essa non ha paramentri ne in ingresso ne in uscita perchè è strettamente collegata all'oggetto partita regolandone gli attributi senza l'ausilio esterno.
     * Ogni chiamata alla funzione scontro rappresenta uno e uno sol lancio di pietre fra golem, quindi va inserita in un ciclo che ne regola la quantità di chiamate.
     * Per mostare a video ciò che sta succedendo essa richiama la controparte IOStream mostraDanni ed eventualmente segnala la morte di uno dei due golem attivi nello scontro.
     */
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
     * Evoca un nuovo golem, chiamando la funzione di IOStream caricaSlotPietre.
     * @param giocatore giocatore a cui appartiene il golem
     */
    private void creaGolem(Giocatore giocatore) {
        Queue<Elemento.TipoElemento> pietre = IOStream.caricaSlotPietre(this.sacchetto, this.equilibrio.getNumElementiUsati(), this.nPietre, giocatore.getNome());
        Tamagolem golem = new Tamagolem(pietre);
        giocatore.setGolemAttivo(golem);
    }

    /**
     * La funzione fine scontro rappresenta effettivamente la terza fase dello scontro e contemporaneamente il controllo sul ciclo facendo da ponte fra prima e seconda fase. 
     * @return true se uno dei due giocatori ha finito i golem e decreta vincitore l'altro giocatore, false altrimenti
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

