package sluchanal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Trieda spracuvávajúca teóriu trojzvukov. Reprezentuje jeden konkrétny trojzvuk a ponúka nástroje
 * na jeho analýzu (pomenovanie, porovnanie) a obraty.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *  
 */

public class Trojzvuk implements Suzvuk {

	// Výška spodného tónu v trojzvuku (60 = c').
	
	private int vyska;
	
	// Počty poltónov k jednotlivým tónom trojzvuku od basu.
	
	private int pocet1;
	private int pocet2;
	
	Trojzvuk(int pocet1, int pocet2) {
		this.pocet1 = pocet1;
		this.pocet2 = pocet2;
		this.vyska = 60; // prednastavena výška basu: c'
	}
	
	Trojzvuk(int vyska, int pocet1, int pocet2) {
		this.pocet1 = pocet1;
		this.pocet2 = pocet2;
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

	public Dvojzvuk vratDvojzvuk12() {
		return new Dvojzvuk(pocet2-pocet1);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Trojzvuk) {
			if ((this.pocet1 == ((Trojzvuk)obj).pocet1) && (this.pocet2 == ((Trojzvuk)obj).pocet2)) {
				return true;
			}
		}
		return false;
	}
	
	public String vratPocty() {
		return new Integer(getPocet1()).toString() + "," + new Integer(getPocet2()).toString();
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
			
		// skúsi vrátiť skratku ako skratky dvojzvukov, pokiaľ je null, pokračuje ďalej
		skratka = vratSkratkuDvojzvukov();
		if (skratka != null) {
			return skratka;
		}
		
		return null;
	}
	
	/**
	 * Vráti skratku trojzvuku využijúc tabuľku skratiek.
	 * 
	 * @return Skratka trojzvuku, alebo null, ak nie je nájdená.
	 */
	
	public String vratSkratkuZTabulky() {
		return SluchovaAnalyza.trojzvukTab.VratRetazec2podla2(new Integer(pocet1).toString(),new Integer(pocet2).toString());
	}
	
	/**
	 * Vráti zoznam skratiek trojzvuku využijúc tabuľku skratiek.
	 * 
	 * @return Zoznam reťazcov skratiek trojzvuku, alebo null, ak nie je nájdený.
	 */
	
	public List<String> vratSkratkuZTabulkyEnh() {
		return SluchovaAnalyza.trojzvukTab.VratZoznam2podla2(new Integer(pocet1).toString(),new Integer(pocet2).toString());
	}
	
	/**
	 * Vráti generickú skratku trojzvuku.
	 * 
	 * @return Generická skratka trojzvuku, alebo null, ak nie je nájdená.
	 */
	
	public String vratSkratkuGenericky() {
		// podľa poltónového rozsahu celého trojzvuku sa rozhodne, či bude trojzvuk brať
		// ako kvintakord (rozsah 6,7) alebo ako sextakord/kvartsextakord (rozsah 8-10), kde
		// sa následne podľa vnútorného intervalu rozhodne pre vhodnejší variant. 
		switch (pocet2) {
			case 6: 
			case 7: return vratSkratkuGenerickyKvintakord();
			case 8:
			case 9:
			case 10:
				switch (pocet1) {
					case 2:
					case 3: 
					case 4: return vratSkratkuGenerickySextakord();
					case 5: 
					case 6: return vratSkratkuGenerickyKvartsextakord();
				}
		}
		return null;
	}
	
	/**
	 * Vráti zoznam možných generických skratiek trojzvuku.
	 * 
	 * @return Zoznam reťazcov generických skratiek trojzvuku, alebo null, ak je prázdny.
	 */
	
	public List<String> vratSkratkuGenerickyEnh() {
		// do zoznamu pridáva všetky možné skratky trojzvuku. Akonáhle je možné trojzvuk
		// považovať za kvintakord (rozsah 6-8), pridá príslušnú skratku, od rozsahu 7 pridáva
		// aj skratky pre sextakord a kvartsextakord.
		List<String> skratky = new ArrayList<String>();
		switch (pocet2) {
			case 6: skratky.add(vratSkratkuGenerickyKvintakord()); break;
			case 7: 
				skratky.add(vratSkratkuGenerickyKvintakord());
				switch (pocet1) {
					case 2:
					case 3: skratky.add(vratSkratkuGenerickySextakord()); break;
					case 4:
						skratky.add(vratSkratkuGenerickySextakord());
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						break;
					case 5:
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						skratky.add(vratSkratkuGenerickySextakord());
						break;
					case 6: skratky.add(vratSkratkuGenerickyKvartsextakord());			
				}
				break;
			case 8:
				switch (pocet1) {
					case 2:
					case 3: skratky.add(vratSkratkuGenerickySextakord()); break;
					case 4:
						skratky.add(vratSkratkuGenerickySextakord());
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						break;
					case 5:
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						skratky.add(vratSkratkuGenerickySextakord());
						break;
					case 6: skratky.add(vratSkratkuGenerickyKvartsextakord());			
				}
				skratky.add(vratSkratkuGenerickyKvintakord());
				break;
			case 9:
			case 10:
				switch (pocet1) {
					case 2:
					case 3: skratky.add(vratSkratkuGenerickySextakord()); break;
					case 4:
						skratky.add(vratSkratkuGenerickySextakord());
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						break;
					case 5:
						skratky.add(vratSkratkuGenerickyKvartsextakord());
						skratky.add(vratSkratkuGenerickySextakord());
						break;
					case 6: skratky.add(vratSkratkuGenerickyKvartsextakord());			
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
	 * Vráti generickú skratku za predpokladu, že sa jedná o kvintakord.
	 * 
	 * @return Generická skratka kvintakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickyKvintakord() {
		List<String> tercie = vratDvojzvuk1().vratEnh();
		List<String> kvinty = vratDvojzvuk2().vratEnh();
		if ((tercie == null) || (kvinty == null) || (tercie.size() == 0) || (kvinty.size() == 0)) {
			return null;
		}
		String tercia="",kvinta = "";
		// z možných skratiek pre prvý interval nájde tú, ktorá je terciou.
		for (String t : tercie) {
			if (t.charAt(t.length()-1) == '3')
				tercia = t.substring(0, t.length()-1);
		}
		// z možných skratiek pre druhý interval nájde tú, ktorá je kvintou.
		for (String k : kvinty) {
			if (k.charAt(k.length()-1) == '5')
				kvinta = k.substring(0, k.length()-1);
		}
		// "v" = veľká (tercia) zmení na "d" = durovo(-nejaký kvintakord)
		if (tercia.equals("v")) {
			tercia = "d";
		}
		if ((tercia.equals("")) || (kvinta.equals(""))) {
			return null;
		} else {
			return tercia + "-" + kvinta + "5";
		}
	}
	
	/**
	 * Vráti generickú skratku za predpokladu, že sa jedná o sextakord.
	 * 
	 * @return Generická skratka sextakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickySextakord() {
		// vyrobí obrat smerom nadol - kvintakord, a použije jeho skratku, len vymení "5" za "6"
		String kvintSkratka = urobObratDozadu().vratSkratkuGenerickyKvintakord();
		if (kvintSkratka != null) {
			return kvintSkratka.substring(0,kvintSkratka.length()-1) + "6";
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti generickú skratku za predpokladu, že sa jedná o kvartsextakord.
	 * 
	 * @return Generická skratka kvartsextakordu, alebo null, ak nie ja nájdená.
	 */
	
	public String vratSkratkuGenerickyKvartsextakord() {
		// vyrobí obrat smerom nahor - kvintakord, a použije jeho skratku, len vymení "5" za "46"
		String kvintSkratka = urobObratDopredu().vratSkratkuGenerickyKvintakord();
		if (kvintSkratka != null) {
			return kvintSkratka.substring(0,kvintSkratka.length()-1) + "46";
		} else {
			return null;
		}
	}
	
	/**
	 * Vráti skratku trojzvuku ako skratky dvoch dvojzvukov (intervaly od spodného hlasu).
	 * 
	 * @return Skratky dvoch dvojzvukov.
	 */
	
	public String vratSkratkuDvojzvukov() {
		String dvojzvuk1 = vratDvojzvuk1().vratSkratku();
		String dvojzvuk2 = vratDvojzvuk2().vratSkratku();
		if ((dvojzvuk1 == null) || (dvojzvuk2 == null)) {
			return null;
		} else {
			return dvojzvuk1 + "," + dvojzvuk2;
		}
	}
	
	/**
	 * Vráti skratky trojzvuku ako všetky možné skratky dvoch dvojzvukov v dvoch verziách
	 * (intervaly od spodného hlasu a vzájomné intervaly) využijúc enharmonickú zámenu.
	 * 
	 * @return Zoznam reťazcov skratiek dvoch dvojzvukov.
	 */
	
	public List<String> vratSkratkuDvojzvukovEnh() {
		List<String> skratky = new ArrayList<String>();
		// pre každú možnosť prvej skratky pridá každú možnosť druhej skratky.
		List<String> vratEnh1 = vratDvojzvuk1().vratEnh();
		List<String> vratEnh2 = vratDvojzvuk2().vratEnh();
		List<String> vratEnh12 = vratDvojzvuk12().vratEnh();
		if ((vratEnh1 == null) || (vratEnh2 == null) || (vratEnh12 == null)) {
			return null;
		}
		for (String s1 : vratEnh1) {
			for (String s2 : vratEnh2) {
				skratky.add(s1+","+s2);
			}
		}
		// to isté v "-" variante (intervaly medzi tónmi)
		for (String s1 : vratEnh1) {
			for (String s2 : vratEnh12) {
				skratky.add(s1+"-"+s2);
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
		String typ = "",akord = "",typNazov = "",akordNazov = "";
		
		if (skratka.contains("-")) {
			// skratka obsahuje spojovník "-" => rozdelenie na časti podľa spojovníka
			String[] casti = skratka.split("-");
			if (Character.isDigit(casti[0].charAt(casti[0].length()-1))) {
				// ide o skupinu dvojzvukov
				return vratDvojzvuk1().vratNazov(casti[0]) + "-" + vratDvojzvuk12().vratNazov(casti[1]);
			} else {
				// ide o generickú skratku
				int i = casti[1].length()-1;
				while (Character.isDigit(casti[1].charAt(i))) {
					i=i-1;
				}
				akord = casti[1].substring(i+1);  // oddelí v druhej časti skratky písmená od čísel 
				// písmenové časti nazve akoby to boli dvojzvuky, len s malými korektúrami
				typNazov = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[0]).replace('á','o') + "-" + SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(casti[1].substring(0,i+1)).replace('á','ý');
				if (typNazov == null) {
					return null;
				}
				typNazov = typNazov.replace("malo", "molovo");
				// číselnú časť nazve podľa tabuľky
				akordNazov = SluchovaAnalyza.trojzvukNazovTab.VratRetazec2(akord);
				if (akordNazov == null) {
					return null;
				}
				return typNazov + " " + akordNazov;
			}
		} else
		if (skratka.contains(",")) {
			// skratka obsahuje čiarku "," => rozdelenie na časti podľa čiarky
			// ide o skupinu dvojzvukov
			String[] casti = skratka.split(",");
			String dvojzvuk1 = vratDvojzvuk1().vratNazov(casti[0]);
			String dvojzvuk2 = vratDvojzvuk2().vratNazov(casti[1]);
			if ((dvojzvuk1 == null) || (dvojzvuk2 == null)) {
				return null;
			}
			return dvojzvuk1 + "," + dvojzvuk2;
		} else {
			// skratka neobsahuje spojovník "-" ani čiarku ","
			// ide o trojzvuk z tabulky
			int i = skratka.length()-1;
			while (Character.isDigit(skratka.charAt(i))) {
				i=i-1;
			}
			typ = skratka.substring(0,i+1); // oddelí v skratke písmená od čísel 
			akord = skratka.substring(i+1,skratka.length());
			// každú časť zvlášť nazve pomocou tabuľky
			typNazov = SluchovaAnalyza.trojzvukNazovTab.VratRetazec2(typ);
			akordNazov = SluchovaAnalyza.trojzvukNazovTab.VratRetazec2(akord);
			if ((typNazov == null) || (akordNazov == null)) {
				return null;
			} else {
				return typNazov + " " + akordNazov;
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
		for (String enh : vratEnh) {
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
		return SluchovaAnalyza.trojzvukCharakter.VratRetazec2podla2(new Integer(pocet1).toString(),new Integer(pocet2).toString());
	}
	
	public Trojzvuk urobObratDopredu() {
		// vytvorí obrat smerom nahor využijúc to, ako sa pri obrate menia intervaly
		return new Trojzvuk(pocet2-pocet1,pocet2-pocet1+(12-pocet2));
	}
	
	public Trojzvuk urobObratDozadu() {
		// vytvorí obrat smerom nadol využijúc to, ako sa pri obrate menia intervaly
		return new Trojzvuk((12-pocet2),pocet1+(12-pocet2));
	}
}