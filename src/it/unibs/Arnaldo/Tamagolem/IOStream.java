package it.unibs.Arnaldo.Tamagolem;

import java.util.List;

import it.unibs.fp.mylib.InputDati;

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
	
	public static void mostraGolemMorto (String nomeGiocatore) {
		System.out.println(String.format("%s il tuo golem è morto!",nomeGiocatore));
	}
	
	public static void mostraSacchetto (List<Coppia> sacchetto) {
		System.out.println("La lista di possibili elementi in gioco:");
		for (Coppia coppia: sacchetto) {
			System.out.println(coppia.toString());
		}
	}
		
}
