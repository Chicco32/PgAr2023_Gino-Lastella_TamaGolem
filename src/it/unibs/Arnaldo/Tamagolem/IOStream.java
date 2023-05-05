package it.unibs.Arnaldo.Tamagolem;

import java.util.List;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class IOStream {
	
	//imput del numero di elementi, nome giocatori, mostare il sacchetto, imput pietre da inserire nel golem, mostrare quando muore il golem, mostrare vittoria, mostrare danno
	
	public static int chiediNumeroElementi () {
		return InputDati.leggiIntero("Con quanti elementi volete giocare?", 3, 10);
	}
	
	public static String chiediNome (int ngiocatore) {
		return InputDati.leggiStringaNonVuota(String.format("Giocatore %d come ti chiami?",ngiocatore));
	}
	
	public static void mostraVincitore (String nomeGiocatore) {
		System.out.println(String.format("Compliemnti %s hai vinto!",nomeGiocatore));
	}
	
	public static void mostraBenvenuto () {
		System.out.println("Benvenuti in questa nuova lotta fra golem, preparatevi perchè sarà uno scontro memorabile!");
	}
	
	public static void mostraGolemMorto (String nomeGiocatore) {
		System.out.println(String.format("%s il tuo golem è morto!",nomeGiocatore));
	}
	
	/**
	 * mostra quante pietre ci sono nel sacchetto comune 
	 * @param sacchetto la lista di coppie pietra e numero di quella pietra
	 */
	public static void mostraSacchetto (List<Coppia> sacchetto) {
		System.out.println("La lista di possibili elementi in gioco:");
		for (Coppia coppia: sacchetto) {
			System.out.println(coppia.toString());
		}
	}
	
	/**
	 * mostra ai due giocatori cosa effettivamente sta succedendo, anzichè guardare cosa succede fra i golem si calcola lui il danno e lo mostra a schermo
	 * @param g1 il giocatore attaccante come oggetto
	 * @param g2 il giocatore difendente come oggetto
	 * @param elementoDiG1 il nome dell'elemento scagliato da g1
	 * @param elementoDiG2 il nome dell'elemento scagliato da g1
	 * @param eq serve sapere come funziona l'equilibrio generato in quella partita
	 */
	public static void mostraDanni (Giocatore g1, Giocatore g2, String elementoDiG1,String elementoDiG2, Equilibrio eq) {
		System.out.print(String.format("Il golem di %s lancia %s", g1.getNome(), elementoDiG1));
		System.out.print(String.format(" mentre il golem di %s lancia %s", g2.getNome(), elementoDiG2));
		int danno = eq.getDannoSubito(elementoDiG1, elementoDiG2);
		if (danno == 0) System.out.println("Essendo gli stessi elementi si annichiliscono!");
		else if (danno > 0) System.out.println(String.format("Il golem di %s subisce %d", g1.getNome(), danno));
		else if (danno < 0) System.out.println(String.format("Il golem di %s subisce %d", g2.getNome(), -danno));
	}
	
	public static boolean seiSoddisfatto() {
		String titolo = "Prodi giocatori, volete ancora sfidarvi in questo sanguinario combattimento?";
		String opzione[] = {"Continua"};
		MyMenu escMenu = new MyMenu(titolo, opzione);
		int scelta = escMenu.scegli();
		switch (scelta) {
		case 1: return false;
		default: return true;
		}
	}
		
}
