package sluchanal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Trieda spracuvávajúca teóriu štvorzvukov. Reprezentuje jeden konkrétny štvorzvuk a ponúka nástroje
 * na jeho analýzu (pomenovanie, porovnanie) a obraty.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *  
 */

public class Stvorzvuk implements Suzvuk {


	// Výška spodného tónu v štvorzvuku (60 = c').
	
	private int vyska;
	
	// Počty poltónov k jednotlivým tónom štvorzvuku od basu.
	
	private int pocet1;
	private int pocet2;
	private int pocet3;
	
	Stvorzvuk(int pocet1, int pocet2, int pocet3) {
		this.pocet1 = pocet1;
		this.pocet2 = pocet2;
		this.pocet3 = pocet3;
		this.vyska = 60; // prednastavená výška basu: c'
	}
	
	Stvorzvuk(int vyska, int pocet1, int pocet2, int pocet3) {
		this.pocet1 = pocet1;
		this.pocet2 = pocet2;
		this.pocet3 = pocet3;
		this.vyska = vyska;
	}
	
	public int getPocet1() {
		return pocet1;
	}
	
	public void setPocet1(int pocet1) {
		this.pocet1 = pocet1;
	}
	
	public int getPocet2() {
		return pocet2;
	}
	
	public void setPocet2(int pocet2) {
		this.pocet2 = pocet2;
	}
	
	public int getPocet3() {
		return pocet3;
	}
	
	public void setPocet3(int pocet3) {
		this.pocet3 = pocet3;
	}
	
	public int getVyska() {
		return vyska;
	}
	
	public void setVyska(int vyska) {
		this.vyska = vyska;
	}
	
	public Dvojzvuk vratDvojzvuk1() {
		return new Dvojzvuk(pocet1);
	}
	
	public Dvojzvuk vratDvojzvuk2() {
		return new Dvojzvuk(pocet2);
	}

	public Dvojzvuk vratDvojzvuk3() {
		return new Dvojzvuk(pocet3);
	}
	
	public Dvojzvuk vratDvojzvuk12() {
		return new Dvojzvuk(pocet2-pocet1);
	}
	
	public Dvojzvuk vratDvojzvuk13() {
		return new Dvojzvuk(pocet3-pocet1);
	}
	
	public Dvojzvuk vratDvojzvuk23() {
		return new Dvojzvuk(pocet3-pocet2);
	}
	
	public Trojzvuk vratTrojzvuk123() {
		// vráti trojzvuk z prvých troch tónov štvorzvuku
		return new Trojzvuk(pocet1,pocet2);
	}
	
	public Trojzvuk vratTrojzvuk234() {
		// vráti trojzvuk z posledných troch tónov štvorzvuku
		return new Trojzvuk(pocet2-pocet1,pocet3-pocet1);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Stvorzvuk) {
			if ((this.pocet1 == ((Stvorzvuk)obj).pocet1) && (this.pocet2 == ((Stvorzvuk)obj).pocet2) && (this.pocet3 == ((Stvorzvuk)obj).pocet3)) {
				return true;
			}
		}
		return false;
	}
	
	public String vratPocty() {
		return new Integer(getPocet1()).toString() + "," + new Integer(getPocet2()).toString() + "," + new Integer(getPocet3()).toString();
	}
	
	public String vratSkratku() {
		String skratka = null;
		
		// skúsi vrátiť skratku z tabuľky, pokiaľ je null, pokračuje ďalej
		skratka = vratSkratkuZTabulky();
		if (skratka != null) {
			return skratka;
		}
		
		// skúsi vrátiť generickú skratku, pokiaľ je null, pokračuje ďalej
		skratka = vratSkratkuGenericky();
		if (skratka != null) {
			return skratka;
		}
		
		// skúsi vrátiť skratku ako skratky dvojzvuku a trojzvuku, pokiaľ je null, pokračuje ďalej
		skratka = vratSkratkuDvojzvukTrojzvuk();
		if (skratka != null) {
			return skratka;
		}
		
		// skúsi vrátiť skratku ako skratky dvojzvukov, pokiaľ je null, pokračuje ďalej
		skratka = vratSkratkuDvojzvukov();
		if (skratka != null) {
			return skratka;
		}

		return null;
	}
	
	/**
	 * Vráti skratku štvorzvuku využijúc tabuľku skratiek.
	 * 
	 * @return Skratka štvorzvuku, alebo null, ak nie je nájdená.
	 */
	
	public String vratSkratkuZTabulky() {
		return SluchovaAnalyza.stvorzvukTab.VratRetazec2podla3(new Integer(pocet1).toString(),new Integer(pocet2).toString(),new Integer(pocet3).toString());
	}
	
	/**
	 * Vráti zoznam možných skratiek štvorzvuku využijúc tabuľku skratiek.
	 * 
	 * @return Zoznam skratiek štvorzvuku, alebo null, ak nie je nájdený.
	 */
	
	public List<String> vratSkratkuZTabulkyEnh() {
		return SluchovaAnalyza.stvorzvukTab.VratZoznam2podla3(new Integer(pocet1).toString(),new Integer(pocet2).toString(),new Integer(pocet3).toString());
	}
	
	/**
	 * Vráti generickú skratku štvorzvuku.
	 * 
	 * @return Generická skratka štvorzvuku, alebo null, ak nie je nájdená.
	 */
	
	public String vratSkratkuGenericky() {
		// podľa poltónového rozsahu celého štvorzvuku sa rozhodne, či bude trojzvuk brať
		// ako obrat septakordu (rozsah 7-9), v tom prípade hľadá, medzi ktorými hlasmi je 
		// sekunda a podľa toho rozhodne o ktorý obrat ide (keď nenájde: zmenšeno-zmenšený
		// septakord). Väčší rozsah (10-11) bude chápať ako septakord.
		switch (pocet3) {
			case 7: 
			case 8:
			case 9: 
				// obrat: zisti, kde je sekunda
				if (pocet1 < 3) {
					return vratSkratkuGenerickySekundakord();
				} else if ((pocet2 - pocet1) < 3) {
					return vratSkratkuGenerickyTerckvartakord();
				} else if ((pocet3 - pocet2) < 3) {
					return vratSkratkuGenerickyKvintsextakord();
				} else {
					// sekunda nebola nájdená: zmenšeno-zmenšený septakord
					return vratSkratkuGenerickySeptakord();
				}
			case 10:
			case 11:
				// septakord
				return vratSkratkuGenerickySeptakord();
		}
		return null;
	}
	
	/**
	 * Vráti zoznam možných generických skratiek štvorzvuku.
	 * 
	 * @return Zoznam reťazcov generických skratiek štvorzvuku, alebo null, ak je prázdny.
	 */
	
	public List<String> vratSkratkuGenerickyEnh() {
		// do zoznamu pridáva všetky možné skratky štvorzvuku. Akonáhle je možné štvorzvuk
		// považovať za obrat septakordu (rozsah 7-10), pridá príslušnú skratku, od rozsahu
		// 8 pridáva aj skratku pre septakord. V prípade obratu hľadá medzi ktorými hlasmi je 
		// sekunda a podľa toho rozhodne o ktorý obrat ide (má tu ale zjemnené kritériá).
		List<String> skratky = new ArrayList<String>();
		List<String> pridaj = new ArrayList<String>();
		switch (pocet3) {
			case 7: 
				if (pocet1 < 4) {
					pridaj = vratSkratkuGenerickySekundakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet2 - pocet1) < 4) {
					pridaj = vratSkratkuGenerickyTerckvartakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet3 - pocet2) < 4) {
					pridaj = vratSkratkuGenerickyKvintsextakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				break;
			case 8: 
			case 9:
				if (pocet1 < 4) {
					pridaj = vratSkratkuGenerickySekundakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet2 - pocet1) < 4) {
					pridaj = vratSkratkuGenerickyTerckvartakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet3 - pocet2) < 4) {
					pridaj = vratSkratkuGenerickyKvintsextakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				pridaj = vratSkratkuGenerickySeptakordEnh();
				if (pridaj != null) {
					skratky.addAll(pridaj);
				}
				break;
			case 10:
				pridaj = vratSkratkuGenerickySeptakordEnh();
				if (pridaj != null) {
					skratky.addAll(pridaj);
				}
				if (pocet1 < 4) {
					pridaj = vratSkratkuGenerickySekundakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet2 - pocet1) < 4) {
					pridaj = vratSkratkuGenerickyTerckvartakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				if ((pocet3 - pocet2) < 4) {
					pridaj = vratSkratkuGenerickyKvintsextakordEnh();
					if (pridaj != null) {
						skratky.addAll(pridaj);
					}
				}
				break;
			case 11:
				pridaj = vratSkratkuGenerickySeptakordEnh();
				if (pridaj != null) {
					skratky.addAll(pridaj);
				}
				break;
		}
		// vyhodenie null položiek zo zoznamu
		for (Iterator<String> it = skratky.iterator(); it.hasNext();) {
			if (it.next() == null) {
				it.remove();
			}
		}
		if (skratky.size() == 0) {
			return null;
		} else {
			return skratky;
		}
	}
	
	/**
	 * Vráti najvhodnejšiu generickú skratku za predpokladu, že sa jedná o septakord.
	 * 
	 * @return Generická skratka septakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickySeptakord() {
		List<String> septimy = vratDvojzvuk3().vratEnh();
		if (septimy == null) {
			return null;
		}
		String septima = "",kvintakord = "";
		// z možných skratiek pre rozsah nájde tú, ktorá je septimou
		for (String t : septimy) {
			if (t.charAt(t.length()-1) == '7')
				septima = t.substring(0, t.length()-1);
		}
		// spodné tri tóny nazve ako kvintakord, najprv sa pokúsi o tabuľkový názov, potom o generický
		// na konci názvu vymaže "5", pridá septimu a značku septakordu "7" a názov je hotový
		kvintakord = vratTrojzvuk123().vratSkratkuZTabulky();
		if (kvintakord == null) {
			kvintakord = vratTrojzvuk123().vratSkratkuGenericky();
		}
		if ((kvintakord == null) || (septima.equals(""))) {
			return null;
		} else {
			kvintakord = kvintakord.substring(0, kvintakord.length()-1);
			return kvintakord + "-" + septima + "7";
		}
	}
	
	/**
	 * Vráti všetky možné generické skratky za predpokladu, že sa jedná o septakord.
	 * 
	 * @return Zoznam generických skratiek septakordu, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuGenerickySeptakordEnh() {
		List<String> skratky = new ArrayList<String>();
		
		List<String> septimy = vratDvojzvuk3().vratEnh();
		if (septimy == null) {
			return null;
		}
		String septima = "",kvintakord = "";
		// z možných skratiek pre rozsah nájde tú, ktorá je septimou
		for (String t : septimy) {
			if (t.charAt(t.length()-1) == '7')
				septima = t.substring(0, t.length()-1);
		}
		// spodné tri tóny nazve genericky ako kvintakord, najprv pridá tabuľkový názov, potom o generický
		// na konci názvu vymaže "5", pridá septimu a značku septakordu "7" a názvy sú hotové
		kvintakord = vratTrojzvuk123().vratSkratkuZTabulky();
		if (kvintakord != null && (septima.equals("") == false)) {
			kvintakord = kvintakord.substring(0, kvintakord.length()-1);
			skratky.add(kvintakord + "-" + septima + "7");
		}
		kvintakord = vratTrojzvuk123().vratSkratkuGenericky();
		if (kvintakord != null && (septima.equals("") == false)) {
			kvintakord = kvintakord.substring(0, kvintakord.length()-1);
			skratky.add(kvintakord + "-" + septima + "7");
		}
		if (skratky.size() == 0) {
			return null;
		} else {
			return skratky;
		}
	}
	
	/**
	 * Vráti najvhodnejšiu generickú skratku za predpokladu, že sa jedná o kvintsextakord.
	 * 
	 * @return Generická skratka kvintsextakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickyKvintsextakord() {
		// vyrobí obrat smerom nadol - septakord, a použije jeho skratku, len vymení "7" za "56"
		String septSkratka = urobObratDozadu().vratSkratkuGenerickySeptakord();
		if (septSkratka != null) {
			return septSkratka.substring(0,septSkratka.length()-1) + "56";
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti zoznam generických skratiek za predpokladu, že sa jedná o kvintsextakord.
	 * 
	 * @return Zoznam Generických skratiek kvintsextakordu, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuGenerickyKvintsextakordEnh() {
		// vyrobí obrat smerom nadol - septakord, a použije jeho enharmonické skratky, len vymení "7" za "56"
		List<String> septSkratky = urobObratDozadu().vratSkratkuGenerickySeptakordEnh();
		if (septSkratky != null) {
			for (int i = 0; i < septSkratky.size(); i++) {
				septSkratky.set(i, septSkratky.get(i).substring(0,septSkratky.get(i).length()-1) + "56");
			}
			return septSkratky;
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti najvhodnejšiu generickú skratku za predpokladu, že sa jedná o terckvartakord.
	 * 
	 * @return Generická skratka terckvartakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickyTerckvartakord() {
		// vyrobí dvojnásobný obrat - septakord, a použije jeho skratku, len vymení "7" za "34"
		String septSkratka = urobObratDozadu().urobObratDozadu().vratSkratkuGenerickySeptakord();
		if (septSkratka != null) {
			return septSkratka.substring(0,septSkratka.length()-1) + "34";
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti zoznam generických skratiek za predpokladu, že sa jedná o terckvartakord.
	 * 
	 * @return Zoznam Generických skratiek terckvartakordu, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuGenerickyTerckvartakordEnh() {
		// vyrobí dvojnásobný obrat - septakord, a použije jeho enharmonické skratky, len vymení "7" za "34"
		List<String> septSkratky = urobObratDozadu().urobObratDozadu().vratSkratkuGenerickySeptakordEnh();
		if (septSkratky != null) {
			for (int i = 0; i < septSkratky.size(); i++) {
				septSkratky.set(i, septSkratky.get(i).substring(0,septSkratky.get(i).length()-1) + "34");
			}
			return septSkratky;
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti najvhodnejšiu generickú skratku za predpokladu, že sa jedná o sekundakord.
	 * 
	 * @return Generická skratka sekundakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickySekundakord() {
		// vyrobí obrat smerom nahor - septakord, a použije jeho skratku, len vymení "7" za "2"
		String septSkratka = urobObratDopredu().vratSkratkuGenerickySeptakord();
		if (septSkratka != null) {
			return septSkratka.substring(0,septSkratka.length()-1) + "2";
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti zoznam generických skratiek za predpokladu, že sa jedná o sekundakord.
	 * 
	 * @return Zoznam Generických skratiek sekundakordu, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuGenerickySekundakordEnh() {
		// vyrobí obrat smerom nahor - septakord, a použije jeho enharmonické skratky, len vymení "7" za "2"
		List<String> septSkratky = urobObratDopredu().vratSkratkuGenerickySeptakordEnh();
		if (septSkratky != null) {
			for (int i = 0; i < septSkratky.size(); i++) {
				septSkratky.set(i, septSkratky.get(i).substring(0,septSkratky.get(i).length()-1) + "2");
			}
			return septSkratky;
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti skratku štvorzvuku ako kombináciu skratiek známeho trojzvuku a intervalu.
	 * 
	 * @return Skratky známeho trojzvuku a intervalu, alebo null, ak nie sú nájdené.
	 */
	
	public String vratSkratkuDvojzvukTrojzvuk() {
		// vyskúša, či sa trojzvuk prvých troch tónov dá nazvať z tabuľky
		String trojzvuk = vratTrojzvuk123().vratSkratkuZTabulky();
		String dvojzvuk = vratDvojzvuk3().vratSkratku();
		if ((trojzvuk != null) && (dvojzvuk != null)) {
			// ak áno, vráti skratku ako skratky trojzvuku a dvojzvuku
			return trojzvuk + "," + dvojzvuk;
		} else { 
			// ak nie, vyskúša, či sa trojzvuk posledných troch tónov dá nazvať z tabuľky
			String trojzvuk2 = vratTrojzvuk234().vratSkratkuZTabulky();
			String dvojzvuk2 = vratDvojzvuk1().vratSkratku();
			if ((trojzvuk2 != null) && (dvojzvuk2 != null)) {
				// ak áno, vráti skratku ako skratky trojzvuku a dvojzvuku
				return dvojzvuk2 + "-" + trojzvuk2;
			} else {
				// ak nie, táto časť vráti null
				return null;
			}
		}
	}
	
	/**
	 * Vráti skratky štvorzvuku ako všetky možné kombinácie skratiek známeho trojzvuku a intervalu,
	 * využijúc enharmonickú zámenu.
	 * 
	 * @return Skratky známeho trojzvuku a intervalu, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuDvojzvukTrojzvukEnh() {
		List<String> skratky = new ArrayList<String>();
		List<String> trojzvuky123 = vratTrojzvuk123().vratSkratkuZTabulkyEnh();
		List<String> trojzvuky234 = vratTrojzvuk234().vratSkratkuZTabulkyEnh();
		List<String> dvojzvuky1 = vratDvojzvuk1().vratEnh();
		List<String> dvojzvuky23 = vratDvojzvuk23().vratEnh();
		List<String> dvojzvuky3 = vratDvojzvuk3().vratEnh();
		if (trojzvuky123 != null) {
			if (dvojzvuky3 != null) {
				// ak vie trojzvuk prvých troch tónov nazvať z tabuľky, tak pre všetky jeho skratky
				// pridá všetky možnosti skratky rozsahu (intervalu)
				for (String s1 : trojzvuky123) {
					for (String s2 : dvojzvuky3) {
						skratky.add(s1+","+s2);
					}
				}
			}
			if (dvojzvuky23 != null) {
				// to isté len posledný interval pridá ako interval medzi altom a sopránom
				for (String s1 : trojzvuky123) {
					for (String s2 : dvojzvuky23) {
						skratky.add(s1+"-"+s2);
					}
				}
			}
		}
		if (trojzvuky234 != null) {
			if (dvojzvuky1 != null) {
				// ak vie trojzvuk posledných troch tónov nazvať z tabuľky, tak pre všetky skratky
				// intervalu medzi basom a tenorom pridá všetky možnosti skratky trojzvuku
				for (String s1 : dvojzvuky1) {
					for (String s2 : trojzvuky234) {
						skratky.add(s1+"-"+s2);
					}
				}
			}
		}
		if (skratky.size() == 0) {
			return null;
		} else {
			return skratky;
		}
	}
	
	/**
	 * Vráti skratku štvorzvuku ako skratky troch dvojzvukov (intervalov od basu).
	 * 
	 * @return Skratky troch dvojzvukov (intervaly od basu), alebo null, ak nie sú nájdené.
	 */
	
	public String vratSkratkuDvojzvukov() {
		String dvojzvuk1 = vratDvojzvuk1().vratSkratku();
		String dvojzvuk2 = vratDvojzvuk2().vratSkratku();
		String dvojzvuk3 = vratDvojzvuk3().vratSkratku();
		if ((dvojzvuk1 != null) && (dvojzvuk2 != null) && (dvojzvuk3 != null)) {
			return dvojzvuk1 + ","+ dvojzvuk2 + "," + dvojzvuk3;
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti skratky štvorzvuku ako všetky možné kombinácie skratiek dvojzvukov v dvoch verziách
	 * (intervaly od basu a vzájomné intervaly).
	 * 
	 * @return Zoznam reťazcov skratiek troch dvojzvukov, alebo null, ak nie sú nájdené.
	 */
	
	public List<String> vratSkratkuDvojzvukovEnh() {
		List<String> skratky = new ArrayList<String>();
		List<String> dvojzvuky1 = vratDvojzvuk1().vratEnh();
		List<String> dvojzvuky2 = vratDvojzvuk2().vratEnh();
		List<String> dvojzvuky3 = vratDvojzvuk3().vratEnh();
		List<String> dvojzvuky12 = vratDvojzvuk12().vratEnh();
		List<String> dvojzvuky23 = vratDvojzvuk23().vratEnh();
		if ((dvojzvuky1 != null) && (dvojzvuky2 != null) && (dvojzvuky3 != null)) {
			// pre každú možnosť prvej skratky pridá každú možnosť druhej skratky a tretej skratky
			for (String s1 : dvojzvuky1) {
				for (String s2 : dvojzvuky2) {
					for (String s3 : dvojzvuky3) {
						skratky.add(s1+","+s2+","+s3);
					}
				}
			}
		}
		if ((dvojzvuky1 != null) && (dvojzvuky12 != null) && (dvojzvuky23 != null)) {
			// to isté v "-" variante (intervaly medzi tónmi)
			for (String s1 : dvojzvuky1) {
				for (String s2 : dvojzvuky12) {
					for (String s3 : dvojzvuky23) {
						skratky.add(s1+"-"+s2+"-"+s3);
					}
				}
			}
		}
		if (skratky.size() == 0) {
			return null;
		} else {
			return skratky;
		}
	}
	
	public String vratNazov() {
		return vratNazov(vratSkratku());
	}
	
	public String vratNazov(String skratka) {
		// podľa zloženia skratky musí zistiť, o aký typ skratky ide, a potom kúsok po kúsku
		// skratku parsuje a nazýva jednotlivé časti (využíva na to tabuľky).
		String typ = "",akord = "",typString = "",akordString = "";
		
		if (skratka.contains("-")) {
			// skratka obsahuje spojovník "-" => rozdelenie na časti podľa spojovníka
			String[] casti = skratka.split("-");
			if (Character.isDigit(casti[0].charAt(casti[0].length()-1))) {
				// ide o skupinu dvojzvukov alebo skupinu dvojzvuk-trojzvuk resp. opačne
				if (Character.isUpperCase(casti[0].charAt(0))) {
					// ide o skupinu trojzvuk-dvojzvuk
					String trojzvuk = vratTrojzvuk123().vratNazov(casti[0]); // nazve trojzvuk
					String dvojzvuk = vratDvojzvuk23().vratNazov(casti[1]); // nazve dvojzvuk
					if ((dvojzvuk == null) || (trojzvuk == null)) {
						return null;
					} else {
						return trojzvuk + "-" + dvojzvuk;
					}
				} else if (Character.isUpperCase(casti[1].charAt(0))) {
					// ide o skupinu dvojzvuk-trojzvuk
					String dvojzvuk = vratDvojzvuk1().vratNazov(casti[0]); // nazve dvojzvuk
					String trojzvuk = vratTrojzvuk234().vratNazov(casti[1]); // nazve trojzvuk
					if ((dvojzvuk == null) || (trojzvuk == null)) {
						return null;
					} else {
						return dvojzvuk + "-" + trojzvuk;
					}
				} else {
					// ide o skupinu dvojzvukov
					String dvojzvuk1 = vratDvojzvuk1().vratNazov(casti[0]);
					String dvojzvuk2 = vratDvojzvuk12().vratNazov(casti[1]);
					String dvojzvuk3 = vratDvojzvuk23().vratNazov(casti[2]);
					if ((dvojzvuk1 == null) || (dvojzvuk2 == null) || (dvojzvuk3 == null)) {
						return null;
					} else {
						return dvojzvuk1 + "-" + dvojzvuk2 + "-" + dvojzvuk3;
					}
				}
			} else {
				// ide o generickú skratku
				
				if (casti.length == 2) {
					// ide o generickú skratku s jedným spojovníkom "-" (KVINTAKORD-septima)
					
					int i = casti[1].length()-1;
					while (Character.isDigit(casti[1].charAt(i))) {
						i=i-1;
					}
					akord = casti[1].substring(i+1); // oddelí v druhej časti skratky písmená od čísel 
					// písmenové časti nazve akoby to bol trojzvuk a dvojzvuk, len s malými korektúrami
					typString = SluchovaAnalyza.trojzvukNazovTab.VratRetazec2(casti[0]).replace('ý','o') + "-" + SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[1].substring(0,i+1)).replace('á','ý');
					if (typString == null) {
						return null;
					}
					// číselnú časť nazve podľa tabuľky
					akordString = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec2(akord);
					if (akordString == null) {
						return null;
					}
					
				} else {
					// ide o generickú skratu s dvoma spojovníkmi "-" (tercia-kvinta-septima)
					
					int i = casti[2].length()-1;
					while (Character.isDigit(casti[2].charAt(i))) {
						i=i-1;
					}
					akord = casti[2].substring(i+1); // oddelí v tretej časti skratky písmená od čísel 
					// písmenové časti nazve akoby to boli dvojzvuky, len s malými korektúrami
					typString = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[0]).replace('á','o') + "-" + SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[1]).replace('á','o') + "-" + SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[2].substring(0,i+1)).replace('á','ý');
					if (typString == null) {
						return null;
					}
					typString = typString.replace("malo", "molovo");
					// číselnú časť nazve podľa tabuľky
					akordString = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec2(akord);
					if (akordString == null) {
						return null;
					}
					
				}
				
				return typString + " " + akordString;
			}
		} else
		if (skratka.contains(",")) {
			// skratka obsahuje čiarku "," => rozdelenie na časti podľa čiarky
			// ide o skupinu dvojzvukov alebo skupinu trojzvuk-dvojzvuk
			String[] casti = skratka.split(",");
			if (Character.isUpperCase(casti[0].charAt(0))) {
				// ide o skupinu trojzvuk,dvojzvuk
				String trojzvuk = vratTrojzvuk123().vratNazov(casti[0]); // nazve trojzvuk
				String dvojzvuk = vratDvojzvuk3().vratNazov(casti[1]); // nazve dvojzvuk
				if ((dvojzvuk == null) || (trojzvuk == null)) {
					return null;
				} else {
					return trojzvuk + "," + dvojzvuk;
				}
			} else {
				// ide o skupinu dvojzvukov
				String dvojzvuk1 = vratDvojzvuk1().vratNazov(casti[0]);
				String dvojzvuk2 = vratDvojzvuk2().vratNazov(casti[1]);
				String dvojzvuk3 = vratDvojzvuk3().vratNazov(casti[2]);
				if ((dvojzvuk1 == null) || (dvojzvuk2 == null) || (dvojzvuk3 == null)) {
					return null;
				} else {
					return dvojzvuk1 + "," + dvojzvuk2 + "," + dvojzvuk3;
				}
			}
		} else {
			// skratka neobsahuje spojovník "-" ani čiarku ","
			// ide o štvorzvuk z tabuľky
			int i = skratka.length()-1;
			while (Character.isDigit(skratka.charAt(i))) {
				i=i-1;
			}
			typ = skratka.substring(0,i+1); // oddelí v skratke písmená od čísel
			akord = skratka.substring(i+1);
			// každú časť zvlášť nazve pomocou tabuľky
			typString = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec2(typ);
			akordString = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec2(akord);
			if ((typString == null) || (akordString == null)) {
				return null;
			} else {
				return typString + " " + akordString;
			}
		}		
	}
	
	public List<String> vratEnh() {
		List<String> skratky = new ArrayList<String>();
		List<String> pridaj = new ArrayList<String>();
		
		// pridá do zoznamu všetky typy skratiek
		pridaj = vratSkratkuZTabulkyEnh();
		if (pridaj != null) {
			skratky.addAll(pridaj);
		}
		pridaj = vratSkratkuGenerickyEnh();
		if (pridaj != null) {
			skratky.addAll(pridaj);
		}
		pridaj = vratSkratkuDvojzvukTrojzvukEnh();
		if (pridaj != null) {
			skratky.addAll(pridaj);
		}
		pridaj = vratSkratkuDvojzvukovEnh();
		if (pridaj != null) {
			skratky.addAll(pridaj);
		}
		
		if (skratky.size() != 0) {
			return skratky;
		} else {
			return null;
		}
	}
	
	public List<String> vratEnhNazvy() {
		List<String> nazvy = new ArrayList<String>();
		// nazve každú enharmonickú skratku a pridá do zoznamu
		
		List<String> vratEnh = vratEnh();
		if (vratEnh == null) {
			return null;
		}
		for (String enh : vratEnh()) {
			nazvy.add(vratNazov(enh));
		}
		// vyhodenie null položiek zo zoznamu
		for (Iterator<String> it = nazvy.iterator(); it.hasNext();) {
			if (it.next() == null) {
				it.remove();
			}
		}
		if (nazvy.size() == 0) {
			return null;
		} else {
			return nazvy;
		}
	}
	
	public String vratCharakter() {
		return SluchovaAnalyza.stvorzvukCharakter.VratRetazec2podla3(new Integer(pocet1).toString(),new Integer(pocet2).toString(),new Integer(pocet3).toString());
	}

	public Stvorzvuk urobObratDopredu() {
		// vytvorí obrat smerom nahor využijúc to, ako sa pri obrate menia intervaly
		return new Stvorzvuk(pocet2-pocet1,pocet3-pocet1,(12-pocet1));
	}
	
	public Stvorzvuk urobObratDozadu() {
		// vytvorí obrat smerom nadol využijúc to, ako sa pri obrate menia intervaly
		return new Stvorzvuk((12-pocet3),(12-pocet3)+pocet1,(12-pocet3)+pocet2);
	}
	
}