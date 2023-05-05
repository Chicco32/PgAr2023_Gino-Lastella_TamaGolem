package it.unibs.Arnaldo.Tamagolem;

public class MainGolem {

	public static void main(String[] args) {
		boolean soddisfatto = false;
		IOStream.mostraBenvenuto();
		String nomeg1 = IOStream.chiediNome(1);
		String nomeg2 =IOStream.chiediNome(2);
		do {
			int elementiDiQuestaPartita = IOStream.chiediNumeroElementi();
			int nDiPietre = ((elementiDiQuestaPartita + 1)/3) + 1; //formula data dalle slides
			int nDiGolem = (elementiDiQuestaPartita - 1)*(elementiDiQuestaPartita - 2)/(nDiPietre * 2); //formula data dalle slides
			Equilibrio eq = new Equilibrio(elementiDiQuestaPartita);
			Giocatore g1 = new Giocatore (nomeg1, nDiGolem);
			Giocatore g2 = new Giocatore (nomeg2, nDiGolem);
			Partita partita = new Partita(g1,g2, eq);
			
			/*
			 * corpo della partita
			 */
			
			
			
			soddisfatto = IOStream.seiSoddisfatto(); 
		} while (!soddisfatto);
		
	}

}
