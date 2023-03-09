package porssi;

import java.io.PrintStream;

/**
 * Pistepörssi ottelu
 * @author Esa Hyyryläinen
 * @version 10.7.2015
 *
 */
public class Ottelu {
	private int otteluNro;
	private String otteluNimi;
	
	private static int seuraavaNro = 1;
	
	
	/**
	 * Tulostetaan ottelun tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("%03d", otteluNro, 3) + " " + otteluNimi);
	}
	
	
	/**
	 * Antaa ottelulle seuraavan vapaan id-numeron
	 * @return ottelu uusi numero
	 * @example
	 * <pre name="test">
	 *	Ottelu ottelu1 = new Ottelu();
	 *	Ottelu ottelu2 = new Ottelu();
	 *	ottelu1.getOtteluNro() === 0;
	 *	ottelu2.getOtteluNro() === 0;
	 *	ottelu1.rekisteroi();
	 *	ottelu2.rekisteroi();
	 *	int n = ottelu1.getOtteluNro();
	 *	ottelu2.getOtteluNro() === n+1;
	 * </pre>
	 */
	public int rekisteroi() {
		otteluNro = seuraavaNro;
		seuraavaNro++;
		return otteluNro;
	}
	
	
	/**
	 * Palauttaa ottelun numeron.
	 * @return ottelun numero
	 */
	public int getOtteluNro() {
		return otteluNro;
	}
	
	
	/**
	 * Antaa ottelun nimen (Sama toiminto kuin toString())
	 * @return palauttaa ottelun nimen
	 */
	public String getNimi() {
		return otteluNimi;
	}
	
	
	@Override
	public String toString() {
		return otteluNimi;
	}
	
	
	/** 
	 * Kokoaa ottelun tiedot merkkijonosta, joka on muotoa otteluNro|otteluNimi
	 * @param rivi merkkijono muodossa otteluNro|otteluNimi josta ottelun tiedot parsitaan
	 */
	public void parse(String rivi) {
		int erotin1 =  rivi.indexOf('|', 0);
		otteluNro = Integer.parseInt(rivi.substring(0, erotin1));
		otteluNimi = rivi.substring(erotin1+1);
	}
	
	
	/**
	 * Asettaa ottelulle halutun nimen
	 * @param nimi ottelulle annettava nimi
	 */
	public void setNimi(String nimi) {
		otteluNimi=nimi;
	}
	
	/**
	 * Kokeillaan ottelun toimintaa
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		Ottelu ottelu1 = new Ottelu();
		Ottelu ottelu2 = new Ottelu();

		ottelu1.tulosta(System.out);
		ottelu2.tulosta(System.out);

		ottelu1.rekisteroi();
		ottelu2.rekisteroi();

		ottelu1.tulosta(System.out);
		ottelu2.tulosta(System.out);
	}
}
