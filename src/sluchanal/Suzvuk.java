package sluchanal;

import java.util.List;

/**
 * Rozhranie všetkých súzvukov, poskytujúce ich základnú funkcionalitu, pre všetky súzvuky spoločnú.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 * 
 */

public interface Suzvuk {
	
	/**
	 * Porovná súzvuk s iným objektom.
	 * 
	 * @param obj Objekt s ktorým porovnávame (spravidla tiež súzvuk).
	 * @return true ak sú súzvuky totožné (rovnakého typu aj štruktúry), false ak nie sú.
	 */
	
	public boolean equals(Object obj);
	
	/**
	 * Vráti počty poltónov súzvuku oddelené čiarkami.
	 * 
	 * @return Reťazec počtov poltónov.
	 */
	
	public String vratPocty();
	
	/**
	 * Vráti skratku súzvuku, z viacerých možností (z tabuľky, genericky, a iné) vyberie najpresnejšiu
	 * a z rôznych enharmonických variánt najbežnejšiu.
	 * 
	 * @return Skratka súzvuku, alebo null, ak nie je nájdená.
	 */
	
	public String vratSkratku();
	
	/**
	 * Vráti názov súzvuku, podľa najbežnejšej skratky súzvuku.
	 * 
	 * @return Názov súzvuku s diakritikou, alebo null, ak nie je nájdený.
	 */
	
	public String vratNazov();
	
	/**
	 * Vráti názov súzvuku, podľa danej skratky.
	 * 
	 * @param skratka Skratka súzvuku, ktorú chceme nazvať.
	 * @return Názov súzvuku s diakritikou, alebo null, ak nie je nájdený.
	 */
	
	public String vratNazov(String skratka);
	
	/**
	 * Vráti zoznam možných skratiek súzvuku, využijúc všetky varianty (tabuľku, generický názov a i.)
	 * a plne využijúc možnosti enharmonickej zámeny.
	 * 
	 * @return Zoznam reťazcov všetkých možných skratie súzvuku, alebo null, ak nie je nájdený.
	 */
	
	public List<String> vratEnh();
	
	/**
	 * Vráti zoznam možných názvov súzvuku, podľa zoznamu všetkých skratiek súzvuku.
	 * 
	 * @return Zoznam reťazcov všetkých možných názvov súzvuku, alebo null, ak nie je nájdený.
	 */
	
	public List<String> vratEnhNazvy();
	
	/**
	 * Vráti zvukový charakter súzvuku.
	 * 
	 * @return Reťazec vyjadrujúci charakter súzvuku, alebo null, ak nie je nájdený.
	 */
	
	public String vratCharakter();
	
	/**
	 * Urobí obrat súzvuku smerom nahor.
	 *  
	 * @return Obrat súzvuku smerom nahor.
	 */
	
	public Suzvuk urobObratDopredu();
	
	/**
	 * Urobí obrat súzvuku smerom nadol.
	 * 
	 * @return Obrat súzvuku smerom nadol.
	 */
	
	public Suzvuk urobObratDozadu();
}