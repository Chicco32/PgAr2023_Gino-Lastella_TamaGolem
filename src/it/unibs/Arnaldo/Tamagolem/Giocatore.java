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
     * Rimuove il golem attivo e diminuisce nGolem
     * @return true se il giocatore ha ancora golem da poter evocare, false altrimenti
         */
    public boolean killGolem() {
        this.golemAttivo = null;
        this.nGolem--;
        if(this.nGolem > 0)
            return true;
        return false;
    }
    
}
