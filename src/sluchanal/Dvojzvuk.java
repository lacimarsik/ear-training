package sluchanal;

import java.util.ArrayList;
import java.util.List;

/**
 * Trieda spracuvávajúca teóriu dvojzvukov (intervalov). Reprezentuje jeden konkrétny
 * interval a ponúka nástroje na jeho analýzu (pomenovanie, porovnanie) a obraty.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *  
 */

public class Dvojzvuk implements Suzvuk {
	
	// Výška spodného tónu v dvojzvuku (60 = c').
	
	private int vyska;
	
	// Počet poltónov medzi tónmi v dvojzvuku.

	private int pocet;
	
	Dvojzvuk(int pocet) {
		this.pocet = pocet;
		this.vyska = 60; // prednastavena výška spodného tónu: c'
	}
	
	Dvojzvuk(int vyska,int pocet) {
		this.pocet = pocet;
		this.vyska = vyska;
	}
	
	public int getPocet() {
		return pocet;
	}
	
	public void setPocet(int pocet) {
		this.pocet = pocet;
	}
	
	public int getVyska() {
		return vyska;
	}
	
	public void setVyska(int vyska) {
		this.vyska = vyska;
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Dvojzvuk) {
			if (this.pocet == ((Dvojzvuk)obj).pocet) {
				return true;
			}
		}
		return false;
	}
	
	public String vratPocty() {
		return new Integer(getPocet()).toString();
	}
	
	public String vratSkratku() {
		return SluchovaAnalyza.dvojzvukTab.VratRetazec(pocet);
	}
	
	public String vratNazov() {
		return vratNazov(vratSkratku());
	}
	
	public String vratNazov(String skratka) {
		String typ = skratka.substring(0,skratka.length()-1);
		String interval = skratka.substring(skratka.length()-1);

		String typNazov = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(typ);
		String intervalNazov = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(interval);		
		
		if ((typNazov == null) || (intervalNazov == null)) {
			return null;
		} else {
			return typNazov + " " + intervalNazov;
		}
	}
	
	public List<String> vratEnh() {
		return SluchovaAnalyza.dvojzvukTab.VratZoznam(pocet);
	}
	
	public List<String> vratEnhNazvy() {
		List<String> nazvy = new ArrayList<String>();
		String typ,interval,typNazov,intervalNazov;
		List<String> vratEnh = vratEnh();
		if (vratEnh == null) {
			return null;
		}
		for (String enh : vratEnh) {
			// nazýva všetky enharmonické skratky a pridá ich do zoznamu
			typ = enh.substring(0,enh.length()-1);
			interval = enh.substring(enh.length()-1);

			typNazov = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(typ);
			if (typNazov == null) {
				return null;
			}
			intervalNazov = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec2(interval);
			if (intervalNazov == null) {
				return null;
			}
			nazvy.add(typNazov + " " + intervalNazov);
		}
		if (nazvy != null) {
			// vyhodenie duplicitných názvov, vznikli kvôli duplicitným skratkám:
			// č1 a c1, č4 a c4, č5 a c5, č8 a c8
			if ((getPocet() == 0) || (getPocet() == 5) || (getPocet() == 7) || (getPocet() == 12)) {
				nazvy.remove(nazvy.size()-1);
			}
		}
		return nazvy;
	}
	
	public String vratCharakter() {
		return SluchovaAnalyza.dvojzvukCharakter.VratRetazec(pocet);
	}
	
	public Dvojzvuk urobObratDopredu() {
		// vytvorí obrat využijúc doplnok do oktávy
		return new Dvojzvuk(12-pocet);
	}
	
	public Dvojzvuk urobObratDozadu() {
		// vytvorí obrat využijúc doplnok do oktávy
		return new Dvojzvuk(12-pocet);
	}
}
