package porssi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;


/**
 * Luokka ottelu pelaajille
 * - Osaa lis‰t‰ ja poistaa ottelusta pelaajia ja niiden tietoja
 * - lukee ja kirjoittaa ottelupelaajien tietoja tiedostoon
 * - osaa etsi‰ ja lajitella
 * @author Esa Hyyryl‰inen
 * @version 10.7.2015
 *
 */
public class OtteluPelaajat {

	
	//Taulukko ottelupelaajista
	private final Collection<OtteluPelaaja> otteluPelaajat = new ArrayList<OtteluPelaaja>();
	
	
	/**
	 * Lis‰‰ uuden ottelupelaajan tietorakenteeseen
	 * @param otteluPelaaja lis‰tt‰v‰ ottelu
	 */
	public void lisaa(OtteluPelaaja otteluPelaaja) {
		otteluPelaajat.add(otteluPelaaja);
	}
	
	
	/**
	 * Poistaa ottelupelaajan tietorakenteesta
	 * @param otteluPelaaja poistettava ottelupelaaja
	 */
	public void poista(OtteluPelaaja otteluPelaaja) {
		otteluPelaajat.remove(otteluPelaaja);
	}
	
	
	/**
	 * Poistaa ottelupelaajista kaikki tiettyyn pelaajanumeroon liittyv‰t tiedot
	 * t‰m‰n voisi ehk‰ hoitaa j‰rkev‰mminkin
	 * @param pelaajaNro poistaa kaikki t‰m‰n pelaajanumeron tiedot otteluPelaajista
	 */
	public void poistaOtteluPelaajat(int pelaajaNro) {
		for(int i=0; i<getLkm(); i++)
			if(pelaajaNro == anna(i).getPelaajaNro()) {
				poista(anna(i));
				i--;
			}
	}
	
	
	/**
	 * Poistaa pelaajan maalin/syˆtˆn/jne. ottelusta
	 * @param pelaajaNro sen pelaajan numero, jonka maali poistetaan
	 * @param otteluNro maali poistetaan t‰m‰n numeroisesta ottelusta
	 * @param rooli poistettavan rooli
	 */
	public void poistaYksittainen(int pelaajaNro, int otteluNro, int rooli) {
		for(int i=0; i<getLkm(); i++)
			if(otteluNro == anna(i).getOtteluNro() && pelaajaNro == anna(i).getPelaajaNro() && rooli == anna(i).getRooli()) {
				if(anna(i).getMontako()>1)
					anna(i).setMontako(anna(i).getMontako()-1);
				else
					poista(anna(i));
			}
	}
	
	
	/**
	 * Poistaa ottelupelaajista kaikki tiettyyn ottelunumeroon liittyv‰t tiedot
	 * t‰m‰n voisi ehk‰ hoitaa j‰rkev‰mminkin
	 * @param otteluNro poistaa kaikki t‰m‰n ottelunumeron tiedot otteluPelaajista
	 */
	public void poistaOtteluTiedot(int otteluNro) {
		for(int i=0; i<getLkm(); i++)
			if(otteluNro == anna(i).getOtteluNro()) {
				poista(anna(i));
				i--;
			}
	}
	
	
	/**
	 * Palauttaa viitteen i:teen otteluPelaajaan
	 * @param i monennenko otteluPelaajan viite halutaan
	 * @return viite ottelupelaajaan, jonka indeksi on i
	 */
	public OtteluPelaaja anna(int i) {
		return ((ArrayList<OtteluPelaaja>)otteluPelaajat).get(i);
	}
	
	
	/**
	 * Palauttaa pistepˆrssin ottelu tietojen lukum‰‰r‰n
	 * @return ottelujen lukum‰‰r‰
	 */
	public int getLkm() {
		return otteluPelaajat.size();
	}
	
	
	/**
	 * Tallentaa ottelupelaajat tiedostoon otteluPelaajat.dat
	 * @throws IOException jos tallennus ep‰onnistuu
	 */
	public void tallenna() throws IOException {
		File otteluPelaajatTiedosto = new File("otteluPelaajat.dat");

		try ( PrintWriter otteluPelaajatTallennus = new PrintWriter(new FileWriter(otteluPelaajatTiedosto.getCanonicalPath())) ) {
			otteluPelaajatTallennus.println("otteluid|pelaajaid|rooli|montako (1=maalintekij‰, 2=syˆtt‰j‰, 3=j‰‰hyll‰2min, 4=j‰‰hyll‰5min, 5=pelannut)");
			for(int i=0; i<getLkm(); i++) {
				OtteluPelaaja tallennettavaOtteluPelaaja = anna(i);
				otteluPelaajatTallennus.println(tallennettavaOtteluPelaaja.getOtteluNro() + "|" + tallennettavaOtteluPelaaja.getPelaajaNro() + "|" + tallennettavaOtteluPelaaja.getRooli() + "|" + tallennettavaOtteluPelaaja.getMontako());
			}
		}
	}
	
	
	/**
	 * Lukee otteluPelaajat tiedostosta
	 * @throws FileNotFoundException jos tiedostoa ei lˆydy
	 */
	public void lueTiedostosta() throws FileNotFoundException {
		try(Scanner otteluPelaajatTiedostoLuku = new Scanner(new FileReader("otteluPelaajat.dat"))) {
			otteluPelaajatTiedostoLuku.nextLine(); //ekalla rivill‰ ei luettavaa dataa
			while(otteluPelaajatTiedostoLuku.hasNextLine()) {
				OtteluPelaaja luettavaOtteluPelaaja = new OtteluPelaaja();
				String rivi = otteluPelaajatTiedostoLuku.nextLine();
				luettavaOtteluPelaaja.parse(rivi);
				lisaa(luettavaOtteluPelaaja);
			}
		}
	}
	
	
	/**
	 * Antaa tietyn tunnusNro omaavan pelaajan kaikki j‰‰hyminuutit
	 * @param tunnusNro pelaajan numero jota etsit‰‰n
	 * @return palauttaa pelaajan j‰‰hyminuutit yhteens‰
	 */
	public int annaPelaajanJaahyt(int tunnusNro) {
		int jaahyt = 0;
		for(int i = 0; i<getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = anna(i);
			if(tunnusNro == otteluPelaaja.getPelaajaNro() && (otteluPelaaja.getRooli() == 3 || otteluPelaaja.getRooli() == 4) ) {
				if(otteluPelaaja.getRooli() == 3)
					jaahyt += 2*otteluPelaaja.getMontako();
				else
					jaahyt += 5*otteluPelaaja.getMontako();
			}
		}
		return jaahyt;
	}
	
	
	/**
	 * Antaa pelaajan pelattujen ottelujen m‰‰r‰n
	 * @param tunnusNro pelaajan numero
	 * @return palauttaa pelaajan pelattujen ottelujen m‰‰r‰n
	 */
	public int annaPelaajanOttelujenMaara(int tunnusNro) {
		int otteluja = 0;
		for(int i=0; i<getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = anna(i);
			if(tunnusNro == otteluPelaaja.getPelaajaNro() && otteluPelaaja.getRooli() == 5)
				otteluja += otteluPelaaja.getMontako();
		}
		return otteluja;
	}
	
	
	/**
	 * Antaa pelaajan tekemien maalien m‰‰r‰n
	 * @param tunnusNro pelaajan numero
	 * @return pelaajan tekemien maalien m‰‰r‰
	 */
	public int annaPelaajanMaalit(int tunnusNro) {
		int maaleja = 0;
		for(int i=0; i<getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = anna(i);
			if(tunnusNro == otteluPelaaja.getPelaajaNro() && otteluPelaaja.getRooli() == 1)
				maaleja += otteluPelaaja.getMontako();
		}
		return maaleja;
	}
	
	
	/**
	 * Antaa pelaajan tekemien syˆttˆjen m‰‰r‰n
	 * @param tunnusNro pelaajan numero
	 * @return pelaajan tekemien syˆttˆjen m‰‰r‰
	 */
	public int annaPelaajanSyotot(int tunnusNro) {
		int syottoja = 0;
		for(int i=0; i<getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = anna(i);
			if(tunnusNro == otteluPelaaja.getPelaajaNro() && otteluPelaaja.getRooli() == 2)
				syottoja += otteluPelaaja.getMontako();
		}
		return syottoja;
	}
	
	
	/**
	 * Laskee pelaajan pisteiden m‰‰r‰n
	 * @param tunnusNro pelaajan numero
	 * @return Palauttaa pelaajan pisteiden m‰‰r‰n (maalit+syˆtˆt)
	 */
	public int annaPelaajanPisteet(int tunnusNro) {
		return annaPelaajanSyotot(tunnusNro) + annaPelaajanMaalit(tunnusNro);
	}
	
	
	/**
	 * Etsii tietyn kaltaista otteluPelaaja
	 * @param otteluPelaaja pelaaja jonka kaltaista etsit‰‰n lukuunottamatta montako kentt‰‰
	 * @return palauttaa etsityn kaltaisen otteluPelaajan indeksin, tai -1 jos ei lˆydy
	 */
	public int loytyyko(OtteluPelaaja otteluPelaaja) {
		for(int i=0; i<getLkm(); i++) {
			if(anna(i).getOtteluNro()==otteluPelaaja.getOtteluNro() && anna(i).getPelaajaNro()==otteluPelaaja.getPelaajaNro() && anna(i).getRooli() == otteluPelaaja.getRooli())
				return i;
		}
		return -1;
	}
	
	/**
	 * Antaa tietyss‰ ottelussa pelanneiden/maalintehneiden pelaajien tunnusnumerot riippuen roolista
	 * @param otteluNro ottelu, jossa pelanneet pelaajat halutaan selvitt‰‰
	 * @param rooli pelaajan rooli (1=maalintekij‰, 2=syˆtt‰j‰, 3=j‰‰hyll‰2min, 4=j‰‰hyll‰5min, 5=pelannut)
	 * @return palauttaa ottelussa pelanneiden pelaajien tunnusNumerot
	 */
	public int[] annaOttelussaPelanneidenNumerot(int otteluNro, int rooli) {
		int[] pelaajaNumerot = new int[0];
		int j = 0;
		for(int i=0; i<getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = anna(i);
			if(otteluNro == otteluPelaaja.getOtteluNro() && otteluPelaaja.getRooli() == rooli) {
				int montako = otteluPelaaja.getMontako();
				while(montako > 0) {
					int[] pelaajaNumerot2 = pelaajaNumerot;
					pelaajaNumerot = new int[pelaajaNumerot2.length+1];
					for(int k=0; k<pelaajaNumerot2.length; k++)
						pelaajaNumerot[k]=pelaajaNumerot2[k];
					pelaajaNumerot[j] = otteluPelaaja.getPelaajaNro();
					j++;
					montako--;
				}
			}
		}
		return pelaajaNumerot;
	}
	
	
	/**
	 * Antaa kaikkien niiden ottelujen numerot, joissa pelanneet annetut pelaajat annetussa roolissa
	 * @param pelaajaNumerot t‰m‰n numeroisia pelaajia haetaan
	 * @param rooli pelaajan rooli
	 * @return palauttaa kaikkien niiden ottelujen numerot, jotka t‰ytt‰v‰t ehdot
	 */
	public int[] annaOtteluNumerot(int[] pelaajaNumerot, int rooli) {
		int[] palautettavatNumerot = {};
		boolean eiLisata = false;
		for(OtteluPelaaja otteluPelaaja: otteluPelaajat) {
			if(otteluPelaaja.getRooli()==rooli)
				for(int i=0; i<pelaajaNumerot.length; i++) {
					if(pelaajaNumerot[i] == otteluPelaaja.getPelaajaNro()) {
						eiLisata = false;
						for(int j=0; j<palautettavatNumerot.length;j++) {
							if(palautettavatNumerot[j] == otteluPelaaja.getOtteluNro()) {
								eiLisata = true;
								break;
							}
						}
						if(eiLisata==false) {
							int[] palautettavatNumerot2 = palautettavatNumerot;
							palautettavatNumerot = new int[palautettavatNumerot2.length+1];
							for(int k=0; k<palautettavatNumerot2.length; k++) {
								palautettavatNumerot[k] = palautettavatNumerot2[k];
							}
							palautettavatNumerot[palautettavatNumerot.length-1]=otteluPelaaja.getOtteluNro();
							break;
						}
					}
				}
		}
		return palautettavatNumerot;
	}
	
	
	/**
	 * Testataan Ottelut luokkaa
	 * @param args ei k‰ytˆss‰
	 */
	public static void main(String[] args) {
		OtteluPelaajat otteluPelaajat = new OtteluPelaajat();

		OtteluPelaaja otteluPelaaja1 = new OtteluPelaaja();
		OtteluPelaaja otteluPelaaja2 = new OtteluPelaaja();
		OtteluPelaaja otteluPelaaja3 = new OtteluPelaaja();


		otteluPelaajat.lisaa(otteluPelaaja1);
		otteluPelaajat.lisaa(otteluPelaaja2);
		otteluPelaajat.lisaa(otteluPelaaja3);
		otteluPelaajat.lisaa(otteluPelaaja3);
		otteluPelaajat.lisaa(otteluPelaaja3);
		otteluPelaajat.lisaa(otteluPelaaja3);

		System.out.println("======== OtteluPelaajat testi ========");

		System.out.println("Tietoja otteluista on " + otteluPelaajat.getLkm());

		for(int i=0; i < otteluPelaajat.getLkm(); i++) {
			OtteluPelaaja otteluPelaaja = otteluPelaajat.anna(i);
			System.out.println("Tieto nro: " + i);
			otteluPelaaja.tulosta(System.out);
		}

	}
}
