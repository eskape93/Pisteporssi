package porssi;

import java.io.PrintStream;

/**
 * Pistepörssi Pelaaja
 * - tietää pelaajan nimen (ja pelipaikan)
 * - osaa tarkistaa tietyn kentän oikeellisuuden
 * - osaa muuttaa 1|Pelaaja 1|Hyökkääjä| pelaajan tiedoiksi
 * - osaa antaa merkkijonon i;n kentän tiedot
 * - osaa laittaa merkkijonon i:nkse kentäksi
 * @author Esa Hyyryläinen
 * @version 28.6.2015
 *
 */
public class Pelaaja {
	
	private int	tunnusNro = 0;
	private String	nimi = "";
	private String	pelipaikka = "";

	private static int seuraavaNro	= 1;
	
	
	/**
	 * Tulostetaan pelaajan tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("%03d", tunnusNro, 3) + " " + nimi + " " + pelipaikka);
	}


	/**
	 * Antaa pelaajalle seuraavan vapaan id-numeron
	 * @return pelaajan uusi numero
	 * @example
	 * <pre name="test">
	 *	Pelaaja pelaaja1 = new Pelaaja();
	 *	Pelaaja pelaaja2 = new Pelaaja();
	 *	pelaaja1.getTunnusNro() === 0;
	 *	pelaaja2.getTunnusNro() === 0;
	 *	pelaaja1.rekisteroi();
	 *	pelaaja2.rekisteroi();
	 *	int n = pelaaja1.getTunnusNro();
	 *	pelaaja2.getTunnusNro() === n+1;
	 * </pre>
	 */
	public int rekisteroi() {
		tunnusNro = seuraavaNro;
		seuraavaNro++;
		return tunnusNro;
	}


	/**
	 * Palauttaa pelaajan tunnusnumeron.
	 * @return pelaajan tunnusnumero
	 */
	public int getTunnusNro() {
		return tunnusNro;
	}
	
	
	/**
	 * Palauttaa pelaajan pelipaikan
	 * @return pelaajan pelipaikka
	 */
	public String getPelipaikka() {
		return pelipaikka;
	}
	
	
	/**
	 * Asettaa pelaajalle pelipaikan
	 * @param pelipaikka2 pelaajalle asetettava pelipaikka
	 */
	public void setPelipaikka(String pelipaikka2) {
		pelipaikka = pelipaikka2;
	}
	
	
	@Override
	public String toString() {
		return (nimi);
	}
	
	
	/** 
	 * Kokoaa pelaajan tiedot merkkijonosta, joka on muotoa id|nimi|pelipaikka
	 * @param rivi merkkijono muodossa id|nimi|pelipaikka, josta pelaajan tiedot parsitaan
	 */
	public void parse(String rivi) {
		int erotin1 =  rivi.indexOf('|', 0);
		int erotin2 = rivi.indexOf('|', erotin1+1);
		tunnusNro = Integer.parseInt(rivi.substring(0, erotin1));
		nimi = rivi.substring(erotin1+1, erotin2);
		pelipaikka = rivi.substring(erotin2+1);
	}

	
	/**
	 * Asettaa pelaajalle halutun nimen
	 * @param nimi pelaajalle annettava nimi
	 */
	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	
	/**
	 * Kokeillaan pelaajan toimintaa
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Pelaaja pelaaja1 = new Pelaaja();
		Pelaaja pelaaja2 = new Pelaaja();

		pelaaja1.tulosta(System.out);
		pelaaja2.tulosta(System.out);

		pelaaja1.rekisteroi();
		pelaaja2.rekisteroi();


		pelaaja1.tulosta(System.out);
		pelaaja2.tulosta(System.out);
	}
}