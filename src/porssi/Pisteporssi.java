package porssi;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * T�h�n CRC-kortista kopio???
 * @author Esa Hyyryl�inen
 * @version 30.6.2015
 *
 */
public class Pisteporssi {
	
	private Pelaajat pelaajat;
	private Ottelut ottelut;
	private OtteluPelaajat otteluPelaajat;

	/**
	 * Luodaan pelaajisto
	 */
	public Pisteporssi() {
		pelaajat = new Pelaajat();
		ottelut = new Ottelut();
		otteluPelaajat = new OtteluPelaajat();
	}
	
	
	/**
	 * @param pelaaja lis�tt�v� uusi pelaaja
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * Pelaaja pelaaja1 = new Pelaaja();
	 * Pelaaja pelaaja2 = new Pelaaja();
	 * Pelaaja pelaaja3 = new Pelaaja();
	 * pisteporssi.getPelaajia() === 0;
	 * pisteporssi.lisaa(pelaaja1);
	 * pisteporssi.getPelaajia() === 1;
	 * pisteporssi.lisaa(pelaaja2);
	 * pisteporssi.getPelaajia() === 2;
	 * pisteporssi.lisaa(pelaaja3);
	 * pisteporssi.getPelaajia() === 3;
	 * </pre>
	 */
	public void lisaa(Pelaaja pelaaja) {
		pelaajat.lisaa(pelaaja);
	}
	
	
	/**
	 * Poistaa pelaajan ja siihen liittyv�t ottelutiedot
	 * @param pelaajaNro sen pelaajan numero, joka poistetaan
	 */
	public void poistaPelaaja(int pelaajaNro) {
		pelaajat.poistaPelaaja(pelaajaNro);
		otteluPelaajat.poistaOtteluPelaajat(pelaajaNro);
	}
	
	
	/**
	 * Poistaa ottelun ja siihen liittyv�t ottelutiedot
	 * @param otteluNro sen ottelun numero, joka poistetaan
	 */
	public void poistaOttelu(int otteluNro) {
		ottelut.poista(otteluNro);
		otteluPelaajat.poistaOtteluTiedot(otteluNro);
	}
	
	
	/**
	 * Poistaa pelaajan maalin/sy�t�n/jne. ottelusta
	 * @param pelaajaNro sen pelaajan numero, jonka maali poistetaan
	 * @param otteluNro maali poistetaan t�st� ottelusta
	 * @param rooli poistettavan rooli (1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut)
	 */
	public void poistaYksittainen(int pelaajaNro, int otteluNro, int rooli) {
		otteluPelaajat.poistaYksittainen(pelaajaNro, otteluNro, rooli);
	}
	
	
	/**
	 * Lis�� ottelun p�rssiin
	 * @param ottelu lis�tt�v� uusi ottelu
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * Ottelu ottelu1 = new Ottelu();
	 * Ottelu ottelu2 = new Ottelu();
	 * Ottelu ottelu3 = new Ottelu();
	 * pisteporssi.getOtteluja() === 0;
	 * pisteporssi.lisaa(ottelu1);
	 * pisteporssi.getOtteluja() === 1;
	 * pisteporssi.lisaa(ottelu2);
	 * pisteporssi.getOtteluja() === 2;
	 * pisteporssi.lisaa(ottelu3);
	 * pisteporssi.getOtteluja() === 3;
	 * </pre>
	 */
	public void lisaa(Ottelu ottelu) {
		ottelut.lisaa(ottelu);
	}
	
	
	/**
	 * @param otteluPelaaja lis�tt�v� ottelu pelaaja
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * OtteluPelaaja otteluPelaaja1 = new OtteluPelaaja();
	 * OtteluPelaaja otteluPelaaja2 = new OtteluPelaaja();
	 * OtteluPelaaja otteluPelaaja3 = new OtteluPelaaja();
	 * pisteporssi.getOtteluPelaajia() === 0;
	 * pisteporssi.lisaa(otteluPelaaja1);
	 * pisteporssi.getOtteluPelaajia() === 1;
	 * pisteporssi.lisaa(otteluPelaaja2);
	 * pisteporssi.getOtteluPelaajia() === 2;
	 * pisteporssi.lisaa(otteluPelaaja3);
	 * pisteporssi.getOtteluPelaajia() === 3;
	 * </pre>
	 */
	public void lisaa(OtteluPelaaja otteluPelaaja) {
		otteluPelaajat.lisaa(otteluPelaaja);
	}

	
	/**
	 * @return pistep�rssin pelaajam��r�
	 */
	public int getPelaajia() {
		return pelaajat.getLkm();
	}

	
	/**
	 * @return pistep�rssin ottelum��r�
	 */
	public int getOtteluja() {
		return ottelut.getLkm();
	}
	
	
	/**
	 * @return ottelutietojen lukum��r�
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * OtteluPelaaja otteluPelaaja1 = new OtteluPelaaja();
	 * OtteluPelaaja otteluPelaaja2 = new OtteluPelaaja();
	 * pisteporssi.getOtteluPelaajia() === 0;
	 * pisteporssi.lisaa(otteluPelaaja1);
	 * pisteporssi.getOtteluPelaajia() === 1;
	 * pisteporssi.lisaa(otteluPelaaja2);
	 * pisteporssi.getOtteluPelaajia() === 2;
	 * </pre>
	 */
	public int getOtteluPelaajia() {
		return otteluPelaajat.getLkm();
	}

	
	/**
	 * Antaa viitteen i;nteen pelaajaan
	 * @param i monesko pelaaja annetaan
	 * @return viite i:nteen pelaajaan
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * Pelaaja pelaaja1 = new Pelaaja();
	 * Pelaaja pelaaja2 = new Pelaaja();
	 * pisteporssi.lisaa(pelaaja1);
	 * pisteporssi.lisaa(pelaaja2);
	 * pisteporssi.annaPelaaja(0) === pelaaja1;
	 * pisteporssi.annaPelaaja(1) === pelaaja2;
	 * </pre>
	 */
	public Pelaaja annaPelaaja(int i) {
		return pelaajat.anna(i);
	}
	
	
	/**
	 * Antaa viitteen i;nteen otteluun
	 * @param i monesko ottelu annetaan
	 * @return viite i:nteen otteluun
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * Ottelu ottelu1 = new Ottelu();
	 * Ottelu ottelu2 = new Ottelu();
	 * pisteporssi.lisaa(ottelu1);
	 * pisteporssi.lisaa(ottelu2);
	 * pisteporssi.annaOttelu(0) === ottelu1;
	 * pisteporssi.annaOttelu(1) === ottelu2;
	 * </pre>
	 */
	public Ottelu annaOttelu(int i) {
		return ottelut.anna(i);
	}
	
	
	/**
	 * Antaa viitteen i;nteen otteluPelaajaan
	 * @param i monesko ottelutieto annetaan
	 * @return viite i:nteen ottelutietoon
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * OtteluPelaaja otteluPelaaja1 = new OtteluPelaaja();
	 * OtteluPelaaja otteluPelaaja2 = new OtteluPelaaja();
	 * pisteporssi.lisaa(otteluPelaaja1);
	 * pisteporssi.lisaa(otteluPelaaja2);
	 * pisteporssi.annaOtteluPelaaja(0) === otteluPelaaja1;
	 * pisteporssi.annaOtteluPelaaja(1) === otteluPelaaja2;
	 * </pre>
	 */
	public OtteluPelaaja annaOtteluPelaaja(int i) {
		return otteluPelaajat.anna(i);
	}
	
	
	/**
	 * Antaa tietyn pelaajan j��hy minuutit
	 * @param tunnusNro pelaajan tunnusNro
	 * @return j��hyt minuuteissa
	 * @example
	 * <pre name="test">
	 * Pisteporssi pisteporssi = new Pisteporssi();
	 * OtteluPelaaja otteluPelaaja1 = new OtteluPelaaja();
	 * OtteluPelaaja otteluPelaaja2 = new OtteluPelaaja();
	 * Pelaaja pelaaja1 = new Pelaaja();
	 * pelaaja1.rekisteroi();
	 * pisteporssi.lisaa(pelaaja1);
	 * pisteporssi.lisaa(otteluPelaaja1);
	 * pisteporssi.lisaa(otteluPelaaja2);
	 * pisteporssi.annaPelaajanJaahyt(pelaaja1.getTunnusNro()) === 0;
	 * otteluPelaaja1.setRooli(4);
	 * otteluPelaaja1.setMontako(4);
	 * otteluPelaaja1.setPelaajaNro(pelaaja1.getTunnusNro());
	 * pisteporssi.annaPelaajanJaahyt(pelaaja1.getTunnusNro()) === 20;
	 * otteluPelaaja2.setRooli(3);
	 * otteluPelaaja2.setMontako(2);
	 * otteluPelaaja2.setPelaajaNro(pelaaja1.getTunnusNro());
	 * pisteporssi.annaPelaajanJaahyt(pelaaja1.getTunnusNro()) === 24;
	 * </pre>
	 */
	public int annaPelaajanJaahyt(int tunnusNro) {
		return otteluPelaajat.annaPelaajanJaahyt(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajien pelattujen ottelujen m��r�n
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan pelattujen otteluiden m��r�n
	 */
	public int annaPelaajanOttelujenMaara(int tunnusNro) {
		return otteluPelaajat.annaPelaajanOttelujenMaara(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajien tekemien maalien m��r�n
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan tekem�t maalit
	 */
	public int annaPelaajanMaalit(int tunnusNro) {
		return otteluPelaajat.annaPelaajanMaalit(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajan sy�tt�jen m��r�n
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan sy�tt�m�t sy�t�t
	 */
	public int annaPelaajanSyotot(int tunnusNro) {
		return otteluPelaajat.annaPelaajanSyotot(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajan pisteiden m��r�n (maalit+syotot)
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan pisteet=maalit+syotot
	 */
	public int annaPelaajanPisteet(int tunnusNro) {
		return otteluPelaajat.annaPelaajanPisteet(tunnusNro);
	}
	
	
	/**
	 * Tutkii onko etsityn kaltainen otteluPelaaja jo p�rssiss�
	 * @param otteluPelaaja tieto, jonka kaltaista etsit��n ei huomioida montako kentt��
	 * @return palauttaa indeksin ottelupelaajaan jos l�ytyy, muuten -1
	 */
	public int loytyyko(OtteluPelaaja otteluPelaaja) {
		return otteluPelaajat.loytyyko(otteluPelaaja);
	}
	
	
	/**
	 * Antaa taulukon tietyss� ottelussa pelanneiden/maalin tehneist�/sy�tt�neist� pelaajista, riippuen roolista
	 * @param otteluNro ottelu jossa pelanneet pelaajat halutaan saada
	 * @param rooli mit� roolia haetaan (1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut)
	 * @return palauttaa taulukon ottelussa pelanneista pelaajista
	 */
	public Pelaaja[] annaOttelunPelaajat(int otteluNro, int rooli) {
		int[] pelaajienNumerot = otteluPelaajat.annaOttelussaPelanneidenNumerot(otteluNro, rooli);
		Pelaaja[] pelanneet = new Pelaaja[pelaajienNumerot.length];
		for(int i=0; i<pelaajienNumerot.length; i++)
			pelanneet[i] = pelaajat.annaNumerolla(pelaajienNumerot[i]);
		return pelanneet;
	}
	
	
	/**
	 * Tallentaa pistep�rssin tiedostoon
	 * @throws IOException jos tallennus ep�onnistuu
	 */
	public void tallenna() throws IOException {
		//luo oma kansio sy�tetylle nimelle mihin tallennetaan
		pelaajat.tallenna();
		ottelut.tallenna();
		otteluPelaajat.tallenna();
	}
	
	
	/**
	 * Lukee pistep�rssin tiedostosta
	 * @throws FileNotFoundException jos tiedostoa ei l�ydy
	 */
	public void lueTiedostosta() throws FileNotFoundException {
		pelaajat.lueTiedostosta();
		ottelut.lueTiedostosta();
		otteluPelaajat.lueTiedostosta();
	}
	
	
	/**
	 * Tutkii l�ytyyk� tietyn nimist� pelaajaa p�rssist�, jos l�ytyy palauttaa t�m�n numeron, muuten palauttaa -1
	 * @param nimi t�m�n nimist� pelaajaa etsit��n
	 * @return palauttaa pelaajan tunnusnumeron, jos ei l�ydy palauttaa -1
	 */
	public int onkoTamanNimista(String nimi) {
		for(int i=0; i<getPelaajia();i++)
			if(nimi.equals(annaPelaaja(i).toString()))
				return annaPelaaja(i).getTunnusNro();
		return -1;
	}
	
	
	/**
	 * Tutkii l�ytyyk� tietyn nimist� ottelua p�rssist�, jos l�ytyy palauttaa t�m�n numeron, muuten palauttaa -1
	 * @param nimi t�m�n nimist� ottelua etsit��n
	 * @return palauttaa ottelun tunnusnumeron, jos ei l�ydy palauttaa -1
	 */
	public int onkoTamanNimistaOttelua(String nimi) {
		for(int i=0; i<getOtteluja(); i++)
			if(nimi.equals(annaOttelu(i).toString()))
				return annaOttelu(i).getOtteluNro();
		return -1;
	}
	
	
	/**
	 * Antaa tietyn nimiehden t�ytt�v�t ottelut
	 * @param ottelunNimea nimi ehto jolla otteluja haetaan
	 * @return palauttaa annettuun nimeen t�sm��v�t ottelut
	 */
	public Ottelu[] annaOttelut(String ottelunNimea) {
		return ottelut.anna(ottelunNimea);
	}

	
	/**
	 * Palauttaa ottelut jotka sis�lt�v�t pelaajia, jotka ovat olleet tietyss� roolissa ottelussa
	 * @param pelaajanNimea osa pelaajan nimest�
	 * @param rooli pelaajan rooli
	 * @return Palauttaa taulukon ottelusta, jotka t�ytt�v�t ehdot
	 */
	public Ottelu[] annaOttelut(String pelaajanNimea, int rooli) {
		int[] pelaajaNumerot = pelaajat.anna(pelaajanNimea);
		int[] otteluNumerot = otteluPelaajat.annaOtteluNumerot(pelaajaNumerot, rooli);
		return ottelut.anna(otteluNumerot);
	}
	
	
	/**
	 * kloonaa p�rssin
	 * @return palauttaa viitteen uuteen p�rssiin
	 */
	@Override
    public Pisteporssi clone() {
		Pisteporssi kopioPorssi = new Pisteporssi();
		for(int i=0; i<getPelaajia(); i++)
			kopioPorssi.lisaa(annaPelaaja(i));
		for(int i=0; i<getOtteluja(); i++)
			kopioPorssi.lisaa(annaOttelu(i));
		for(int i=0; i<getOtteluPelaajia(); i++)
			kopioPorssi.lisaa(annaOtteluPelaaja(i));
		return kopioPorssi;
	}
	
	
	/**
	 * Kopioi p�rssiin toisen p�rssin arvot
	 * @param kopioPorssi p�rssi jonka arvot kopioidaan
	 */
	public void kopioi(Pisteporssi kopioPorssi) {
		pelaajat = new Pelaajat();
		ottelut = new Ottelut();
		otteluPelaajat = new OtteluPelaajat();
		for(int i=0; i<kopioPorssi.getPelaajia(); i++)
			lisaa(kopioPorssi.annaPelaaja(i));
		for(int i=0; i<kopioPorssi.getOtteluja(); i++)
			lisaa(kopioPorssi.annaOttelu(i));
		for(int i=0; i<kopioPorssi.getOtteluPelaajia(); i++)
			lisaa(kopioPorssi.annaOtteluPelaaja(i));
	}
	
	
	/**
	 * Kokeillaan pisteporssin toimintaa
	 * @param args ei k�yt�ss�
	 */
	public static void main(String[] args) {
		Pisteporssi pisteporssi = new Pisteporssi();

		Pelaaja pelaaja1 = new Pelaaja();
		Pelaaja pelaaja2 = new Pelaaja();
		Pelaaja pelaaja3 = new Pelaaja();

		pelaaja1.rekisteroi();
		pelaaja2.rekisteroi();
		pelaaja3.rekisteroi();

	
		pisteporssi.lisaa(pelaaja1);
		pisteporssi.lisaa(pelaaja2);
		pisteporssi.lisaa(pelaaja3);
		
		
		for (int i=0; i < pisteporssi.getPelaajia(); i++) {
			Pelaaja pelaaja = pisteporssi.annaPelaaja(i);
			System.out.println("Pelaaja nro: " + i);
			pelaaja.tulosta(System.out);
		}
		
	}

}
