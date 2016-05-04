/* ----------------------------------------- *
 *                                           *
 *                 Tabulka                   *
 *                                           *
 *   pomocné triedy tabuľkového charakteru   *
 *                                           *
 *              Ladislav Maršík              *
 *                                           *
 * ----------------------------------------- */

package sluchanal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trieda plniaca funkciu tabuľky, z ktorej vieme vybrať podľa číselného kľúča (číslo riadku)
 * aj podľa hodnoty - zoznamu reťazcov (na danom riadku). Vyberáme buď prvú položku zoznamu
 * reťazcov alebo celý zoznam.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 3.2010
 * 
 */

class Tabulka {
	
	private Map<Integer,List<String>> pole;
	
	Tabulka() {
		pole = new HashMap<Integer,List<String>>();
	}
	
	/**
	 * Pridá zoznam reťazcov do ďalšieho riadku tabuĺky.
	 * 
	 * @param hodnotyString Zoznam reťazcov oddelených čiarkou.
	 */
	
	void Pridaj(String hodnotyString) {
		String[] hodnoty = hodnotyString.split(",");
		List<String> polozka = new ArrayList<String>();
		for (int i = 0; i < hodnoty.length; i++) {
			polozka.add(hodnoty[i]);
		}
		pole.put(pole.size(),polozka);
	}
	
	/**
	 * Vráti prvú položku zoznamu reťazcov v danom riadku.
	 * 
	 * @param cislo Číslo riadku.
	 * @return Prvá položka zoznamu reťazcov v danom riadku, alebo null ak riadok nebol nájdený.
	 */
	
	String VratRetazec(Integer cislo) {
		if (pole.get(cislo).get(0) == null) {
			return null;
		} else {
			return pole.get(cislo).get(0);
		}
	}
	
	/**
	 * Vráti celý zoznam reťazcov v danom riadku.
	 * 
	 * @param cislo Číslo riadku.
	 * @return Zoznam reťazcov v danom riadku, alebo null ak riadok nebol nájdený.
	 */
	
	List<String> VratZoznam(Integer cislo) {
		if (pole.get(cislo) == null) {
			return null;
		} else {
			return pole.get(cislo);
		}
	}
	
	/**
	 * Vráti číslo riadku
	 * 
	 * @param retazec Prvá položka zoznamu reťazcov v hľadanom riadku.
	 * @return Číslo riadku, alebo null ak riadok nebol nájdený.
	 */
	
	Integer VratCislo(String retazec) {
		for (Integer i : pole.keySet()) {
			if (pole.get(i).contains(retazec)) {
				return i;
			}
		}
		return null;
	}
}

/**
 * Trieda plniaca funkciu dvojstĺpcovej tabuľky zoznamov reťazcov. Vieme vyberať podľa oboch
 * stĺpcov. Môžeme vyberať podľa prvej položky v zozname alebo podľa viacerých, a vyberáme buď
 * prvú položku alebo celý zoznam.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 3.2010
 * 
 */

class TabulkaRetazcov {
	
	private Map<List<String>,List<String>> pole;
	
	TabulkaRetazcov() {
		pole = new HashMap<List<String>,List<String>>();
	}
	
	/**
	 * Pridá zoznamy reťazcov do ďalšieho riadku tabuľky.
	 * 
	 * @param hodnotyString Zoznamy reťazcov oddelené ';', vrámci zoznamu sú reťazce oddelené ','
	 */
	
	void Pridaj(String hodnotyString) {
		String[] hodnoty = hodnotyString.split(";");
		String[] hodnoty1 = hodnoty[0].split(",");
		String[] hodnoty2 = hodnoty[1].split(",");
		List<String> polozka1 = new ArrayList<String>();
		for (int i = 0; i < hodnoty1.length; i++) {
			polozka1.add(hodnoty1[i]);
		}
		List<String> polozka2 = new ArrayList<String>();
		for (int i = 0; i < hodnoty2.length; i++) {
			polozka2.add(hodnoty2[i]);
		}
		pole.put(polozka1,polozka2);
	}

	/**
	 * Vráti prvú položku zoznamu v 2.stĺpci.
	 * 
	 * @param retazec1 Prvá položka zoznamu v 1.stĺpci.
	 * @return Prvá položka zoznamu v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	String VratRetazec2(String retazec1) {
		for (List<String> l : pole.keySet()) {
			if (l.contains(retazec1)) {
				return pole.get(l).get(0);
			}
		}
		return null;
	}
	
	/**
	 * Vráti prvú položku zoznamu v 1.stĺpci.
	 * 
	 * @param retazec2 Prvá položka zoznamu v 2.stĺpci.
	 * @return Prvá položka zoznamu v 1.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	String VratRetazec1(String retazec2) {
		for (List<String> l : pole.keySet()) {
			if (pole.get(l).contains(retazec2)) {
				return l.get(0);
			}
		}
		return null;
	}
	
	/**
	 * Vráti zoznam reťazcov v 2.stĺpci.
	 * 
	 * @param retazec1 Prvá položka zoznamu v 1.stĺpci.
	 * @return Zoznam reťazcov v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	List<String> VratZoznam2(String retazec1) {
		for (List<String> l : pole.keySet()) {
			if (l.contains(retazec1)) {
				return pole.get(l);
			}
		}
		return null;
	}
	
	/**
	 * Vráti zoznam reťazcov v 1.stĺpci.
	 * 
	 * @param retazec2 Prvá položka zoznamu v 2.stĺpci.
	 * @return Zoznam reťazcov v 1.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	List<String> VratZoznam1(String retazec2) {
		for (List<String> l : pole.keySet()) {
			if (pole.get(l).contains(retazec2)) {
				return l;
			}
		}
		return null;
	}
	
	/**
	 * Vráti prvú položku zoznamu v 2.stĺpci podľa prvých dvoch položiek zoznamu v 1.stĺpci.
	 * 
	 * @param cast1 Prvá položka zoznamu v 1.stĺpci.
	 * @param cast2 Druhá položka zoznamu v 1.stĺpci.
	 * @return Prvá položka zoznamu v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	String VratRetazec2podla2(String cast1, String cast2) {
		for (List<String> l : pole.keySet()) {
			try {
				if ((l.get(0).equals(cast1)) && (l.get(1).equals(cast2))) {
					return pole.get(l).get(0);
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Vráti prvú položku zoznamu v 2.stĺpci podľa prvých troch položiek zoznamu v 1.stĺpci.
	 * 
	 * @param cast1 Prvá položka zoznamu v 1.stĺpci.
	 * @param cast2 Druhá položka zoznamu v 1.stĺpci.
	 * @param cast2 Tretia položka zoznamu v 1.stĺpci.
	 * @return Prvá položka zoznamu v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	String VratRetazec2podla3(String cast1, String cast2, String cast3) {
		for (List<String> l : pole.keySet()) {
			try {
				if ((l.get(0).equals(cast1)) && (l.get(1).equals(cast2)) && (l.get(2).equals(cast3))) {
					return pole.get(l).get(0);
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Vráti zoznam reťazcov v 2.stĺpci podľa prvých dvoch položiek zoznamu v 1.stĺpci.
	 * 
	 * @param cast1 Prvá položka zoznamu v 1.stĺpci.
	 * @param cast2 Druhá položka zoznamu v 1.stĺpci.
	 * @return Zoznam reťazcov v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	List<String> VratZoznam2podla2(String cast1, String cast2) {
		for (List<String> l : pole.keySet()) {
			try {
				if ((l.get(0).equals(cast1)) && (l.get(1).equals(cast2))) {
					return pole.get(l);
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	/**
	 * Vráti zoznam reťazcov v 2.stĺpci podľa prvých troch položiek zoznamu v 1.stĺpci.
	 * 
	 * @param cast1 Prvá položka zoznamu v 1.stĺpci.
	 * @param cast2 Druhá položka zoznamu v 1.stĺpci.
	 * @param cast2 Tretia položka zoznamu v 1.stĺpci.
	 * @return Zoznam reťazcov v 2.stĺpci, alebo null ak riadok podľa vstupu nebol nájdený.
	 */
	
	List<String> VratZoznam2podla3(String cast1, String cast2, String cast3) {
		for (List<String> l : pole.keySet()) {
			try {
				if ((l.get(0).equals(cast1)) && (l.get(1).equals(cast2)) && (l.get(2).equals(cast3))) {
					return pole.get(l);
				}
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
}