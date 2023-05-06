package it.unibs.Arnaldo.Tamagolem;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import it.unibs.fp.mylib.InputDati;
import it.unibs.fp.mylib.MyMenu;

public class IOStream {
	
	//mostrare golem rimasti in gioco, mostare vita dei golem attivi, fare una pausa fra un attacco e l'altro 
	
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
		System.out.println("Benvenuti in questa nuova lotta fra golem, preparatevi perche' sara' uno scontro memorabile!");
	}
	
	public static void mostraGolemMorto (String nomeGiocatore) {
		System.out.println(String.format("\n%s il tuo golem e' morto!",nomeGiocatore));
	}
	
	public static void inserimentoInvalido() {
		System.out.println("Spiacenti, l'inserimento dato non e' valido");
	}
	
	/**
	 * Mostra quante pietre ci sono nel sacchetto comune dei giocatori. 
	 * @param sacchetto la lista di coppie pietra e numero di quella pietra
	 */
	public static void mostraSacchetto (List<Coppia> sacchetto) {
		System.out.println("La lista di possibili elementi in gioco:");
		for (Coppia coppia: sacchetto) {
			if (coppia.getQuantita()>0)System.out.print(coppia.toString());
		}
	}
	
	/**
	 * Mostra ai due giocatori cosa effettivamente sta succedendo durante uno scontro fra tamagolem.
	 * La funzione a seconda del segno di danno mostra quale golem ha subito dei danni nello scontro.
	 * @param g1 il nome del giocatore attaccante
	 * @param g2 il nome del giocatore difendente
	 * @param elementoDiG1 il nome dell'elemento scagliato da g1
	 * @param elementoDiG2 il nome dell'elemento scagliato da g2
	 * @param danno il valore positivo negativo o zero del danno di quello scontro come intero
	 */
	public static void mostraDanni (String g1, String g2, String elementoDiG1, String elementoDiG2, int danno) {
		System.out.print(String.format("Il golem di %s lancia %s", g1, elementoDiG1));
		System.out.print(String.format(" mentre il golem di %s lancia %s\n", g2, elementoDiG2));
		if (danno == 0) System.out.println("Essendo gli stessi elementi si annichiliscono!");
		else if (danno > 0) System.out.println(String.format("Il golem di %s subisce %d danni", g2, danno));
		else if (danno < 0) System.out.println(String.format("Il golem di %s subisce %d danni", g1, -danno));
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
	
	/**
	 * L'interfaccia da evocare ogni volta che si carica di pietre un nuovo tamagolem quando viene evocato.
	 * La funzione aggiorna in autonomia il sacchetto di pietre dei giocatori.
	 * @param sacchetto deve riceve il sacchetto comune per mostarrlo
	 * @param nElementi int del numero di elementi usati nella partita
	 * @param pietrePerGolem il numero P di pietre che ogni golem deve mangiare
	 * @param nomeGiocatore il nome di colui che ha evocato il golem e deve dargli in pasto le pietre
	 * @return una Queue<Elemento.TipoElemento> carica di pietre 
	 */
	public static Queue<Elemento.TipoElemento> caricaSlotPietre (List<Coppia> sacchetto, int nElementi, int pietrePerGolem, String nomeGiocatore) {
		Queue<Elemento.TipoElemento> nuovoSlot = new ArrayDeque<>();
		String nomeElemento;
		//mostra le pietre disponibili all'utente
		System.out.println(String.format("%s Ecco le pietre che puoi caricare", nomeGiocatore));
		for (int i= 0; i < pietrePerGolem; i++) {
			System.out.println(String.format("Pietre Disponibili (%d)", pietrePerGolem - i));
			IOStream.mostraSacchetto(sacchetto);
			System.out.println("\n");
			boolean valido = false;
			do {
				nomeElemento = InputDati.leggiStringaNonVuota("Inserisci il nome dell'elemento");
				//normalizzazione a prima lettera maiuscola
				String iniziale = nomeElemento.substring(0,1).toUpperCase(); //setta la prima lettera maiscola
				String corpo = nomeElemento.substring(1).toLowerCase(); //setta il corpo minuscolo
				nomeElemento = iniziale + corpo; //ricompone la parola
				//controllo della validità dell'inserimento
				if (Elemento.indiceElemento(nomeElemento) >= nElementi) IOStream.inserimentoInvalido(); // se mette un elemento che è oltre quelli disponibili (tipo magia con 3 elementi) o sbaglia a scrivere lo scarta
				else if (sacchetto.get(Elemento.indiceElemento(nomeElemento)).getQuantita() == 0) IOStream.inserimentoInvalido(); // se mette un elemento tra quelli disponibili ma la cui quantità è zero va scartato (son finite le pietre)
				else valido = true;
			} while (!valido);
			//aggiunta allo slot golem e rimozione dal sacchetto
			nuovoSlot.add(Elemento.TipoElemento.valueOf(nomeElemento));
			sacchetto.get(Elemento.indiceElemento(nomeElemento)).diminuisciQuantita();
		}
		return nuovoSlot;
	}
	
}
