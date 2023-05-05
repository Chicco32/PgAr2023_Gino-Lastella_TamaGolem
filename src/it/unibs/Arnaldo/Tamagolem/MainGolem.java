package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayList;
import java.util.List;

public class MainGolem {

	public static void main(String[] args) {
		boolean soddisfatto = false;
		IOStream.mostraBenvenuto();
		String nomeg1 = IOStream.chiediNome(1);
		String nomeg2 =IOStream.chiediNome(2);
		do {
			//prima fase dello scontro e generazione delle variabili
			int elementiDiQuestaPartita = IOStream.chiediNumeroElementi();
			int nDiPietrePerGolem = Math.ceilDiv((elementiDiQuestaPartita + 1),3) + 1; //formula data dalle slides
			int nDiGolem = Math.ceilDiv((elementiDiQuestaPartita - 1)*(elementiDiQuestaPartita - 2),(nDiPietrePerGolem * 2)); //formula data dalle slides
			int scortaComune = Math.ceilDiv(2 * nDiGolem * nDiPietrePerGolem, elementiDiQuestaPartita) * elementiDiQuestaPartita; //formula data dalle slides
			Equilibrio eq = new Equilibrio(elementiDiQuestaPartita);
			Giocatore g1 = new Giocatore (nomeg1, nDiGolem);
			Giocatore g2 = new Giocatore (nomeg2, nDiGolem);
			Partita partita = new Partita(g1,g2, eq,scortaComune);
			List<Coppia> sacchettoProva = Partita.caricaSacchetto(elementiDiQuestaPartita, scortaComune);

			IOStream.caricaSlotPietre(sacchettoProva, elementiDiQuestaPartita, nDiPietrePerGolem, nomeg2);
			
			/*
			 * corpo della partita
			 */
			
			
			
			soddisfatto = IOStream.seiSoddisfatto(); 
		} while (!soddisfatto);
		
	}

}
