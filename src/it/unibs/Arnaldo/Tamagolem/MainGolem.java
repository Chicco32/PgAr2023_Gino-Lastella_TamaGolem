package it.unibs.Arnaldo.Tamagolem;


public class MainGolem {

	public static void main(String[] args) {
		boolean soddisfatto = false;
		IOStream.mostraBenvenuto();
		String nomeG1 = IOStream.chiediNome(1);
		String nomeG2 =IOStream.chiediNome(2);
		do {
			//prima fase dello scontro e generazione delle variabili
			int numElementiUsati = IOStream.chiediNumeroElementi();
			Partita partita = new Partita(nomeG1, nomeG2, numElementiUsati);
			
			// seconda fase dello sconntro e effettiva lotta fra i tamagolem
			while (!partita.fineScontro()) partita.scontro();
			
			// terza fase dello scontro e rivelazione finale
			partita.getEquilibrio().printMatrice();
			soddisfatto = IOStream.seiSoddisfatto();

		} while (!soddisfatto);
		
	}

}
