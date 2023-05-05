package it.unibs.Arnaldo.Tamagolem;

public class Giocatore {
    
    private String nome;
    private int nGolem;
    private Tamagolem golemAttivo;

    public Giocatore(String nome, int nGolem) {
        this.nome = nome;
        this.nGolem = nGolem;
    }

    public String getNome() {
        return this.nome;
    }

    public void setGolemAttivo(Tamagolem golemAttivo) {
        this.golemAttivo = golemAttivo;
    }

    public Tamagolem getGolemAttivo() {
        return this.golemAttivo;
    }

    /**
     * Rimuove il golem attivo e diminuisce di uno nGolem ad indicare che quel golem è rimosso dal gioco.
     * La funzione inoltre segnala se il giocatore è ancora possibilitato a giocare o meno.
     * Se nGOlem arriva a zero significa che il giocatore corrispondente non ha piu golem da evocare.
     * @return true se il giocatore ha ancora golem da poter evocare, false altrimenti
         */
    public void uccidiGolem() {
        this.golemAttivo = null;
        this.nGolem--;
    }

    /**
     * Controlla se il giocatore ha ancora tamagolem da evocare.
     * @return true se il giocatore ha ancora tamagolem, false altrimenti
     */
    public boolean hasGolem() {
        if (this.nGolem > 0)
            return true;
        return false;
    }
    
}
