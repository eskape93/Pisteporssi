package porssi;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Tähän CRC-kortista kopio???
 * @author Esa Hyyryläinen
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
	 * @param pelaaja lisättävä uusi pelaaja
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
	 * Poistaa pelaajan ja siihen liittyvät ottelutiedot
	 * @param pelaajaNro sen pelaajan numero, joka poistetaan
	 */
	public void poistaPelaaja(int pelaajaNro) {
		pelaajat.poistaPelaaja(pelaajaNro);
		otteluPelaajat.poistaOtteluPelaajat(pelaajaNro);
	}
	
	
	/**
	 * Poistaa ottelun ja siihen liittyvät ottelutiedot
	 * @param otteluNro sen ottelun numero, joka poistetaan
	 */
	public void poistaOttelu(int otteluNro) {
		ottelut.poista(otteluNro);
		otteluPelaajat.poistaOtteluTiedot(otteluNro);
	}
	
	
	/**
	 * Poistaa pelaajan maalin/syötön/jne. ottelusta
	 * @param pelaajaNro sen pelaajan numero, jonka maali poistetaan
	 * @param otteluNro maali poistetaan tästä ottelusta
	 * @param rooli poistettavan rooli (1=maalintekijä, 2=syöttäjä, 3=jäähyllä2min, 4=jäähyllä5min, 5=pelannut)
	 */
	public void poistaYksittainen(int pelaajaNro, int otteluNro, int rooli) {
		otteluPelaajat.poistaYksittainen(pelaajaNro, otteluNro, rooli);
	}
	
	
	/**
	 * Lisää ottelun pörssiin
	 * @param ottelu lisättävä uusi ottelu
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
	 * @param otteluPelaaja lisättävä ottelu pelaaja
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
	 * @return pistepörssin pelaajamäärä
	 */
	public int getPelaajia() {
		return pelaajat.getLkm();
	}

	
	/**
	 * @return pistepörssin ottelumäärä
	 */
	public int getOtteluja() {
		return ottelut.getLkm();
	}
	
	
	/**
	 * @return ottelutietojen lukumäärä
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
	 * Antaa tietyn pelaajan jäähy minuutit
	 * @param tunnusNro pelaajan tunnusNro
	 * @return jäähyt minuuteissa
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
	 * Palauttaa pelaajien pelattujen ottelujen määrän
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan pelattujen otteluiden määrän
	 */
	public int annaPelaajanOttelujenMaara(int tunnusNro) {
		return otteluPelaajat.annaPelaajanOttelujenMaara(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajien tekemien maalien määrän
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan tekemät maalit
	 */
	public int annaPelaajanMaalit(int tunnusNro) {
		return otteluPelaajat.annaPelaajanMaalit(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajan syöttöjen määrän
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan syöttämät syötöt
	 */
	public int annaPelaajanSyotot(int tunnusNro) {
		return otteluPelaajat.annaPelaajanSyotot(tunnusNro);
	}
	
	
	/**
	 * Palauttaa pelaajan pisteiden määrän (maalit+syotot)
	 * @param tunnusNro pelaajan tunnusNro
	 * @return pelaajan pisteet=maalit+syotot
	 */
	public int annaPelaajanPisteet(int tunnusNro) {
		return otteluPelaajat.annaPelaajanPisteet(tunnusNro);
	}
	
	
	/**
	 * Tutkii onko etsityn kaltainen otteluPelaaja jo pörssissä
	 * @param otteluPelaaja tieto, jonka kaltaista etsitään ei huomioida montako kenttää
	 * @return palauttaa indeksin ottelupelaajaan jos löytyy, muuten -1
	 */
	public int loytyyko(OtteluPelaaja otteluPelaaja) {
		return otteluPelaajat.loytyyko(otteluPelaaja);
	}
	
	
	/**
	 * Antaa taulukon tietyssä ottelussa pelanneiden/maalin tehneistä/syöttäneistä pelaajista, riippuen roolista
	 * @param otteluNro ottelu jossa pelanneet pelaajat halutaan saada
	 * @param rooli mitä roolia haetaan (1=maalintekijä, 2=syöttäjä, 3=jäähyllä2min, 4=jäähyllä5min, 5=pelannut)
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
	 * Tallentaa pistepörssin tiedostoon
	 * @throws IOException jos tallennus epäonnistuu
	 */
	public void tallenna() throws IOException {
		//luo oma kansio syötetylle nimelle mihin tallennetaan
		pelaajat.tallenna();
		ottelut.tallenna();
		otteluPelaajat.tallenna();
	}
	
	
	/**
	 * Lukee pistepörssin tiedostosta
	 * @throws FileNotFoundException jos tiedostoa ei löydy
	 */
	public void lueTiedostosta() throws FileNotFoundException {
		pelaajat.lueTiedostosta();
		ottelut.lueTiedostosta();
		otteluPelaajat.lueTiedostosta();
	}
	
	
	/**
	 * Tutkii löytyykö tietyn nimistä pelaajaa pörssistä, jos löytyy palauttaa tämän numeron, muuten palauttaa -1
	 * @param nimi tämän nimistä pelaajaa etsitään
	 * @return palauttaa pelaajan tunnusnumeron, jos ei löydy palauttaa -1
	 */
	public int onkoTamanNimista(String nimi) {
		for(int i=0; i<getPelaajia();i++)
			if(nimi.equals(annaPelaaja(i).toString()))
				return annaPelaaja(i).getTunnusNro();
		return -1;
	}
	
	
	/**
	 * Tutkii löytyykö tietyn nimistä ottelua pörssistä, jos löytyy palauttaa tämän numeron, muuten palauttaa -1
	 * @param nimi tämän nimistä ottelua etsitään
	 * @return palauttaa ottelun tunnusnumeron, jos ei löydy palauttaa -1
	 */
	public int onkoTamanNimistaOttelua(String nimi) {
		for(int i=0; i<getOtteluja(); i++)
			if(nimi.equals(annaOttelu(i).toString()))
				return annaOttelu(i).getOtteluNro();
		return -1;
	}
	
	
	/**
	 * Antaa tietyn nimiehden täyttävät ottelut
	 * @param ottelunNimea nimi ehto jolla otteluja haetaan
	 * @return palauttaa annettuun nimeen täsmäävät ottelut
	 */
	public Ottelu[] annaOttelut(String ottelunNimea) {
		return ottelut.anna(ottelunNimea);
	}

	
	/**
	 * Palauttaa ottelut jotka sisältävät pelaajia, jotka ovat olleet tietyssä roolissa ottelussa
	 * @param pelaajanNimea osa pelaajan nimestä
	 * @param rooli pelaajan rooli
	 * @return Palauttaa taulukon ottelusta, jotka täyttävät ehdot
	 */
	public Ottelu[] annaOttelut(String pelaajanNimea, int rooli) {
		int[] pelaajaNumerot = pelaajat.anna(pelaajanNimea);
		int[] otteluNumerot = otteluPelaajat.annaOtteluNumerot(pelaajaNumerot, rooli);
		return ottelut.anna(otteluNumerot);
	}
	
	
	/**
	 * kloonaa pörssin
	 * @return palauttaa viitteen uuteen pörssiin
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
	 * Kopioi pörssiin toisen pörssin arvot
	 * @param kopioPorssi pörssi jonka arvot kopioidaan
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
	 * @param args ei käytössä
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
