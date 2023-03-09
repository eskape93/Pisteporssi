package porssi;

import java.io.PrintStream;

/**
 * Pistep�rssi OtteluPelaaja
 * - tiet�� lis�tietojaotteluista (pelanneet, j��hyll� jne.)
 * - osaa muuttaa merkkijonon 1|1|1|2 ottelun tiedoiksi   
 * - osaa tarkastaa tietyn kent�n oikeellisuuden
 * - osaa antaa merkkijonon i;n kent�n tiedot
 * - osaa laittaa merkkijonon i:nksi kent�ksi  
 * @author Esa Hyyryl�inen
 * @version 10.7.2015
 *
 */
public class OtteluPelaaja {
	private int otteluNro;
	private int pelaajaNro;
	private int rooli;	// 1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut
	private int montako;	
	
	
	/**
	 * Asettaa ottelutietoon pelaajan tunnistenumeron
	 * @param pelaajaNro2 tunnistenumero joka pelaajalla on
	 */
	public void setPelaajaNro(int pelaajaNro2) {
		pelaajaNro = pelaajaNro2;
	}
	
	
	/**
	 * Asettaa ottelutietoon ottelun tunnistenumeron
	 * @param otteluNro2 tunnistenumero joka ottelulla on
	 */
	public void setOtteluNro(int otteluNro2) {
		otteluNro = otteluNro2;
	}
	
	
	/**
	 * Asettaa pelaajalle ottelussa olleen roolin
	 * @param rooli2 on rooli joka pelaajalla ottelussa oli 1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut
	 */
	public void setRooli(int rooli2) {
		rooli = rooli2;
	}
	
	
	/**
	 * Asettaa monta kertaa kyseinen tapahtuma tapahtui
	 * @param monta kertojen m��r� jolloin kyseinen tapahtuma tapahtui
	 */
	public void setMontako(int monta) {
		montako = monta;
	}
	
	
	/**
	 * Palauttaa ottelun numeron
	 * @return ottelu numero
	 */
	public int getOtteluNro() {
		return otteluNro;
	}
	
	
	/**
	 * Palauttaa pelaajan tunnusnumeron
	 * @return peaalaja tunnusnumero
	 */
	public int getPelaajaNro() {
		return pelaajaNro;
	}
	
	
	/**
	 * Palauttaa pelaajan rooli ottelussa (1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut)
	 * @return pelaajan rooli ottelussa (1=maalintekij�, 2=sy�tt�j�, 3=j��hyll�2min, 4=j��hyll�5min, 5=pelannut)
	 */
	public int getRooli() {
		return rooli;
	}
	
	
	/**
	 * Palauttaa tehdyn asian m��r�n (monta maalia/sy�tt��/j��hy� jne.)
	 * @return tehdyn asian m��r� (monta maalia/sy�tt��/j��hy� jne.)
	 */
	public int getMontako() {
		return montako;
	}
	
	
	/**
	 * Tulostaa yhden otteluPelaajan tiedot
	 * @param out tietovirta johon tulostetaan
	 */
	public void tulosta(PrintStream out) {
		out.println(String.format("Ottelun nro: " + otteluNro + "Pelaajan nro: " + pelaajaNro + "pelaajan rooli: " + rooli + "monta: " + montako));
	}
		
	
	/** 
	 * Kokoaa otteluPelaajan tiedot merkkijonosta, joka on muotoa otteluid|pelaajaid|rooli|montako
	 * @param rivi merkkijono muodossa otteluid|pelaajaid|rooli|montako, josta otteluPelaajan tiedot parsitaan
	 */
	public void parse(String rivi) {
		int erotin1 =  rivi.indexOf('|', 0);
		int erotin2 = rivi.indexOf('|', erotin1+1);
		int erotin3 = rivi.indexOf('|', erotin2+1);
		otteluNro = Integer.parseInt(rivi.substring(0, erotin1));
		pelaajaNro = Integer.parseInt(rivi.substring(erotin1+1, erotin2));
		rooli = Integer.parseInt(rivi.substring(erotin2+1, erotin3));
		montako = Integer.parseInt(rivi.substring(erotin3+1));
	}
}
