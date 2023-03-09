package porssi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/** Luokka, joka pitää huolta pistepörssin pelaajista
 * @author Esa Hyyryläinen
 * @version 28.6.2015
 *
 */
public class Pelaajat {
	
	private static final int MAX_PELAAJIA = 5;

	private int lkm;
	private Pelaaja[] alkiot;

	/**
	 * Luodaan 5 kokoinen pelaajisto
	 */
	public Pelaajat() {
		alkiot = new Pelaaja[MAX_PELAAJIA];
		lkm = 0;
	}

	/**
	 * Lisaa uuden pelaajan tietorakenteeseen. Ottaa pelaajan omistukseensa.
	 * @param uusiPelaaja lisättävän pelaajan viite.
	 * @example
	 * <pre name="test">
	 * #THROWS SailoException //tee uusi testi
	 * <pre>
	 */
	public void lisaa(Pelaaja uusiPelaaja) {
		if(lkm >= alkiot.length) {
			Pelaaja[] alkiot2 = alkiot;
			alkiot = new Pelaaja[alkiot2.length+10];
			
			for(int i = 0; i < alkiot2.length; i++)
				alkiot[i] = alkiot2[i];
		}
		alkiot[lkm] = uusiPelaaja;
		lkm++;
	}
	
	
	/**
	 * Poistaa tietyn tunnusnumeron omaavan pelaajan
	 * @param pelaajaNro sen pelaajan numero joka poistetaan
	 */
	public void poistaPelaaja(int pelaajaNro) {
		for(int i=0, j=0; i<lkm; i++) {
			if(anna(i).getTunnusNro() != pelaajaNro)
				alkiot[j++] = alkiot[i];
		}
		lkm--;
	}
	
	
	/**
	 * Palauttaa viitteen i:teen pelaajaan
	 * @param i monennenko pelaajan viite halutaan
	 * @return viite pelaajaan, jonka indeksi on i
	 */
	public Pelaaja anna(int i) {
		return alkiot[i];
	}

	
	/**
	 * Palauttaa pelaajan, jonka tunnusNro on annettu tunnusNro
	 * @param tunnusNro sen pelaajan tunnusNro jota etsitään
	 * @return palauttaa pelaajan, joka omaa etsityn tunnusnumeron
	 */
	public Pelaaja annaNumerolla(int tunnusNro) {
		for(int i=0; i<getLkm(); i++)
			if(tunnusNro == alkiot[i].getTunnusNro())
				return alkiot[i];
		//voisi palauttaa pelaajan, jonka nimi virhe tai null tai sitten erillinen aliohjelma, joka palauttaa -1 jos ei löydy ja virheen
		return new Pelaaja();
	}
	
	
	/**
	 * Palauttaa pistepörssin pelaajien lukumäärän
	 * @return pelaajien lukumäärä
	 */
	public int getLkm() {
		return lkm;
	}
	
	
	/**
	 * Tallentaa pelaajat
	 * @throws IOException jos tallennus epäonnistuu
	 */
	public void tallenna() throws IOException {
		File pelaajatTiedosto = new File("pelaajat.dat");
		
		try ( PrintWriter pelaajatTallennus = new PrintWriter(new FileWriter(pelaajatTiedosto.getCanonicalPath())) ) {
			pelaajatTallennus.println("Pelaajaid|Pelaajan nimi|Pelipaikka");
			for(int i=0; i<getLkm(); i++) {
				Pelaaja talletettavaPelaaja = anna(i);
				pelaajatTallennus.println(talletettavaPelaaja.getTunnusNro() + "|" + talletettavaPelaaja + "|" + talletettavaPelaaja.getPelipaikka());
			}
		}
	}
	
	
	/**
	 * Lukee pelaajat tiedostosta
	 * @throws FileNotFoundException jos tiedostoa ei löydy
	 */
	public void lueTiedostosta() throws FileNotFoundException {
		try(Scanner pelaajatTiedostoLuku = new Scanner(new FileReader("pelaajat.dat"))) {
			pelaajatTiedostoLuku.nextLine(); //ekalla rivillä ei luettavaa dataa
			while(pelaajatTiedostoLuku.hasNextLine()) {
				Pelaaja luettavaPelaaja = new Pelaaja();
				String rivi = pelaajatTiedostoLuku.nextLine();
				luettavaPelaaja.parse(rivi);
				lisaa(luettavaPelaaja);
			}
		}
	}
	
	
	/**
	 * Antaa tietyn nimiehdon täyttävien pelaajien numerot
	 * @param pelaajanNimea nimi ehto jolla pelaajia haetaan
	 * @return palauttaa annettuun nimeen täsmäävien pelaajien numerot
	 */
	public int[] anna(String pelaajanNimea) {
		int[] palautettavatNumerot = {};
		int i = 0;
		for(Pelaaja pelaaja: alkiot) {
			if(pelaaja != null && pelaaja.toString().length()>=pelaajanNimea.length() && pelaaja.toString().substring(0, pelaajanNimea.length()).equals(pelaajanNimea)) {
					int[] palautettavatNumerot2 = palautettavatNumerot;
					palautettavatNumerot = new int[palautettavatNumerot2.length+1];
					for(int j=0; j<palautettavatNumerot2.length;j++)
						palautettavatNumerot[j]=palautettavatNumerot2[j];
					palautettavatNumerot[i] = pelaaja.getTunnusNro();
					i++;
			}
		}
		return palautettavatNumerot;
	}

	
	/**
	 * Testataan Pelaajat luokkaa
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Pelaajat pelaajat = new Pelaajat();

		Pelaaja pelaaja1 = new Pelaaja();
		Pelaaja pelaaja2 = new Pelaaja();
		Pelaaja pelaaja3 = new Pelaaja();

		pelaaja1.rekisteroi();
		pelaaja2.rekisteroi();
		pelaaja3.rekisteroi();


			pelaajat.lisaa(pelaaja1);
			pelaajat.lisaa(pelaaja2);
			pelaajat.lisaa(pelaaja3);
			pelaajat.lisaa(pelaaja3);
			pelaajat.lisaa(pelaaja3);
			pelaajat.lisaa(pelaaja3);

		System.out.println("======== Pelaajat testi ========");

		System.out.println("Pelaajia on " + pelaajat.getLkm());

		for(int i=0; i< pelaajat.getLkm(); i++) {
			Pelaaja pelaaja = pelaajat.anna(i);
			System.out.println("Pelaaja nro: " + i);
			pelaaja.tulosta(System.out);
		}

	}

}

