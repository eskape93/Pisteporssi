package porssi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Luokka joka pitää huolta pistepörssin otteluista
 * - osaa lisätä ja poistaa ottelun
 * - lukee ja kirjoittaa ottelun ja sen tietoja tiedostoon   
 * - osaa etsiä ja lajitella
 * @author Esa Hyyryläinen
 * @version 10.7.2015
 *
 */
public class Ottelut {

	
	//Taulukko otteluista
	private final Collection<Ottelu> ottelut = new ArrayList<Ottelu>();
	
	
	/**
	 * Lisää uuden ottelun tietorakenteeseen
	 * @param ottelu lisättävä ottelu
	 */
	public void lisaa(Ottelu ottelu) {
		ottelut.add(ottelu);
	}
	
	
	/**
	 * Poistaa ottelun tietorakenteesta
	 * @param ottelu poistettava ottelu
	 */
	public void poista(Ottelu ottelu) {
		ottelut.remove(ottelu);
	}
	
	
	/**
	 * Poistaa tietyn numeron omaavan ottelun
	 * @param otteluNro sen ottelun numero joka poistetaan
	 */
	public void poista(int otteluNro) {
		for(Ottelu ottelu: ottelut)
			if(ottelu.getOtteluNro()==otteluNro) {
				poista(ottelu);
				return;
			}
	}
	
	
	/**
	 * Palauttaa viitteen i:teen otteluun
	 * @param i monennenko ottelun viite halutaan
	 * @return viite otteluun, jonka indeksi on i
	 */
	public Ottelu anna(int i) {
		return ((ArrayList<Ottelu>)ottelut).get(i);
	}
	
	
	/**
	 * Antaa tietyn nimiehdon täyttävät ottelut
	 * @param ottelunNimea nimi ehto jolla otteluja haetaan
	 * @return palauttaa annettuun nimeen täsmäävät ottelut
	 */
	public Ottelu[] anna(String ottelunNimea) {
		Ottelu[] palautettavatOttelut = {};
		int i = 0;
		for(Ottelu ottelu: ottelut) {
			if(ottelu.getNimi().length()>=ottelunNimea.length() && ottelu.getNimi().substring(0, ottelunNimea.length()).equals(ottelunNimea)) {
					Ottelu[] palautettavatOttelut2 = palautettavatOttelut;
					palautettavatOttelut = new Ottelu[palautettavatOttelut2.length+1];
					for(int j=0; j<palautettavatOttelut2.length;j++)
						palautettavatOttelut[j]=palautettavatOttelut2[j];
					palautettavatOttelut[i] = ottelu;
					i++;
			}
		}
		return palautettavatOttelut;
	}
	
	
	/**
	 * Palauttaa halutulla numeroilla varustetut ottelut
	 * @param otteluNumerot tämän numeroiset ottelut halutaan
	 * @return palauttaa annetuilla numeroilla varustetut ottelut
	 */
	public Ottelu[] anna(int[] otteluNumerot) {
		Ottelu[] palautettavatOttelut = new Ottelu[otteluNumerot.length];
		int j=0;
		for(Ottelu ottelu: ottelut) {
			for(int i=0; i<otteluNumerot.length;i++)
				if(ottelu.getOtteluNro() == otteluNumerot[i]) {
					palautettavatOttelut[j]=ottelu;
					j++;
					break;
				}
		}
		return palautettavatOttelut;
	}
	
	
	/**
	 * Palauttaa pistepörssin ottelujen lukumäärän
	 * @return ottelujen lukumäärä
	 */
	public int getLkm() {
		return ottelut.size();
	}
	
	
	/**
	 * Tallentaa ottelut tiedostoon ottelut.dat
	 * @throws IOException jos tallennus epäonnistuu
	 */
	public void tallenna() throws IOException {
		File ottelutTiedosto = new File("ottelut.dat");

		try ( PrintWriter ottelutTallennus = new PrintWriter(new FileWriter(ottelutTiedosto.getCanonicalPath())) ) {
			ottelutTallennus.println("otteluid|nimi");
			for(Ottelu tallennettavaOttelu: ottelut) {
				ottelutTallennus.println(tallennettavaOttelu.getOtteluNro() + "|" + tallennettavaOttelu);
			}
		} /*catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	
	
	/**
	 * Lukee ottelut tiedostosta
	 * @throws FileNotFoundException jos tiedostoa ei löydy 
	 */
	public void lueTiedostosta() throws FileNotFoundException {
		try(Scanner ottelutTiedostoLuku = new Scanner(new FileReader("ottelut.dat"))) {
			ottelutTiedostoLuku.nextLine(); //ekalla rivillä ei luettavaa dataa
			while(ottelutTiedostoLuku.hasNextLine()) {
				Ottelu luettavaOttelu = new Ottelu();
				String rivi = ottelutTiedostoLuku.nextLine();
				luettavaOttelu.parse(rivi);
				lisaa(luettavaOttelu);
			}
		}
	}
	
	
	/**
	 * Testataan Ottelut luokkaa
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Ottelut ottelut = new Ottelut();

		Ottelu ottelu1 = new Ottelu();
		Ottelu ottelu2 = new Ottelu();
		Ottelu ottelu3 = new Ottelu();

		ottelu1.rekisteroi();
		ottelu2.rekisteroi();
		ottelu3.rekisteroi();


		ottelut.lisaa(ottelu1);
		ottelut.lisaa(ottelu2);
		ottelut.lisaa(ottelu3);
		ottelut.lisaa(ottelu3);
		ottelut.lisaa(ottelu3);
		ottelut.lisaa(ottelu3);

		System.out.println("======== Ottelut testi ========");

		System.out.println("Otteluja on " + ottelut.getLkm());

		//for(int i=0; i < ottelut.getLkm(); i++) {
		//	Ottelu ottelu = ottelut.anna(i);
		//	System.out.println("Ottelu nro: " + i);
		//	ottelu.tulosta(System.out);
		//}

	}
}
