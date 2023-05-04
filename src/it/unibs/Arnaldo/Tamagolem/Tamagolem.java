package it.unibs.Arnaldo.Tamagolem;

import java.util.Queue;

public class Tamagolem {

    public static final int VITA_MAX = 10;
    private int vita; 
    private Queue<Elemento.TipoElemento> pietre;

    public Tamagolem(Queue<Elemento.TipoElemento> pietre) {
        this.pietre = pietre;
        this.vita = VITA_MAX;
    }

    /**
     * Scaglia la pietra in fondo alla coda e la rimette all'inizio
     * @return la pietra che viene scagliata
     */
    public Elemento.TipoElemento scagliaPietra() {
        Elemento.TipoElemento pietra = pietre.poll();
        pietre.add(pietra);
        return pietra;
    }

    /**
     * Infligge del danno al golem
     * @param danno quantità di danno inflitta
     * @return true se il golem sopravvive al colpo, false altrimenti
     */
    public boolean subisciDanno(int danno) {
        this.vita -= danno;
        if (this.vita <= 0)
            return false;
        return true;
    }
}
