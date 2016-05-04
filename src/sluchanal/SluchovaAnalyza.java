/* ----------------------------------------- *
 *                                           *
 *                SLUCHANAL                  *
 *                                           *
 *  aplikácia realizačnej bakalárskej práce  *
 *                                           *
 *              Ladislav Maršík              *
 *                                           *
 * Fakulta matematiky, fyziky a informatiky  *
 *                                           *
 *           Univerzita Komenského           *
 *                                           *                            
 *           vedúci: Martin Ilčík            *
 *                                           *
 *             3.2010 - 5.2010               *
 *                                           *
 *         Všetky práva vyhradené            *
 *                                           *
 * ----------------------------------------- */

package sluchanal;

import java.util.*;

/**
 * Zaobaľujúca trieda celého projektu SLUCHANAL
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *
 */

public class SluchovaAnalyza {

	/* statické polia údajov */
	
	public static Tabulka dvojzvukTab; // počet | zoznam skratiek 
	public static Tabulka dvojzvukCharakter; // počet | charakter
	public static TabulkaRetazcov dvojzvukNazovTab;	 // zoznam skratiek | zoznam názvov
	
	public static TabulkaRetazcov trojzvukTab; // dvojica počtov (ako zoznam Stringov) | zoznam skratiek
	public static TabulkaRetazcov trojzvukCharakter; // dvojica počtov (ako zoznam Stringov) | charakter
	public static TabulkaRetazcov trojzvukNazovTab; // zoznam skratiek | zoznam názvov
	
	public static TabulkaRetazcov stvorzvukTab; // trojica počtov (ako zoznam Stringov) | zoznam skratiek
	public static TabulkaRetazcov stvorzvukCharakter; // trojica počtov (ako zoznam Stringov) | charakter	
	public static TabulkaRetazcov stvorzvukNazovTab; // zoznam skratiek | zoznam názvov
	
	static {
		
		/* Inicializácie polí */
		
		dvojzvukTab = new Tabulka();
		dvojzvukTab.Pridaj("č1,zm2,c1");
		dvojzvukTab.Pridaj("m2,zv1,dzm3");
		dvojzvukTab.Pridaj("v2,dzv1,zm3");
		dvojzvukTab.Pridaj("m3,zv2,dzm4");
		dvojzvukTab.Pridaj("v3,dzv2,zm4");
		dvojzvukTab.Pridaj("č4,zv3,dzm5,c4");
		dvojzvukTab.Pridaj("zv4,zm5");
		dvojzvukTab.Pridaj("č5,dzv4,zm6,c5");
		dvojzvukTab.Pridaj("m6,zv5,dzm7");
		dvojzvukTab.Pridaj("v6,dzv5,zm7");
		dvojzvukTab.Pridaj("m7,zv6,dzm8");
		dvojzvukTab.Pridaj("v7,dzv6,zm8");
		dvojzvukTab.Pridaj("č8,zv7,c8");
		
		dvojzvukCharakter = new Tabulka();
		dvojzvukCharakter.Pridaj("prázdny");
		dvojzvukCharakter.Pridaj("ostro disonantný");
		dvojzvukCharakter.Pridaj("mierne disonantný");
		dvojzvukCharakter.Pridaj("konsonantný");
		dvojzvukCharakter.Pridaj("konsonantný");
		dvojzvukCharakter.Pridaj("prázdny");
		dvojzvukCharakter.Pridaj("disonantný");
		dvojzvukCharakter.Pridaj("prázdny");
		dvojzvukCharakter.Pridaj("konsonantný");
		dvojzvukCharakter.Pridaj("konsonantný");
		dvojzvukCharakter.Pridaj("mierne disonantný");
		dvojzvukCharakter.Pridaj("ostro disonantný");
		dvojzvukCharakter.Pridaj("prázdny");
		
		dvojzvukNazovTab = new TabulkaRetazcov();
		dvojzvukNazovTab.Pridaj("č,c;čistá,cista");
		dvojzvukNazovTab.Pridaj("m;malá,mala");
		dvojzvukNazovTab.Pridaj("v;veľká,velka");
		dvojzvukNazovTab.Pridaj("zv;zväčšená,zvacsena");
		dvojzvukNazovTab.Pridaj("zm;zmenšená,zmensena");
		dvojzvukNazovTab.Pridaj("dzv;dvojzväčšená,dvojzvacsena");
		dvojzvukNazovTab.Pridaj("dzm;dvojzmenšená,dvojzmensena");
		dvojzvukNazovTab.Pridaj("zv4,zm5;poloktáva,poloktava");
		dvojzvukNazovTab.Pridaj("d;durovo");
		dvojzvukNazovTab.Pridaj("1;príma,prima");
		dvojzvukNazovTab.Pridaj("2;sekunda");
		dvojzvukNazovTab.Pridaj("3;tercia");
		dvojzvukNazovTab.Pridaj("4;kvarta");
		dvojzvukNazovTab.Pridaj("5;kvinta");
		dvojzvukNazovTab.Pridaj("6;sexta");
		dvojzvukNazovTab.Pridaj("7;septima");
		dvojzvukNazovTab.Pridaj("8;oktáva,oktava");
		
		trojzvukTab = new TabulkaRetazcov();
		trojzvukTab.Pridaj("3,6;ZM5");
		trojzvukTab.Pridaj("3,7;MOL5");
		trojzvukTab.Pridaj("4,7;DUR5");
		trojzvukTab.Pridaj("4,8;ZV5,ZV6,ZV46");
		trojzvukTab.Pridaj("3,9;ZM6");
		trojzvukTab.Pridaj("4,9;MOL6");
		trojzvukTab.Pridaj("3,8;DUR6");
		trojzvukTab.Pridaj("6,9;ZM46");
		trojzvukTab.Pridaj("5,8;MOL46");
		trojzvukTab.Pridaj("5,9;DUR46");
		
		trojzvukCharakter = new TabulkaRetazcov();
		trojzvukCharakter.Pridaj("3,6;disonantný");
		trojzvukCharakter.Pridaj("3,7;konsonantný");
		trojzvukCharakter.Pridaj("4,7;konsonantný");
		trojzvukCharakter.Pridaj("4,8;disonantný");
		trojzvukCharakter.Pridaj("3,9;disonantný");
		trojzvukCharakter.Pridaj("4,9;konsonantný");
		trojzvukCharakter.Pridaj("3,8;konsonantný");
		trojzvukCharakter.Pridaj("6,9;disonantný");
		trojzvukCharakter.Pridaj("5,8;konsonantný");
		trojzvukCharakter.Pridaj("5,9;konsonantný");
		
		trojzvukNazovTab = new TabulkaRetazcov();
		trojzvukNazovTab.Pridaj("ZM;zmenšený,zmenseny");
		trojzvukNazovTab.Pridaj("ZV;zväčšený,zvacseny");
		trojzvukNazovTab.Pridaj("MOL;molový,molovy");
		trojzvukNazovTab.Pridaj("DUR;durový,durovy");
		trojzvukNazovTab.Pridaj("5;kvintakord");
		trojzvukNazovTab.Pridaj("6;sextakord");
		trojzvukNazovTab.Pridaj("46;kvartsextakord");
		trojzvukNazovTab.Pridaj("64;kvartsextakord");
		
		stvorzvukTab = new TabulkaRetazcov();
		stvorzvukTab.Pridaj("4,7,10;D7");
		stvorzvukTab.Pridaj("3,6,8;D56");
		stvorzvukTab.Pridaj("3,5,9;D34");
		stvorzvukTab.Pridaj("2,6,9;D2");
		stvorzvukTab.Pridaj("3,6,9;ZMZM7,ZMZM56,ZMZM34,ZMZM2");
		stvorzvukTab.Pridaj("3,6,10;ZMM7");
		stvorzvukTab.Pridaj("3,7,9;ZMM56");
		stvorzvukTab.Pridaj("4,6,9;ZMM34");
		stvorzvukTab.Pridaj("2,5,8;ZMM2");
		
		stvorzvukCharakter = new TabulkaRetazcov();
		stvorzvukCharakter.Pridaj("4,7,10;disonantný dominantný");
		stvorzvukCharakter.Pridaj("3,6,8;disonantný dominantný");
		stvorzvukCharakter.Pridaj("3,5,9;disonantný dominantný");
		stvorzvukCharakter.Pridaj("2,6,9;disonantný dominantný");
		stvorzvukCharakter.Pridaj("3,6,9;disonantný zmenšený");
		stvorzvukCharakter.Pridaj("3,6,10;disonantný zmenšený");
		stvorzvukCharakter.Pridaj("3,7,9;disonantný zmenšený");
		stvorzvukCharakter.Pridaj("4,6,9;disonantný zmenšený");
		stvorzvukCharakter.Pridaj("2,5,8;disonantný zmenšený");
		
		stvorzvukNazovTab = new TabulkaRetazcov();
		stvorzvukNazovTab.Pridaj("D;dominantný,dominantny");
		stvorzvukNazovTab.Pridaj("ZMZM;zmenšeno-zmenšený,zmenšeno zmenšený,zmenseno-zmenseny,zmenseno zmenseny");
		stvorzvukNazovTab.Pridaj("ZMM;zmenšeno-malý,zmenšeno malý,zmenseno-maly,zmenseno maly");
		stvorzvukNazovTab.Pridaj("7;septakord");
		stvorzvukNazovTab.Pridaj("56;kvintsextakord");
		stvorzvukNazovTab.Pridaj("65;kvintsextakord");
		stvorzvukNazovTab.Pridaj("34;terckvartakord");
		stvorzvukNazovTab.Pridaj("43;terckvartakord");
		stvorzvukNazovTab.Pridaj("2;sekundakord");
		
	}
	
	public static void main(String[] args) {	

		// TODO
		// pri skupinach aj moznost generickeho zadavania casti?
		
		Parser p = new Parser();
		
		try {
			Suzvuk xx;
			xx = p.Sparsuj("c4");
			if (xx != null) {
				System.out.println("nazov: "+xx.vratNazov());
				System.out.println("charakter: "+xx.vratCharakter());
				for (String s : xx.vratEnhNazvy()) {
					System.out.println("- "+s);
				}
			}
		} catch (ParsovanieNeuspesneVynimka e) {
			System.out.println("Parsovanie neuspesne!");
		}
		
	}
}

/**
 * Trieda spracuvávajúca vstup od používateľa a vytvárajúca podľa neho súzvuk (d.p. Factory)
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *  
 */

class Parser {
	
	/**
	 * Hlavná metóda - základná analýza vstupu a volanie špecifickejších metód na vytvorenie
	 * súzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Suzvuk Sparsuj(String vstup) throws ParsovanieNeuspesneVynimka {

		// zoznam obsahujúci v sebe prvé písmenká skratky dvojzvuku, na rýchlejšiu detekciu
		List<Character> dvojzvukPrvePismeno = new ArrayList<Character>();
		dvojzvukPrvePismeno.add('č');
		dvojzvukPrvePismeno.add('c');
		dvojzvukPrvePismeno.add('m');
		dvojzvukPrvePismeno.add('v');
		dvojzvukPrvePismeno.add('z');
		dvojzvukPrvePismeno.add('p');
		dvojzvukPrvePismeno.add('d');
		
		if (((vstup.length() < 6) && ((vstup.contains("-") == false) && (vstup.contains(",") == false))) || (vstup.contains("ZMZM"))) {
			// ide o tabuľkovú skratku
			
			if (dvojzvukPrvePismeno.contains(vstup.charAt(0))) {
				// ide o skratku dvojzvuku
				
				return SparsujDvojzvukSkratka(vstup);
				
			} else if ((vstup.contains("ZMZM")) || (vstup.contains("ZMM")) || ((vstup.charAt(0) == 'D') && (vstup.charAt(1)!='U'))) {
				// ide o skratku štvorzvuku
				
				return SparsujStvorzvukSkratka(vstup);
				
			} else {
				// ide o skratku trojzvuku
				
				return SparsujTrojzvukSkratka(vstup);
			}
		} else if (((vstup.length() == 6) || (vstup.length() == 7)) && (vstup.contains("("))) {
			// ide o tónový rad
			
			return SparsujTonovyRad(vstup);
			
		} else if (((vstup.contains(",")) || (vstup.contains("-"))) && (vstup.contains("ý") == false) && (vstup.contains("y") == false) && (vstup.contains("á") == false) && (vstup.contains("a") == false)) {
			// ide o skupinu skratiek alebo generickú skratku
			
			if (vstup.contains("-")) {
				String[] casti = vstup.split("-");
				if (Character.isDigit(casti[0].charAt(casti[0].length()-1))) {
					// ide o skupinu skratiek (so spojovníkom "-")
					
					return SparsujSkupinaSkratiek(vstup);
					
				} else {
					// ide o generickú skratku
					
					return SparsujGenerickuSkratku(vstup);
					
				}
			} else {
				// ide o skupinu skratiek (s čiarkou ",")
			
				return SparsujSkupinaSkratiek(vstup);
				
			}
		} else {
			// ide o názov alebo skupinu nazvov (intervaly,trojzvuky)
			
			if (((vstup.charAt(0) == 'c') || (vstup.charAt(0) == 'č') || (vstup.charAt(0) == 'p') || ((vstup.charAt(0) == 'm') && (vstup.contains("ovy") == false) && (vstup.contains("ový") == false) && (vstup.contains("ovo") == false)) || (vstup.charAt(0) == 'v')) || ((vstup.charAt(0) == 'z') && ((vstup.contains("ená") || vstup.contains("ena")))) || ((vstup.charAt(0) == 'd') && ((vstup.contains("ená") || vstup.contains("ena"))))) {
				// vstup začína intervalom
				
				if (vstup.contains("-") || vstup.contains(",")) {
					// ide o skupinu nazvov intervalov alebo interval - trojzvuk
					
					return SparsujSkupinaNazvov(vstup);
				} else {
					// ide o názov intervalu
					
					return SparsujDvojzvukNazov(vstup);
				}
			} else if (((vstup.charAt(0) == 'm') || ((vstup.charAt(0) == 'z') && ((vstup.contains("ený") || vstup.contains("eny")))) || ((vstup.charAt(0) == 'd') && ((vstup.contains("ový") || vstup.contains("ovy"))))) && (vstup.contains("eno") == false) && (vstup.contains("ovo") == false))  {
				// vstup začína názvom trojzvuku z tabuľky
				
				if (vstup.contains("-") || vstup.contains(",")) {
					// ide skupinu nazvov trojzvuk - interval
					
					return SparsujSkupinaNazvov(vstup);
				} else {
					// ide o názov trojzvuku z tabuľky
					
					return SparsujTrojzvukNazov(vstup);
				}
			} else if (vstup.contains("dom") || ((vstup.charAt(17) == ' ') && (vstup.contains("zmenšeno-zmenšený") || vstup.contains("zmenšeno zmenšený") || vstup.contains("zmenseno-zmenseny") || vstup.contains("zmenseno zmenseny"))) || ((vstup.charAt(13) == ' ') && (vstup.contains("zmenšeno-malý") || vstup.contains("zmenšeno malý") || vstup.contains("zmenseno-maly") || vstup.contains("zmenseno maly")))) {
				// ide o názov štvorzvuku z tabuľky
				
				return SparsujStvorzvukNazov(vstup);
			} else {
				// ide o generický názov
				
				return SparsujGenerickyNazov(vstup);
				
			}
		}
	}
	
	/**
	 * Spracovanie dvojzvuku, podľa dĺžky sa spracováva ako skratka alebo ako názov.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný dvojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Dvojzvuk SparsujDvojzvuk(String vstup) throws ParsovanieNeuspesneVynimka {
		if (vstup.length() < 7) {
			// ide o skratku
			
			return SparsujDvojzvukSkratka(vstup);
		} else {
			// ide o názov
			
			return SparsujDvojzvukNazov(vstup);
		}
	}
	
	/**
	 * Spracovanie skratky dvojzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný dvojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Dvojzvuk SparsujDvojzvukSkratka(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Dvojzvuk skratka");
		
		// získanie počtu podľa skratky z tabuľky
		Integer pocet = SluchovaAnalyza.dvojzvukTab.VratCislo(vstup);
		if (pocet != null) {
			return new Dvojzvuk(pocet);
		} else {
			throw new ParsovanieNeuspesneVynimka();
		}
	}
	
	/**
	 * Spracovanie názvu dvojzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný dvojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Dvojzvuk SparsujDvojzvukNazov(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Dvojzvuk nazov");
		
		// rozdelenie na časti podľa medzier
		String[] parse = vstup.split(" ");
		String skratka = "",nove;
		for (int i = 0; i < parse.length; i++) {
			// každá časť je prevedená na skratku a pridaná k celkovej skratke
			nove = SluchovaAnalyza.dvojzvukNazovTab.VratRetazec1(parse[i]);
			if (nove == null) {
				throw new ParsovanieNeuspesneVynimka();
			}
			skratka = skratka + nove;
		}
		// sparsovanie získanej skratky
		return SparsujDvojzvukSkratka(skratka);
	}
	
	/**
	 * Spracovanie trojzvuku, podľa dĺžky sa spracováva ako skratka alebo ako názov.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný trojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Trojzvuk SparsujTrojzvuk(String vstup) throws ParsovanieNeuspesneVynimka {
		if (vstup.length() < 10) {
			// ide o skratku
			return SparsujTrojzvukSkratka(vstup);
		} else {
			// ide o názov
			return SparsujTrojzvukNazov(vstup);
		}
	}
	
	/**
	 * Spracovanie skratky trojzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný trojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Trojzvuk SparsujTrojzvukSkratka(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Trojzvuk skratka");
		
		// získanie počtov podľa skratky z tabuľky
		List<String> polozka = SluchovaAnalyza.trojzvukTab.VratZoznam1(vstup);
		if (polozka == null) {
			throw new ParsovanieNeuspesneVynimka();
		}
		try {
			return new Trojzvuk(new Integer(polozka.get(0)),new Integer(polozka.get(1)));
		} catch (IndexOutOfBoundsException e) {
			throw new ParsovanieNeuspesneVynimka();
		}
	}

	/**
	 * Spracovanie názvu trojzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný trojzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Trojzvuk SparsujTrojzvukNazov(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Trojzvuk nazov");
		
		// rozdelenie na časti podľa medzier
		String[] parse = vstup.split(" ");
		String skratka = "",nove;
		for (int i = 0; i < parse.length; i++) {
			// každá časť je prevedená na skratku a pridaná k celkovej skratke
			nove = SluchovaAnalyza.trojzvukNazovTab.VratRetazec1(parse[i]);
			if (nove == null) {
				throw new ParsovanieNeuspesneVynimka();
			}
			skratka = skratka + nove;
		}
		// sparsovanie získanej skratky
		return SparsujTrojzvukSkratka(skratka);
	}
	
	/**
	 * Spracovanie štvorzvuku, podľa dĺžky sa spracováva ako skratka alebo ako názov.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný štvorzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Stvorzvuk SparsujStvorzvuk(String vstup) throws ParsovanieNeuspesneVynimka {
		if (vstup.length() < 14) {
			// ide o skratku
			return SparsujStvorzvukSkratka(vstup);
		} else {
			// ide o názov
			return SparsujStvorzvukNazov(vstup);
		}
	}
	
	/**
	 * Spracovanie skratky štvorzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný štvorzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Stvorzvuk SparsujStvorzvukSkratka(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Stvorzvuk skratka");
		
		// získanie počtov podľa skratky z tabuľky
		List<String> polozka = SluchovaAnalyza.stvorzvukTab.VratZoznam1(vstup);
		if (polozka == null) {
			throw new ParsovanieNeuspesneVynimka();
		} 
		try {
			return new Stvorzvuk(new Integer(polozka.get(0)),new Integer(polozka.get(1)),new Integer(polozka.get(2)));
		} catch (IndexOutOfBoundsException e) {
			throw new ParsovanieNeuspesneVynimka();
		}
	}
	
	/**
	 * Spracovanie názvu štvorzvuku.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný štvorzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Stvorzvuk SparsujStvorzvukNazov(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Stvorzvuk nazov");
		
		// rozdelenie na časti podľa medzier
		String[] parse = vstup.split(" ");
		String skratka = "",nove;
		for (int i = 0; i < parse.length; i++) {
			// každá časť je prevedená na skratku a pridaná k celkovej skratke
			nove = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec1(parse[i]);
			if (nove == null) {
				throw new ParsovanieNeuspesneVynimka();
			}
			skratka = skratka + nove;
		}
		return SparsujStvorzvukSkratka(skratka);
	}
	
	/**
	 * Spracovanie generickej skratky.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Suzvuk SparsujGenerickuSkratku(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Generická skratka");
		
		// rozdelenie na číselnú časť (akord) a písmenovú časť (intervaly)
		int i = vstup.length()-1;
		while (Character.isDigit(vstup.charAt(i))) {
			i=i-1;
		}
		String akord = vstup.substring(i+1);
		String intervaly = vstup.substring(0,i+1);
		
		if (akord.equals("5") || akord.equals("6") || akord.equals("46")) {
			// ide o generickú skratku trojzvuku
			
			// rozdelenie na časti podľa spojovníka "-", výmena 'd' za 'v' v prvej časti
			String[] parse = intervaly.split("-");
			if (parse[0].equals("d")) {
				parse[0] = "v";
			}
			// pridanie "3" a "5" ku jednotlivým písmenkám generickej časti a sparsovanie ako dvojzvuk
			// zo sparsovaných dvojzvukov dostaneme počty podľa ktorých vytvoríme kvintakord
			Trojzvuk kvintakord = new Trojzvuk(SparsujDvojzvukSkratka(parse[0] + "3").getPocet(),SparsujDvojzvukSkratka(parse[1] + "5").getPocet());
			
			// podľa toho, aký akord sme parsovali ešte kvintakord obrátime
			if (akord.equals("5")) {
				return kvintakord;
			} else if (akord.equals("6")) {
				return kvintakord.urobObratDopredu();
			} else if (akord.equals("46")) {
				return kvintakord.urobObratDozadu();
			}
			
		} else if (akord.equals("7") || akord.equals("56") || akord.equals("34") || akord.equals("2")) {
			// ide o generickú skratku štvorzvuku
			
			// rozdelenie na časti podľa spojovníka "-"
			String[] parse = intervaly.split("-");
			Stvorzvuk septakord;
			if (parse.length == 2) {
				// ide o generickú skratku s jedným spojovníkom (KVINTAKORD-septima)
				
				// pridanie "5" a "7" ku jednotlivým písmenkám generickej časti a sparsovanie ako
				// trojzvuk a dvojzvuk. Z toho dostaneme počty podľa ktorých vytvoríme septakord
				septakord = new Stvorzvuk(SparsujTrojzvukSkratka(parse[0] + "5").getPocet1(),SparsujTrojzvukSkratka(parse[0] + "5").getPocet2(),SparsujDvojzvukSkratka(parse[1] + "7").getPocet());
				
			} else {
				// ide o generickú skratku s dvoma spojovníkmi (tercia-kvinta-septima)
				
				// výmena 'd' za 'v' v prvej časti
				if (parse[0].equals("d")) {
					parse[0] = "v";
				}
				
				// pridanie "3", "5" a "7" ku jednotlivým písmenkám generickej časti a sparsovanie
				// ako dvojzvuk. Z nich dostaneme počty podľa ktorých vytvoríme septakord
				septakord = new Stvorzvuk(SparsujDvojzvukSkratka(parse[0] + "3").getPocet(),SparsujDvojzvukSkratka(parse[1] + "5").getPocet(),SparsujDvojzvukSkratka(parse[2] + "7").getPocet());
				
			}
			
			// podľa toho, aký akord sme parsovali ešte septakord obrátime
			if (akord.equals("7")) {
				return septakord;
			} else if (akord.equals("56")) {
				return septakord.urobObratDopredu();
			} else if (akord.equals("34")) {
				return septakord.urobObratDopredu().urobObratDopredu();
			} else if (akord.equals("2")) {
				return septakord.urobObratDozadu();
			}
			
		}
		
		return null;
	}
	
	/**
	 * Spracovanie generického názvu.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */
	
	Suzvuk SparsujGenerickyNazov(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Generický názov");
		
		// rozdelenie na časti podľa medzery
		String[] parse = vstup.split(" ");	
		if (parse.length != 2) {
			throw new ParsovanieNeuspesneVynimka();
		}
		
		// získanie skratky akordu z poslednej časti
		String akord = SluchovaAnalyza.stvorzvukNazovTab.VratRetazec1(parse[1]);
		
		// rozdelenie generickej časti na podčasti podľa spojovníka "-"
		String[] casti = parse[0].split("-");
		
		Stvorzvuk septakord;
		if (akord.equals("5") || akord.equals("6") || akord.equals("46")) {
			// ide o generický názov trojzvuku
			
			if (casti.length != 2) {
				throw new ParsovanieNeuspesneVynimka();
			}
			
			// úprava častí z "durovo"->"velka", "molovo"->"mala", "zmenseny"->"zmensena" a pod
			for (int i = 0; i < casti.length; i++) {
				if (casti[i].equals("durovo")) {
					casti[i] = "velka";
				} else if (casti[i].equals("molovo")) {
					casti[i] = "mala";
				} else {
					casti[i] = casti[i].substring(0,casti[i].length()-1) + "a";
				}
			}
			
			// pridanie "tercia" a "kvinta" ku jednotlivým častiam generickej časti a sparsovanie
			// ako názov dvojzvuku. Z toho dostaneme počty podľa ktorých vytvoríme kvintakord
			Trojzvuk kvintakord = new Trojzvuk(SparsujDvojzvukNazov(casti[0] + " tercia").getPocet(),SparsujDvojzvukNazov(casti[1] + " kvinta").getPocet());
			
			// podľa toho, aký akord sme parsovali ešte kvintakord obrátime
			if (akord.equals("5")) {
				return kvintakord;
			} else if (akord.equals("6")) {
				return kvintakord.urobObratDopredu();
			} else if (akord.equals("46")) {
				return kvintakord.urobObratDozadu();
			}
			
		} else if (akord.equals("7") || akord.equals("56") || akord.equals("34") || akord.equals("2")) {	
			// ide o generický názov štvorzvuku
			
			if (casti.length == 2) {
				// ide o generický názov s jedným spojovníkom (KVINTAKORD-septima)
			
				// úprava častí z "durovo"->"durovy", "molovo"->"molovy", "maly"->"mala" a pod
				for (int i = 0; i < casti.length; i++) {
					if (i == 0) {
						casti[i] = casti[i].substring(0,casti[i].length()-1) + "y";
					} else if (i == 1) {
						casti[i] = casti[i].substring(0,casti[i].length()-1) + "a";
					}
				}
				
				// pridanie "kvintakord" a "septima" ku jednotlivým častiam generickej časti a
				// sparsovanie ako názvy. Z toho dostaneme počty podľa ktorých vytvoríme septakord
				septakord = new Stvorzvuk(SparsujTrojzvukNazov(casti[0] + " kvintakord").getPocet1(),SparsujTrojzvukNazov(casti[0] + " kvintakord").getPocet2(),SparsujDvojzvukNazov(casti[1] + " septima").getPocet());
			
			} else {
				// ide o generický názov s dvoma spojovníkmi (tercia-kvinta-septima)
				
				// úprava častí z "durovo"->"velka", "molovo"->"mala", "zmenseny"->"zmensena" a pod
				for (int i = 0; i < casti.length; i++) {
					if (casti[i].equals("durovo")) {
						casti[i] = "velka";
					} else if (casti[i].equals("molovo")) {
						casti[i] = "mala";
					} else {
						casti[i] = casti[i].substring(0,casti[i].length()-1) + "a";
					}		
				}
				
				// pridanie "tercia" , "kvinta" a "septima" ku jednotlivým častiam generickej časti a
				// sparsovanie ako názvy. Z toho dostaneme počty podľa ktorých vytvoríme septakord
				septakord = new Stvorzvuk(SparsujDvojzvukNazov(casti[0] + " tercia").getPocet(),SparsujDvojzvukNazov(casti[1] + " kvinta").getPocet(),SparsujDvojzvukNazov(casti[2] + " septima").getPocet());
				
			}
				
			// podľa toho, aký akord sme parsovali ešte septakord obrátime
			if (akord.equals("7")) {
				return septakord;
			} else if (akord.equals("56")) {
				return septakord.urobObratDopredu();
			} else if (akord.equals("34")) {
				return septakord.urobObratDopredu().urobObratDopredu();
			} else if (akord.equals("2")) {
				return septakord.urobObratDozadu();
			}
		}

		return null;
	}
	
	/**
	 * Spracovanie skupiny skratiek.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Suzvuk SparsujSkupinaSkratiek(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Skupina skratiek");
		
		// zoznam obsahujúci v sebe prvé písmenká skratky dvojzvuku, na rýchlejšiu detekciu
		List<Character> dvojzvukPrvePismeno = new ArrayList<Character>();
		dvojzvukPrvePismeno.add('č');
		dvojzvukPrvePismeno.add('c');
		dvojzvukPrvePismeno.add('m');
		dvojzvukPrvePismeno.add('v');
		dvojzvukPrvePismeno.add('z');
		dvojzvukPrvePismeno.add('p');
		dvojzvukPrvePismeno.add('d');		
		
		// zoznam počtov, z ktorých nakoniec vytvoríme súzvuk
		ArrayList<Integer> pocty = new ArrayList<Integer>();
		
		if (vstup.contains(",") && vstup.contains("-")) {
			// ak obsahuje naraz čiarku "," aj spojovník "-", nevieme sparsovať
			
			throw new ParsovanieNeuspesneVynimka();
		
		} else if (vstup.contains(",")) {
			// skratky sú oddelené čiarkou ","
			
			// rozdelenie na časti podľa čiarky ","
			String[] parse = vstup.split(",");

			// spracovanie jednotlivých skratiek
			for (String cast : parse) {
				if (dvojzvukPrvePismeno.contains(cast.charAt(0))) {
					// ide o dvojzvuk
					
					// sparsovanie ako skratky dvojzvuku, počet pridaný do zoznamu
					pocty.add(SparsujDvojzvukSkratka(cast).getPocet());
				} else
				if ((cast.contains("ZMZM")) || (vstup.contains("ZMM")) || ((vstup.charAt(0) == 'D') && (vstup.charAt(1)!='U'))) {
					// ide o štvorzvuk
					
					// sparsovanie ako skratky štvorzvuku, počty pridané do zoznamu
					pocty.add(SparsujStvorzvukSkratka(cast).getPocet1());
					pocty.add(SparsujStvorzvukSkratka(cast).getPocet2());
					pocty.add(SparsujStvorzvukSkratka(cast).getPocet3());
				} else {
					// ide o trojzvuk
					
					// sparsovanie ako skratky trojzvuku, počty pridané do zoznamu
					pocty.add(SparsujTrojzvukSkratka(cast).getPocet1());
					pocty.add(SparsujTrojzvukSkratka(cast).getPocet2());
				}
			}	
		} else if (vstup.contains("-")) {
			// skratky sú oddelené spojovníkom "-"
			
			// rozdelenie na časti podľa spojovníka "-"
			String[] parse = vstup.split("-");
			
			// v aktPocet bude aktuálny počet, aby sa mohol aditívne pridať každý ďalší počet
			int aktPocet = 0,pocet;
			
			// spracovanie jednotlivých skratiek
			for (String cast : parse) {
				if (dvojzvukPrvePismeno.contains(cast.charAt(0))) {
					// ide o dvojzvuk

					// sparsovanie ako skratky dvojzvuku, počet navýšený o aktPocet pridaný do zoznamu
					pocet = SparsujDvojzvukSkratka(cast).getPocet();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;			
				} else
				if ((cast.contains("ZMZM")) || (vstup.contains("ZMM")) || ((vstup.charAt(0) == 'D') && (vstup.charAt(1)!='U'))) {
					// ide o štvorzvuk

					// sparsovanie ako skratky štvorzvuku, počty navýšené o aktPocet pridané do zoznamu
					pocet = SparsujStvorzvukSkratka(cast).getPocet1();
					pocty.add(pocet + aktPocet);
					pocet = SparsujStvorzvukSkratka(cast).getPocet2();
					pocty.add(pocet + aktPocet);
					pocet = SparsujStvorzvukSkratka(cast).getPocet3();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;
				} else {
					// ide o trojzvuk

					// sparsovanie ako skratky trojzvuku, počty navýšené o aktPocet pridané do zoznamu
					pocet = SparsujTrojzvukSkratka(cast).getPocet1();
					pocty.add(pocet + aktPocet);
					pocet = SparsujTrojzvukSkratka(cast).getPocet2();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;
				}
			}
		}
		
		// utriedenie počtov pre prípad, že by vo vstupe neboli v správnom poradí
		Collections.sort(pocty);
		
		// vytvorenie súzvukov podľa získaných počtov
		switch (pocty.size()) {
			case 0: throw new ParsovanieNeuspesneVynimka();
			case 1: return new Dvojzvuk(pocty.get(0));
			case 2: return new Trojzvuk(pocty.get(0),pocty.get(1));
			case 3: return new Stvorzvuk(pocty.get(0),pocty.get(1),pocty.get(2));
			default: throw new ParsovanieNeuspesneVynimka();	
		}
	}
	
	/**
	 * Spracovanie skupiny názvov.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Suzvuk SparsujSkupinaNazvov(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Skupina názvov");
				
		// zoznam počtov, z ktorých nakoniec vytvoríme súzvuk
		ArrayList<Integer> pocty = new ArrayList<Integer>();
		
		if (vstup.contains(",") && vstup.contains("-")) {
			// ak obsahuje naraz čiarku "," aj spojovník "-", nevieme sparsovať
			
			throw new ParsovanieNeuspesneVynimka();
		} else if (vstup.contains(",")) {
			// názvy sú oddelené čiarkou ","
			
			// rozdelenie na časti podľa čiarky ","
			String[] parse = vstup.split(",");

			// spracovanie jednotlivých skratiek
			for (String cast : parse) {
				if (((cast.charAt(0) == 'c') || (cast.charAt(0) == 'č') || (cast.charAt(0) == 'p') || ((cast.charAt(0) == 'm') && (cast.contains("ovy") == false) && (cast.contains("ový") == false)) || (cast.charAt(0) == 'v')) || ((cast.charAt(0) == 'z') && ((cast.contains("ená") || cast.contains("ena")))) || ((cast.charAt(0) == 'd') && ((cast.contains("ená") || cast.contains("ena"))))) {
					// ide o dvojzvuk

					// sparsovanie ako názvy dvojzvuku, počet pridaný do zoznamu
					pocty.add(SparsujDvojzvukNazov(cast).getPocet());
				} else if (((cast.charAt(0) == 'm') || ((cast.charAt(0) == 'z') && ((cast.contains("ený") || cast.contains("eny")))) || ((cast.charAt(0) == 'd') && ((cast.contains("ový") || cast.contains("ovy"))))) && (cast.contains("eno") == false))  {
					// ide o trojzvuk

					// sparsovanie ako názov trojzvuku, počty pridané do zoznamu
					pocty.add(SparsujTrojzvukNazov(cast).getPocet1());
					pocty.add(SparsujTrojzvukNazov(cast).getPocet2());
				} else if ((cast.charAt(0) == 'd') || ((cast.charAt(0) == 'z') && (cast.contains("eno")))) {
					// ide o štvorzvuk

					// sparsovanie ako názov štvorzvuku, počty pridané do zoznamu
					pocty.add(SparsujStvorzvukNazov(cast).getPocet1());
					pocty.add(SparsujStvorzvukNazov(cast).getPocet2());
					pocty.add(SparsujStvorzvukNazov(cast).getPocet3());
				}
			}	
		} else if (vstup.contains("-")) {
			// názvy sú oddelené spojovníkom "-"
			
			// rozdelenie na časti podľa spojovníka "-"
			String[] parse = vstup.split("-");
			
			// v aktPocet bude aktuálny počet, aby sa mohol aditívne pridať každý ďalší počet 
			int aktPocet = 0,pocet;
			
			// spracovanie jednotlivých skratiek
			for (String cast : parse) {
				if (((cast.charAt(0) == 'c') || (cast.charAt(0) == 'č') || (cast.charAt(0) == 'p') || ((cast.charAt(0) == 'm') && (cast.contains("ovy") == false) && (cast.contains("ový") == false)) || (cast.charAt(0) == 'v')) || ((cast.charAt(0) == 'z') && ((cast.contains("ená") || cast.contains("ena")))) || ((cast.charAt(0) == 'd') && ((cast.contains("ená") || cast.contains("ena"))))) {
					// ide o dvojzvuk

					// sparsovanie ako názov dvojzvuku, počty navýšené o aktPocet pridané do zoznamu
					pocet = SparsujDvojzvukNazov(cast).getPocet();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;			
				} else if (((cast.charAt(0) == 'm') || ((cast.charAt(0) == 'z') && ((cast.contains("ený") || cast.contains("eny")))) || ((cast.charAt(0) == 'd') && ((cast.contains("ový") || cast.contains("ovy"))))) && (cast.contains("eno") == false))  {
					// ide o trojzvuk

					// sparsovanie ako názov trojzvuku, počty navýšené o aktPocet pridané do zoznamu
					pocet = SparsujTrojzvukNazov(cast).getPocet1();
					pocty.add(pocet + aktPocet);
					pocet = SparsujTrojzvukNazov(cast).getPocet2();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;
				} else if ((cast.charAt(0) == 'd') || ((cast.charAt(0) == 'z') && (cast.contains("eno")))) {
					// ide o štvorzvuk

					// sparsovanie ako názov štvorzvuku, počty navýšené o aktPocet pridané do zoznamu
					pocet = SparsujStvorzvukNazov(cast).getPocet1();
					pocty.add(pocet + aktPocet);
					pocet = SparsujStvorzvukNazov(cast).getPocet2();
					pocty.add(pocet + aktPocet);
					pocet = SparsujStvorzvukNazov(cast).getPocet3();
					pocty.add(pocet + aktPocet);
					aktPocet = aktPocet + pocet;
				}
			}
		}
		
		// utriedenie počtov pre prípad, že by vo vstupe neboli v správnom poradí
		Collections.sort(pocty);
		
		// vytvorenie súzvukov podľa získaných počtov
		switch (pocty.size()) {
			case 0: throw new ParsovanieNeuspesneVynimka();
			case 1: return new Dvojzvuk(pocty.get(0));
			case 2: return new Trojzvuk(pocty.get(0),pocty.get(1));
			case 3: return new Stvorzvuk(pocty.get(0),pocty.get(1),pocty.get(2));
			default: throw new ParsovanieNeuspesneVynimka();
		}
	}
	
	/**
	 * Spracovanie tónového radu.
	 * 
	 * @param vstup Vstupný reťazec.
	 * @return Správne vygenerovaný súzvuk.
	 * @throws ParsovanieNeuspesneVynimka Ak bolo parsovanie vstupného reťazca neúspešné.
	 */	
	
	Suzvuk SparsujTonovyRad(String vstup) throws ParsovanieNeuspesneVynimka {
		System.out.println("(parser) Tonovy rad");
		
		// TODO
		
		throw new ParsovanieNeuspesneVynimka();
	}
}

/**
 * Výnimka - parseru sa nepodarilo správne sparsovat vstup.
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 *  
 */

class ParsovanieNeuspesneVynimka extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Parsovanie neúspešné!"; 
	}
}