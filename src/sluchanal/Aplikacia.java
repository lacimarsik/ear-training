/* ----------------------------------------- *
 *                                           *
 *                Aplikacia                  *
 *                                           *
 *  grafické užívateľské prostredie projektu *
 *                                           *
 *                SLUCHANAL                  *
 *                                           *
 *              Ladislav Maršík              *
 *                                           *
 * ----------------------------------------- */

package sluchanal;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JDialog;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Rectangle;
import java.awt.Font;
import javax.swing.JList;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Hlavná zaobaľujúca vizuálna trieda aplikácie SluchAnal
 * 
 * @author Ladislav Maršík
 * @version 1.0
 * @since 5.2010
 * 
 */

public class Aplikacia {

	private JFrame hlavneOkno = null;
	private JPanel hlavneOknoObsah = null;
	private JMenuItem exitMenuItem = null;
	private JMenuItem aboutMenuItem = null;
	private JPanel analyzaPanel = null;
	private JPanel menuPanel = null;
	
	
	
	private JMenuBar menuLista = null;
	private JMenu fileMenu = null;
	private JMenu helpMenu = null;
	private JDialog aboutDialog = null;
	private JPanel aboutObsah = null;
	private JLabel aboutVerzia = null;
	private JLabel aboutCopyright = null;
	private JButton zahrajTlacitko = null;
	private JButton analyzujTlacitko = null;
	private JTextField vstupText = null;
	private JButton potvrdTlacitko = null;
	private JLabel cvicenieText = null;
	private JList analyzaList = null;
	private JList logList = null;
	private JLabel suzvukText = null;
	public static LinkedList<String> log;
	public static Parser parser;
	public static Suzvuk priklad;
	public static Syntetizator syntetizator;
	private JScrollPane analyzaScroll = null;
	private JButton dalejTlacitko = null;
	private JButton vycvik1Button = null;
	private JButton skuska1Button = null;
	private JButton vycvik2Button = null;
	private JButton skuska2Button = null;
	private JButton vycvik3Button = null;
	private JButton skuska3Button = null;
	private JLabel uroven1Text = null;
	private JLabel uroven2Text = null;
	private JLabel uroven3Text = null;
	private JLabel cvicenieText4 = null;
	private JTextArea jTextArea = null;
	private JButton jButton = null;
	private JLabel cvicenieText11 = null;
	private JLabel cvicenieText111 = null;
	private JLabel cvicenieText1111 = null;
	private JLabel cvicenieText1112 = null;
	private JPanel spodnyPanel = null;
	private JButton navratButton = null;
	public static String meno = null;
	public static String uroven = null;
	public static String mod = null;
	public static boolean spravne = true;
	public static int poc = 0;
	private JLabel ucivoText = null;
	public static boolean potvrdil = false;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JDialog pomocDialog = null;
	private JPanel jContentPane = null;
	private JButton jButton1 = null;
	private JLabel jLabel41 = null;
	private JLabel jLabel42 = null;
	private JLabel jLabel43 = null;
	private JLabel jLabel411 = null;
	private JLabel jLabel412 = null;
	private JButton pomocButton2 = null;
	private JButton prieskumButton = null;


	public void vypis(String text) {
		if (text != null) {
			log.addFirst(text);
			logList.setListData(log.toArray());
		}
	}

	/**
	 * This method initializes zahrajTlacitko
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getZahrajTlacitko() {
		if (zahrajTlacitko == null) {
			zahrajTlacitko = new JButton();
			zahrajTlacitko.setText("Zahraj");
			zahrajTlacitko.setSize(new Dimension(100, 30));
			zahrajTlacitko.setLocation(new Point(20, 81));
			zahrajTlacitko
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							syntetizator.hraj(priklad);

						}
					});
		}
		return zahrajTlacitko;
	}

	/**
	 * This method initializes analyzujTlacitko
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getAnalyzujTlacitko() {
		if (analyzujTlacitko == null) {
			analyzujTlacitko = new JButton();
			analyzujTlacitko.setText("Analyzuj");
			analyzujTlacitko.setBounds(new Rectangle(20, 115, 100, 30));
			analyzujTlacitko
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							spravne = false;

							LinkedList<String> analyza = new LinkedList<String>();

							String nazov = priklad.vratNazov();
							String pocty = priklad.vratPocty();
							String charakter = priklad.vratCharakter();
							List<String> enhNazvy = priklad.vratEnhNazvy();
							List<String> enhSkratky = priklad.vratEnh();

							if (nazov == null) {
								nazov = "(nenájdený)";
							}
							if (pocty == null) {
								pocty = "(nenájdené)";
							}
							if (charakter == null) {
								charakter = "(nenájdený)";
							}
							if (enhNazvy == null) {
								enhNazvy = new ArrayList<String>();
							}
							if (enhSkratky == null) {
								enhSkratky = new ArrayList<String>();
							}

							analyza.add("Názov: " + nazov);
							analyza.add("Počty poltónov: " + pocty);
							analyza.add("Charakter: " + charakter);
							analyza.add(" ");
							analyza.add("Ďalšie názvy: ");
							analyza
									.addAll(enhNazvy
											.subList(1, enhNazvy.size()));
							analyza.add(" ");
							analyza.add("Skratky: ");
							analyza.addAll(enhSkratky);

							analyzaList.setListData(analyza.toArray());

						}
					});
		}
		return analyzujTlacitko;
	}

	/**
	 * This method initializes vstupText
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getVstupText() {
		if (vstupText == null) {
			vstupText = new JTextField();
			vstupText.setBounds(new Rectangle(124, 278, 300, 30));
		}
		return vstupText;
	}

	/**
	 * This method initializes potvrdTlacitko
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPotvrdTlacitko() {
		if (potvrdTlacitko == null) {
			potvrdTlacitko = new JButton();
			potvrdTlacitko.setText("Potvrď");
			potvrdTlacitko.setLocation(new Point(426, 279));
			potvrdTlacitko.setSize(new Dimension(78, 28));
			potvrdTlacitko
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							if ((vstupText.getText() != null)
									&& (vstupText.getText().equals("") == false)) {
								String vstup = vstupText.getText();
								try {
									potvrdil = true;

									Suzvuk odpoved = parser.Sparsuj(vstup);

									if (priklad.equals(odpoved)) {
										vypis("- SPRÁVNE!!!");
									} else {
										vypis("- Nesprávne...");
										spravne = false;
									}

								} catch (ParsovanieNeuspesneVynimka vynimka) {
									vypis("- neznámy vstup");
								}
							}
						}
					});
		}
		return potvrdTlacitko;
	}

	/**
	 * This method initializes analyzaList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getAnalyzaList() {
		if (analyzaList == null) {
			analyzaList = new JList();
			analyzaList.setBounds(new Rectangle(400, 100, 39, 27));
		}
		return analyzaList;
	}

	/**
	 * This method initializes logList
	 * 
	 * @return javax.swing.JList
	 */
	private JList getLogList() {
		if (logList == null) {
			logList = new JList();
			logList.setBounds(new Rectangle(124, 313, 300, 100));
		}
		return logList;
	}

	/**
	 * This method initializes analyzaScroll
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getAnalyzaScroll() {
		if (analyzaScroll == null) {
			analyzaScroll = new JScrollPane(analyzaList);
			analyzaScroll.setBounds(new Rectangle(125, 115, 297, 159));
		}
		return analyzaScroll;
	}

	/**
	 * This method initializes dalejTlacitko
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getDalejTlacitko() {
		if (dalejTlacitko == null) {
			dalejTlacitko = new JButton();
			dalejTlacitko.setFont(new Font("Dialog", Font.BOLD, 8));
			dalejTlacitko.setSize(new Dimension(42, 25));
			dalejTlacitko.setLocation(new Point(429, 83));
			dalejTlacitko.setText(">");
			dalejTlacitko
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {

							if (potvrdil == false) {
								spravne = false;
							}
							potvrdil = false;

							if (suzvukText.getText().equals("interval 1/10")) {
								suzvukText.setText("interval 2/10");
							} else if (suzvukText.getText().equals(
									"interval 2/10")) {
								suzvukText.setText("interval 3/10");
							} else if (suzvukText.getText().equals(
									"interval 3/10")) {
								suzvukText.setText("interval 4/10");
							} else if (suzvukText.getText().equals(
									"interval 4/10")) {
								suzvukText.setText("interval 5/10");
							} else if (suzvukText.getText().equals(
									"interval 5/10")) {
								suzvukText.setText("interval 6/10");
							} else if (suzvukText.getText().equals(
									"interval 6/10")) {
								suzvukText.setText("interval 7/10");
							} else if (suzvukText.getText().equals(
									"interval 7/10")) {
								suzvukText.setText("interval 8/10");
							} else if (suzvukText.getText().equals(
									"interval 8/10")) {
								suzvukText.setText("interval 9/10");
							} else if (suzvukText.getText().equals(
									"interval 9/10")) {
								suzvukText.setText("interval 10/10");
							}

							if (suzvukText.getText().equals("trojzvuk 1/10")) {
								suzvukText.setText("trojzvuk 2/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 2/10")) {
								suzvukText.setText("trojzvuk 3/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 3/10")) {
								suzvukText.setText("trojzvuk 4/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 4/10")) {
								suzvukText.setText("trojzvuk 5/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 5/10")) {
								suzvukText.setText("trojzvuk 6/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 6/10")) {
								suzvukText.setText("trojzvuk 7/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 7/10")) {
								suzvukText.setText("trojzvuk 8/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 8/10")) {
								suzvukText.setText("trojzvuk 9/10");
							} else if (suzvukText.getText().equals(
									"trojzvuk 9/10")) {
								suzvukText.setText("trojzvuk 10/10");
							}

							if (suzvukText.getText().equals("súzvuk 1/10")) {
								suzvukText.setText("súzvuk 2/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 2/10")) {
								suzvukText.setText("súzvuk 3/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 3/10")) {
								suzvukText.setText("súzvuk 4/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 4/10")) {
								suzvukText.setText("súzvuk 5/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 5/10")) {
								suzvukText.setText("súzvuk 6/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 6/10")) {
								suzvukText.setText("súzvuk 7/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 7/10")) {
								suzvukText.setText("súzvuk 8/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 8/10")) {
								suzvukText.setText("súzvuk 9/10");
							} else if (suzvukText.getText().equals(
									"súzvuk 9/10")) {
								suzvukText.setText("súzvuk 10/10");
							}

							if (mod.equals("1v")) {
								java.util.Random rand = new Random();
								int[] p;

								int r = rand.nextInt(3);
								r = 0;
								switch (r) {
								case 0:
									priklad = new Dvojzvuk(
											rand.nextInt(49) + 36, rand
													.nextInt(13));
									break;
								case 1:
									p = new int[2];
									while (p[0] == p[1]) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Trojzvuk(
											rand.nextInt(49) + 36, p[0], p[1]);
									break;
								case 2:
									p = new int[3];
									while ((p[0] == p[1]) || (p[1] == p[2])
											|| (p[0] == p[2])) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
										p[2] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Stvorzvuk(
											rand.nextInt(49) + 36, p[0], p[1],
											p[2]);
									break;
								}

							} else if (mod.equals("1s")) {
								java.util.Random rand = new Random();
								int r = rand.nextInt(49) + 36;
								if (priklad.equals(new Dvojzvuk(r, 5))) {
									priklad = new Dvojzvuk(r, 8);
								} else if (priklad.equals(new Dvojzvuk(r, 8))) {
									priklad = new Dvojzvuk(r, 7);
								} else if (priklad.equals(new Dvojzvuk(r, 7))) {
									priklad = new Dvojzvuk(r, 4);
								} else if (priklad.equals(new Dvojzvuk(r, 4))) {
									priklad = new Dvojzvuk(r, 11);
								} else if (priklad.equals(new Dvojzvuk(r, 11))) {
									priklad = new Dvojzvuk(r, 12);
								} else if (priklad.equals(new Dvojzvuk(r, 12))) {
									priklad = new Dvojzvuk(r, 2);
								} else if (priklad.equals(new Dvojzvuk(r, 2))) {
									priklad = new Dvojzvuk(r, 10);
								} else if (priklad.equals(new Dvojzvuk(r, 10))) {
									priklad = new Dvojzvuk(r, 9);
								} else if (priklad.equals(new Dvojzvuk(r, 9))) {
									priklad = new Dvojzvuk(r, 6);
								} else if (priklad.equals(new Dvojzvuk(r, 6))) {

									if (spravne == true) {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("GRATUĽUJEME!!!!");
										temp.add("-------------------------------------");
										temp.add("Vaša úroveň je:");
										temp.add("--------------   1   --------------");
										temp.add("teraz zvoĺte návrat");
										analyzaList.setListData(temp.toArray());
										if ((uroven.equals("2") == false)
												&& (uroven.equals("3") == false)) {
											uroven = "1";
											cvicenieText1112.setText(uroven);
										}

									} else {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("               SKÚŠKU STE NESPRAVILI!");
										temp
												.add("-------------------------------------");
										temp
												.add(" Nezodpovedali ste správne všetky príklady");
										temp
												.add("-------------------------------------");
										temp
												.add(" Vaša úroveň zostáva nezmenená");
										temp.add("---------------------------");
										temp.add(" Teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());
										
										analyzaList.setListData(temp.toArray());
										if ((uroven.equals("2") == false)
												&& (uroven.equals("3") == false)) {
											uroven = "1";
											cvicenieText1112.setText(uroven);
										}

									}

								}

							} else if (mod.equals("2v")) {
								java.util.Random rand = new Random();
								int[] p;

								int r = rand.nextInt(2);
								r = 1;
								// if (r == 0) {
								// r = 1;
								// }
								switch (r) {
								case 0:
									priklad = new Dvojzvuk(
											rand.nextInt(49) + 36, rand
													.nextInt(13));
									break;
								case 1:
									p = new int[2];
									while (p[0] == p[1]) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Trojzvuk(
											rand.nextInt(49) + 36, p[0], p[1]);
									break;
								case 2:
									p = new int[3];
									while ((p[0] == p[1]) || (p[1] == p[2])
											|| (p[0] == p[2])) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
										p[2] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Stvorzvuk(
											rand.nextInt(49) + 36, p[0], p[1],
											p[2]);
									break;
								}

							} else if (mod.equals("2s")) {
								java.util.Random rand = new Random();
								int r = rand.nextInt(49) + 36;
								if (priklad.equals(new Trojzvuk(r, 3, 7))) {
									priklad = new Trojzvuk(r, 4, 7);
								} else if (priklad
										.equals(new Trojzvuk(r, 4, 7))) {
									priklad = new Trojzvuk(r, 3, 8);
								} else if (priklad
										.equals(new Trojzvuk(r, 3, 8))) {
									priklad = new Trojzvuk(r, 4, 8);
								} else if (priklad
										.equals(new Trojzvuk(r, 4, 8))) {
									priklad = new Trojzvuk(r, 5, 9);
								} else if (priklad
										.equals(new Trojzvuk(r, 5, 9))) {
									priklad = new Trojzvuk(r, 6, 9);
								} else if (priklad
										.equals(new Trojzvuk(r, 6, 9))) {
									priklad = new Trojzvuk(r, 7, 10);
								} else if (priklad
										.equals(new Trojzvuk(r, 7, 10))) {
									priklad = new Trojzvuk(r, 2, 6);
								} else if (priklad
										.equals(new Trojzvuk(r, 2, 6))) {
									priklad = new Trojzvuk(r, 1, 9);
								} else if (priklad
										.equals(new Trojzvuk(r, 1, 9))) {
									priklad = new Trojzvuk(r, 8, 9);
								} else if (priklad
										.equals(new Trojzvuk(r, 8, 9))) {

									if (spravne == true) {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("                      GRATUĽUJEME!!!!");
										temp.add("-------------------------------------");
										temp.add("                        Vaša úroveň je:");
										temp.add("----------------   2   -----------------");
										temp.add(" Teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());
										if (uroven.equals("3") == false) {
											uroven = "2";
											cvicenieText1112.setText(uroven);
										}

									} else {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("               SKÚŠKU STE NESPRAVILI!");
										temp
												.add("-------------------------------------");
										temp
												.add(" Nezodpovedali ste správne všetky príklady");
										temp
												.add("-------------------------------------");
										temp
												.add(" Vaša úroveň zostáva nezmenená");
										temp.add("---------------------------");
										temp.add(" Teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());

									}

								}

							} else if (mod.equals("3v")) {
								java.util.Random rand = new Random();
								int[] p;

								int r = rand.nextInt(3);
								// r = 2;
								switch (r) {
								case 0:
									priklad = new Dvojzvuk(
											rand.nextInt(49) + 36, rand
													.nextInt(13));
									break;
								case 1:
									p = new int[2];
									while (p[0] == p[1]) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Trojzvuk(
											rand.nextInt(49) + 36, p[0], p[1]);
									break;
								case 2:
									p = new int[3];
									while ((p[0] == p[1]) || (p[1] == p[2])
											|| (p[0] == p[2])) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
										p[2] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Stvorzvuk(
											rand.nextInt(49) + 36, p[0], p[1],
											p[2]);
									break;
								}

							} else if (mod.equals("3s")) {
								if (poc == 0) {
									poc = 1;
								} else {
									poc = poc + 1;
								}
								
								java.util.Random rand = new Random();
								int[] p;

								int r = rand.nextInt(3);
								// r = 2;
								switch (r) {
								case 0:
									priklad = new Dvojzvuk(
											rand.nextInt(49) + 36, rand
													.nextInt(13));
									break;
								case 1:
									p = new int[2];
									while (p[0] == p[1]) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Trojzvuk(
											rand.nextInt(49) + 36, p[0], p[1]);
									break;
								case 2:
									p = new int[3];
									while ((p[0] == p[1]) || (p[1] == p[2])
											|| (p[0] == p[2])) {
										p[0] = rand.nextInt(13);
										p[1] = rand.nextInt(13);
										p[2] = rand.nextInt(13);
									}
									Arrays.sort(p);
									priklad = new Stvorzvuk(
											rand.nextInt(49) + 36, p[0], p[1],
											p[2]);
									break;
								}

								if (poc > 9) {
									if (spravne == true) {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("                      GRATUĽUJEME!!!!");
										temp.add("-------------------------------------");
										temp.add("                        Vaša úroveň je:");
										temp.add("----------------   3   -----------------");
										temp.add(" Teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());
										uroven = "3";
										cvicenieText1112.setText(uroven);

									} else {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add("               SKÚŠKU STE NESPRAVILI!");
										temp
												.add("-------------------------------------");
										temp
												.add(" Nezodpovedali ste správne všetky príklady");
										temp
												.add("-------------------------------------");
										temp
												.add(" Vaša úroveň zostáva nezmenená");
										temp.add("---------------------------");
										temp.add(" Teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());

									}
								}

							} else if (mod.equals("p")) {
								java.util.Random rand = new Random();
								int r = rand.nextInt(49) + 36;
								if (priklad.equals(new Dvojzvuk(r, 5))) {
									priklad = new Dvojzvuk(r, 1);
								} else if (priklad.equals(new Dvojzvuk(r, 1))) {
									priklad = new Trojzvuk(r, 4, 8);
								} else if (priklad.equals(new Trojzvuk(r, 4, 8))) {
									priklad = new Trojzvuk(r, 5, 9);
								} else if (priklad.equals(new Trojzvuk(r, 5, 9))) {
									priklad = new Trojzvuk(r, 2, 7);
								} else if (priklad.equals(new Trojzvuk(r, 2, 7))) {
									priklad = new Stvorzvuk(r, 3, 6, 10);
								} else if (priklad.equals(new Stvorzvuk(r, 3, 6, 10))) {
									priklad = new Stvorzvuk(r, 2, 3, 5);
								} else if (priklad.equals(new Stvorzvuk(r, 2, 3, 5))) {
									priklad = new Stvorzvuk(r, 4, 8, 9);
								} else if (priklad.equals(new Stvorzvuk(r, 4, 8, 9))) {
									priklad = new Trojzvuk(r, 7, 12);
								} else if (priklad.equals(new Trojzvuk(r, 7, 12))) {
									priklad = new Stvorzvuk(r, 8, 10, 11);
								} else if (priklad.equals(new Stvorzvuk(r, 8, 10, 11))) {

									if (spravne == true) {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add(" :) ĎAKUJEME :)");
										temp.add("-------------------------------------");
										temp.add(" a tiež GOOD JOB, všetko dobre!:");
										temp.add("-----------------------------------");
										temp.add(" teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());
										

									} else {
										LinkedList<String> temp = new LinkedList<String>();
										temp.add("");
										temp.add(" :) ĎAKUJEME :)");
										temp.add("-------------------------------------");
										temp.add(" veľmi ste nám pomohli!!");
										temp.add("-----------------------------------");
										temp.add(" teraz zvoľte návrat");
										analyzaList.setListData(temp.toArray());
										

									}

								}
							}

							/*
							 * java.util.Random rand = new Random(); int[] p;
							 * 
							 * int r = rand.nextInt(3); //r = 2; switch (r) {
							 * case 0: priklad = new
							 * Dvojzvuk(rand.nextInt(49)+36,rand.nextInt(13));
							 * break; case 1: p = new int[2]; while (p[0] ==
							 * p[1]) { p[0] = rand.nextInt(13); p[1] =
							 * rand.nextInt(13); } Arrays.sort(p); priklad = new
							 * Trojzvuk(rand.nextInt(49)+36,p[0],p[1]); break;
							 * case 2: p = new int[3]; while ((p[0] == p[1]) ||
							 * (p[1] == p[2]) || (p[0] == p[2])) { p[0] =
							 * rand.nextInt(13); p[1] = rand.nextInt(13); p[2] =
							 * rand.nextInt(13); } Arrays.sort(p); priklad = new
							 * Stvorzvuk(rand.nextInt(49)+36,p[0],p[1],p[2]);
							 * break; }
							 */
						}
					});
		}
		return dalejTlacitko;
	}

	/**
	 * This method initializes analyzaPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getAnalyzaPanel() {
		if (analyzaPanel == null) {
			ucivoText = new JLabel();
			ucivoText.setBounds(new Rectangle(128, 61, 299, 15));
			ucivoText.setText("");
			ucivoText.setHorizontalAlignment(SwingConstants.CENTER);
			analyzaPanel = new JPanel();
			analyzaPanel.setLayout(null);
			analyzaPanel.setVisible(false);
			analyzaPanel.setBounds(new Rectangle(12, 7, 523, 420));
			analyzaPanel.add(getZahrajTlacitko(), null);
			analyzaPanel.add(getAnalyzujTlacitko(), null);
			analyzaPanel.add(getVstupText(), null);
			analyzaPanel.add(getLogList(), null);
			analyzaPanel.add(getPotvrdTlacitko(), null);
			analyzaPanel.add(getAnalyzaScroll(), null);
			analyzaPanel.add(suzvukText, null);
			analyzaPanel.add(getDalejTlacitko(), null);
			analyzaPanel.add(cvicenieText, null);
			analyzaPanel.add(ucivoText, null);
		}
		return analyzaPanel;
	}

	/**
	 * This method initializes menuPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMenuPanel() {
		if (menuPanel == null) {
			jLabel5 = new JLabel();
			jLabel5.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel5.setBounds(new Rectangle(74, 162, 322, 15));
			jLabel5.setText("www.riesky.sk/~laco/web/sluchanal");
			jLabel4 = new JLabel();
			jLabel4.setFont(new Font("Dialog", Font.BOLD, 12));
			jLabel4.setBounds(new Rectangle(5, 88, 447, 15));
			jLabel4.setText("Ako analyzovať?");
			jLabel3 = new JLabel();
			jLabel3.setText("pozostáva z 10 príkladov, na ktoré musíte odpovedať správne!");
			jLabel3.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel3.setBounds(new Rectangle(65, 55, 413, 15));
			jLabel2 = new JLabel();
			jLabel2.setText("Skúška:");
			jLabel2.setBounds(new Rectangle(6, 38, 329, 15));
			jLabel1 = new JLabel();
			jLabel1.setText("Výcvik:");
			jLabel1.setBounds(new Rectangle(5, 7, 329, 15));
			jLabel = new JLabel();
			jLabel.setText("Vyber si úroveň a typ tréningu.");
			jLabel.setBounds(new Rectangle(16, 140, 226, 15));
			cvicenieText1112 = new JLabel();
			cvicenieText1112.setBounds(new Rectangle(160, 104, 25, 28));
			cvicenieText1112.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText1112.setText("");
			cvicenieText1112.setFont(new Font("Dialog", Font.BOLD, 18));
			cvicenieText1111 = new JLabel();
			cvicenieText1111.setBounds(new Rectangle(95, 108, 64, 20));
			cvicenieText1111.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText1111.setText("úroveň");
			cvicenieText1111.setFont(new Font("Dialog", Font.BOLD, 12));
			cvicenieText1111.setVisible(false);
			cvicenieText111 = new JLabel();
			cvicenieText111.setBounds(new Rectangle(4, 110, 92, 17));
			cvicenieText111.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText111.setText("");
			cvicenieText111.setFont(new Font("Dialog", Font.BOLD, 14));
			cvicenieText11 = new JLabel();
			cvicenieText11.setBounds(new Rectangle(10, 72, 128, 25));
			cvicenieText11.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText11.setText("Zadaj svoje meno:");
			cvicenieText11.setFont(new Font("Dialog", Font.BOLD, 12));
			cvicenieText4 = new JLabel();
			cvicenieText4.setBounds(new Rectangle(121, 22, 186, 38));
			cvicenieText4.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText4.setText("Vitaj, hudobník!");
			cvicenieText4.setFont(new Font("Dialog", Font.BOLD, 18));
			uroven3Text = new JLabel();
			uroven3Text.setHorizontalAlignment(SwingConstants.CENTER);
			uroven3Text.setText("Úroveň 3");
			uroven3Text.setSize(new Dimension(102, 23));
			uroven3Text.setLocation(new Point(36, 254));
			uroven3Text.setFont(new Font("Dialog", Font.BOLD, 14));
			uroven2Text = new JLabel();
			uroven2Text.setHorizontalAlignment(SwingConstants.CENTER);
			uroven2Text.setText("Úroveň 2");
			uroven2Text.setSize(new Dimension(101, 23));
			uroven2Text.setLocation(new Point(36, 211));
			uroven2Text.setFont(new Font("Dialog", Font.BOLD, 14));
			uroven1Text = new JLabel();
			uroven1Text.setBounds(new Rectangle(36, 168, 102, 23));
			uroven1Text.setHorizontalAlignment(SwingConstants.CENTER);
			uroven1Text.setText("Úroveň 1");
			uroven1Text.setFont(new Font("Dialog", Font.BOLD, 14));
			menuPanel = new JPanel();
			menuPanel.setLayout(null);
			menuPanel.setEnabled(false);
			menuPanel.setSize(new Dimension(504, 456));
			menuPanel.setLocation(new Point(75, 7));
			menuPanel.add(getVycvik1Button(), null);
			menuPanel.add(getSkuska1Button(), null);
			menuPanel.add(getVycvik2Button(), null);
			menuPanel.add(getSkuska2Button(), null);
			menuPanel.add(getVycvik3Button(), null);
			menuPanel.add(getSkuska3Button(), null);
			menuPanel.add(uroven1Text, null);
			menuPanel.add(uroven2Text, null);
			menuPanel.add(uroven3Text, null);
			menuPanel.add(cvicenieText4, null);
			menuPanel.add(getJTextArea(), null);
			menuPanel.add(getJButton(), null);
			menuPanel.add(cvicenieText11, null);
			menuPanel.add(cvicenieText111, null);
			menuPanel.add(cvicenieText1111, null);
			menuPanel.add(cvicenieText1112, null);
			menuPanel.add(jLabel, null);
			menuPanel.add(getJButton1(), null);
			menuPanel.add(getPrieskumButton(), null);
		}
		return menuPanel;
	}

	/**
	 * This method initializes vycvik1Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVycvik1Button() {
		if (vycvik1Button == null) {
			vycvik1Button = new JButton();
			vycvik1Button.setText("Výcvik");
			vycvik1Button.setEnabled(false);
			vycvik1Button.setSize(new Dimension(74, 25));
			vycvik1Button.setLocation(new Point(148, 166));
			vycvik1Button.setVisible(true);
			vycvik1Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "1v";
					ucivoText.setText("úroveň 1: Základné a rozšírené intervaly");
					cvicenieText.setText("Výcvik");
					suzvukText.setText("Náhodný interval");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);

					java.util.Random rand = new Random();
					int[] p;

					int r = rand.nextInt(3);
					r = 0;
					switch (r) {
					case 0:
						priklad = new Dvojzvuk(rand.nextInt(49) + 36, rand
								.nextInt(13));
						break;
					case 1:
						p = new int[2];
						while (p[0] == p[1]) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Trojzvuk(rand.nextInt(49) + 36, p[0],
								p[1]);
						break;
					case 2:
						p = new int[3];
						while ((p[0] == p[1]) || (p[1] == p[2])
								|| (p[0] == p[2])) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
							p[2] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Stvorzvuk(rand.nextInt(49) + 36, p[0],
								p[1], p[2]);
						break;
					}

				}
			});
		}
		return vycvik1Button;
	}

	/**
	 * This method initializes skuska1Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSkuska1Button() {
		if (skuska1Button == null) {
			skuska1Button = new JButton();
			skuska1Button.setEnabled(false);
			skuska1Button.setSize(new Dimension(83, 25));
			skuska1Button.setLocation(new Point(227, 166));
			skuska1Button.setText("Skúška");
			skuska1Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "1s";
					spravne = true;
					ucivoText
							.setText("úroveň 1: Základné a rozšírené intervaly");
					cvicenieText.setText("Skúška");
					suzvukText.setText("interval 1/10");
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);
					priklad = new Dvojzvuk(5);
				}
			});
		}
		return skuska1Button;
	}

	/**
	 * This method initializes vycvik2Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVycvik2Button() {
		if (vycvik2Button == null) {
			vycvik2Button = new JButton();
			vycvik2Button.setBounds(new Rectangle(148, 210, 74, 25));
			vycvik2Button.setEnabled(false);
			vycvik2Button.setText("Výcvik");
			vycvik2Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "2v";
					ucivoText.setText("úroveň 2: Trojzvuky");
					cvicenieText.setText("Výcvik");
					suzvukText.setText("Náhodný trojzvuk");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);
					java.util.Random rand = new Random();
					int[] p;

					int r = rand.nextInt(2);
					r = 1;
					switch (r) {
					case 0:
						priklad = new Dvojzvuk(rand.nextInt(49) + 36, rand
								.nextInt(13));
						break;
					case 1:
						p = new int[2];
						while (p[0] == p[1]) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Trojzvuk(rand.nextInt(49) + 36, p[0],
								p[1]);
						break;
					case 2:
						p = new int[3];
						while ((p[0] == p[1]) || (p[1] == p[2])
								|| (p[0] == p[2])) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
							p[2] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Stvorzvuk(rand.nextInt(49) + 36, p[0],
								p[1], p[2]);
						break;
					}
				}
			});
			vycvik2Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

				}
			});
		}
		return vycvik2Button;
	}

	/**
	 * This method initializes skuska2Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSkuska2Button() {
		if (skuska2Button == null) {
			skuska2Button = new JButton();
			skuska2Button.setBounds(new Rectangle(227, 210, 83, 25));
			skuska2Button.setEnabled(false);
			skuska2Button.setText("Skúška");
			skuska2Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "2s";
					ucivoText.setText("úroveň 2: Trojzvuky");
					cvicenieText.setText("Skúška");
					suzvukText.setText("trojzvuk 1/10");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);
					priklad = new Trojzvuk(3, 7);
				}
			});
		}
		return skuska2Button;
	}

	/**
	 * This method initializes vycvik3Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getVycvik3Button() {
		if (vycvik3Button == null) {
			vycvik3Button = new JButton();
			vycvik3Button.setBounds(new Rectangle(148, 253, 74, 25));
			vycvik3Button.setEnabled(false);
			vycvik3Button.setText("Výcvik");
			vycvik3Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "3v";
					ucivoText.setText("úroveň 3: Ľubovoľné súzvuky");
					cvicenieText.setText("Výcvik");
					suzvukText.setText("Náhodný súzvuk");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);
					java.util.Random rand = new Random();
					int[] p;

					int r = rand.nextInt(3);
					// r = 0;
					switch (r) {
					case 0:
						priklad = new Dvojzvuk(rand.nextInt(49) + 36, rand
								.nextInt(13));
						break;
					case 1:
						p = new int[2];
						while (p[0] == p[1]) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Trojzvuk(rand.nextInt(49) + 36, p[0],
								p[1]);
						break;
					case 2:
						p = new int[3];
						while ((p[0] == p[1]) || (p[1] == p[2])
								|| (p[0] == p[2])) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
							p[2] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Stvorzvuk(rand.nextInt(49) + 36, p[0],
								p[1], p[2]);
						break;
					}
				}
			});
		}
		return vycvik3Button;
	}

	/**
	 * This method initializes skuska3Button
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getSkuska3Button() {
		if (skuska3Button == null) {
			skuska3Button = new JButton();
			skuska3Button.setEnabled(false);
			skuska3Button.setSize(new Dimension(83, 25));
			skuska3Button.setLocation(new Point(227, 253));
			skuska3Button.setText("Skúška");
			skuska3Button.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "3s";
					ucivoText.setText("úroveň 3: Ľubovoľné súzvuky");
					cvicenieText.setText("Skúška");
					suzvukText.setText("súzvuk 1/10");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);
					
					
					java.util.Random rand = new Random();
					int[] p;

					int r = rand.nextInt(3);
					// r = 2;
					switch (r) {
					case 0:
						priklad = new Dvojzvuk(
								rand.nextInt(49) + 36, rand
										.nextInt(13));
						break;
					case 1:
						p = new int[2];
						while (p[0] == p[1]) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Trojzvuk(
								rand.nextInt(49) + 36, p[0], p[1]);
						break;
					case 2:
						p = new int[3];
						while ((p[0] == p[1]) || (p[1] == p[2])
								|| (p[0] == p[2])) {
							p[0] = rand.nextInt(13);
							p[1] = rand.nextInt(13);
							p[2] = rand.nextInt(13);
						}
						Arrays.sort(p);
						priklad = new Stvorzvuk(
								rand.nextInt(49) + 36, p[0], p[1],
								p[2]);
						break;
					}
					
					
					
				}
			});
		}
		return skuska3Button;
	}

	/**
	 * This method initializes jTextArea
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setBounds(new Rectangle(143, 75, 137, 19));
			jTextArea.setColumns(10);
			jTextArea.setFont(new Font("Dialog", Font.PLAIN, 14));
			jTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (jTextArea.getText().length() > 10) {
						jTextArea.setText(jTextArea.getText().substring(0,10));
					}
				
				}
			});
			jTextArea.addCaretListener(new javax.swing.event.CaretListener() {
				public void caretUpdate(javax.swing.event.CaretEvent e) {
					
				}
			});
		}
		return jTextArea;
	}

	/**
	 * This method initializes jButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(285, 75, 82, 19));
			jButton.setText("Potvrď");
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					if (jTextArea.getText().equals("") == false) {
						meno = jTextArea.getText();
						cvicenieText111.setText(meno);
						cvicenieText1112.setText("0");
						cvicenieText1111.setVisible(true);
						
						uroven = "0";

						vycvik1Button.setEnabled(true);
						skuska1Button.setEnabled(true);
						vycvik2Button.setEnabled(true);
						skuska2Button.setEnabled(true);
						vycvik3Button.setEnabled(true);
						skuska3Button.setEnabled(true);
						prieskumButton.setEnabled(true);
						
						/*
						try {
							URL yahoo = new URL("http://www.yahoo.com/");
							URLConnection yahooConnection = yahoo.openConnection();
							yahooConnection.connect();
						} catch (MalformedURLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/
						
					}

				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes spodnyPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getSpodnyPanel() {
		if (spodnyPanel == null) {
			spodnyPanel = new JPanel();
			spodnyPanel.setLayout(null);
			spodnyPanel.setBounds(new Rectangle(13, 433, 521, 53));
			spodnyPanel.setVisible(false);
			spodnyPanel.add(getNavratButton(), null);
			spodnyPanel.add(getPomocButton2(), null);
		}
		return spodnyPanel;
	}

	/**
	 * This method initializes navratButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getNavratButton() {
		if (navratButton == null) {
			navratButton = new JButton();
			navratButton.setText("Návrat");
			navratButton.setBounds(new Rectangle(8, 17, 74, 25));
			navratButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					analyzaPanel.setVisible(false);
					menuPanel.setVisible(true);
					spodnyPanel.setVisible(false);
					LinkedList<String> temp = new LinkedList<String>();
					temp.add("");
					// temp.add("GRATULUJEME!!!!");
					// temp.add("---------------");
					// temp.add("Vaša úroveň je:");
					// temp.add("----- 2 -------");

					analyzaList.setListData(temp.toArray());

				}
			});
		}
		return navratButton;
	}

	/**
	 * This method initializes pomocDialog	
	 * 	
	 * @return javax.swing.JDialog	
	 */
	private JDialog getPomocDialog() {
		if (pomocDialog == null) {
			pomocDialog = new JDialog(getHlavneOkno());
			pomocDialog.setSize(new Dimension(460, 180));
			pomocDialog.setMinimumSize(new Dimension(400, 150));
			pomocDialog.setPreferredSize(new Dimension(480, 220));
			pomocDialog.setLocation(new Point(200, 100));
			pomocDialog.setContentPane(getJContentPane());
		}
		return pomocDialog;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jLabel412 = new JLabel();
			jLabel412.setBounds(new Rectangle(65, 142, 338, 15));
			jLabel412.setText("Nájdeš ju ako prílohu práce, alebo na stránke");
			jLabel412.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel411 = new JLabel();
			jLabel411.setBounds(new Rectangle(65, 124, 338, 15));
			jLabel411.setText("Ak stále nevieš, čo zadávať, pozri si dokumentáciu");
			jLabel411.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel43 = new JLabel();
			jLabel43.setText("Analýza náhodných súzvukov");
			jLabel43.setSize(new Dimension(276, 15));
			jLabel43.setLocation(new Point(65, 7));
			jLabel43.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel42 = new JLabel();
			jLabel42.setBounds(new Rectangle(65, 38, 267, 15));
			jLabel42.setText("Možnosť zvýšiť si úroveň");
			jLabel42.setFont(new Font("Dialog", Font.PLAIN, 12));
			jLabel41 = new JLabel();
			jLabel41.setBounds(new Rectangle(65, 106, 430, 15));
			jLabel41.setText("Skús nazvať to, čo počuješ a zadať to do textového riadku");
			jLabel41.setFont(new Font("Dialog", Font.PLAIN, 12));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jLabel5, null);
			jContentPane.add(jLabel1, null);
			jContentPane.add(jLabel2, null);
			jContentPane.add(jLabel3, null);
			jContentPane.add(jLabel4, null);
			jContentPane.add(jLabel41, null);
			jContentPane.add(jLabel42, null);
			jContentPane.add(jLabel43, null);
			jContentPane.add(jLabel411, null);
			jContentPane.add(jLabel412, null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBounds(new Rectangle(25, 295, 76, 25));
			jButton1.setText("Pomoc");
			jButton1.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					
					JDialog jDialog = getPomocDialog();
					jDialog.setTitle("Pomoc");
					jDialog.pack();
					Point loc = getHlavneOkno().getLocation();
					loc.translate(20, 20);
					jDialog.setLocation(loc);
					jDialog.setVisible(true);
				
				
				}
			
			});
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton1;
	}

	/**
	 * This method initializes pomocButton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPomocButton2() {
		if (pomocButton2 == null) {
			pomocButton2 = new JButton();
			pomocButton2.setBounds(new Rectangle(87, 17, 77, 25));
			pomocButton2.setText("Pomoc");
			pomocButton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
					JDialog jDialog = getPomocDialog();
					jDialog.setTitle("Pomoc");
					jDialog.pack();
					Point loc = getHlavneOkno().getLocation();
					loc.translate(20, 20);
					jDialog.setLocation(loc);
					jDialog.setVisible(true);
				
				}
			});
		}
		return pomocButton2;
	}

	/**
	 * This method initializes prieskumButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPrieskumButton() {
		if (prieskumButton == null) {
			prieskumButton = new JButton();
			prieskumButton.setText("Prieskum");
			prieskumButton.setLocation(new Point(231, 295));
			prieskumButton.setEnabled(false);
			prieskumButton.setSize(new Dimension(99, 25));
			prieskumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mod = "p";
					ucivoText.setText("Rôzne zvukové ukážky");
					cvicenieText.setText("Prieskum");
					suzvukText.setText("súzvuk 1/10");
					spravne = true;
					potvrdil = false;

					analyzaPanel.setVisible(true);
					menuPanel.setVisible(false);
					spodnyPanel.setVisible(true);

					java.util.Random rand = new Random();
					priklad = new Dvojzvuk(rand.nextInt(49) + 36,5);

				}
			});
			prieskumButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return prieskumButton;
	}

	/**
	 * Spustenie aplikácie SluchAnal
	 */

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Aplikacia application = new Aplikacia();
				application.getHlavneOkno().setVisible(true);

				log = new LinkedList<String>();
				parser = new Parser();
				try {
					syntetizator = new Syntetizator(0, 0, 100,
							Syntetizator.DLHO, Syntetizator.NARAZ);
				} catch (ZvukovaChybaVynimka e) {
					log
							.addFirst("Pri štarte aplikácie došlo ku zvukovej chybe!");
				}

				priklad = new Dvojzvuk(1);
				/*
				 * java.util.Random rand = new Random(); int[] p; int r =
				 * rand.nextInt(3); // r = 2; switch (r) { case 0: priklad = new
				 * Dvojzvuk(rand.nextInt(49)+36,rand.nextInt(13)); break; case
				 * 1: p = new int[2]; while (p[0] == p[1]) { p[0] =
				 * rand.nextInt(13); p[1] = rand.nextInt(13); } Arrays.sort(p);
				 * priklad = new Trojzvuk(rand.nextInt(49)+36,p[0],p[1]); break;
				 * case 2: p = new int[3]; while ((p[0] == p[1]) || (p[1] ==
				 * p[2]) || (p[0] == p[2])) { p[0] = rand.nextInt(13); p[1] =
				 * rand.nextInt(13); p[2] = rand.nextInt(13); } Arrays.sort(p);
				 * priklad = new Stvorzvuk(rand.nextInt(49)+36,p[0],p[1],p[2]);
				 * break; }
				 */
			}
		});

	}

	/**
	 * This method initializes hlavneOkno
	 * 
	 * @return javax.swing.JFrame
	 */

	private JFrame getHlavneOkno() {
		if (hlavneOkno == null) {
			hlavneOkno = new JFrame();
			hlavneOkno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			hlavneOkno.setBackground(new Color(238, 238, 238));
			hlavneOkno.setResizable(true);
			hlavneOkno.setLocation(new Point(200, 100));
			hlavneOkno.setJMenuBar(getMenuLista());
			hlavneOkno.setSize(571, 551);
			hlavneOkno.setContentPane(getHlavneOknoObsah());
			hlavneOkno.setTitle("SluchAnal v1.0");
		}
		return hlavneOkno;
	}

	/* --- 1. INICIALIZAČNÉ METÓDY --- */

	/**
	 * This method initializes hlavneOknoObsah
	 * 
	 * @return javax.swing.JPanel
	 */

	private JPanel getHlavneOknoObsah() {
		if (hlavneOknoObsah == null) {
			suzvukText = new JLabel();
			suzvukText.setHorizontalAlignment(SwingConstants.CENTER);
			suzvukText.setBounds(new Rectangle(126, 83, 300, 25));
			suzvukText.setText("");
			cvicenieText = new JLabel();
			cvicenieText.setFont(new Font("Dialog", Font.BOLD, 18));
			cvicenieText.setHorizontalAlignment(SwingConstants.CENTER);
			cvicenieText.setBounds(new Rectangle(127, 18, 300, 40));
			cvicenieText.setText("Výcvik");
			hlavneOknoObsah = new JPanel();
			hlavneOknoObsah.setLayout(null);
			hlavneOknoObsah.add(getAnalyzaList(), null);
			hlavneOknoObsah.add(getSpodnyPanel(), null);
			hlavneOknoObsah.add(getAnalyzaPanel(), null);
			hlavneOknoObsah.add(getMenuPanel(), null);
		}
		return hlavneOknoObsah;
	}

	/**
	 * This method initializes menuLista
	 * 
	 * @return javax.swing.JMenuBar
	 */

	private JMenuBar getMenuLista() {
		if (menuLista == null) {
			menuLista = new JMenuBar();
			menuLista.add(getFileMenu());
			menuLista.add(getHelpMenu());
		}
		return menuLista;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */

	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("Súbor");
			//fileMenu.add(getSaveMenuItem());
			fileMenu.add(getExitMenuItem());
			fileMenu.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu
	 * 
	 * @return javax.swing.JMenu
	 */

	private JMenu getHelpMenu() {
		if (helpMenu == null) {
			helpMenu = new JMenu();
			helpMenu.setText("Pomoc");
			helpMenu.add(getAboutMenuItem());
		}
		return helpMenu;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */

	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */

	private JMenuItem getAboutMenuItem() {
		if (aboutMenuItem == null) {
			aboutMenuItem = new JMenuItem();
			aboutMenuItem.setText("O aplikácii SluchAnal");
			aboutMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JDialog aboutDialog = getAboutDialog();
					aboutDialog.pack();
					Point loc = getHlavneOkno().getLocation();
					loc.translate(20, 20);
					aboutDialog.setLocation(loc);
					aboutDialog.setVisible(true);
				}
			});
		}
		return aboutMenuItem;
	}

	/**
	 * This method initializes aboutDialog
	 * 
	 * @return javax.swing.JDialog
	 */

	private JDialog getAboutDialog() {
		if (aboutDialog == null) {
			aboutDialog = new JDialog(getHlavneOkno(), true);
			aboutDialog.setTitle("O aplikácii SluchAnal");
			aboutDialog.setSize(new Dimension(100, 100));
			aboutDialog.setResizable(false);
			aboutDialog.setContentPane(getAboutObsah());
		}
		return aboutDialog;
	}

	/**
	 * This method initializes aboutObsah
	 * 
	 * @return javax.swing.JPanel
	 */

	private JPanel getAboutObsah() {
		if (aboutObsah == null) {
			aboutCopyright = new JLabel();
			aboutCopyright.setText("‎© Ladislav Maršík, FMFI 2010");
			aboutCopyright.setName("aboutCopyright");
			aboutCopyright.setHorizontalAlignment(SwingConstants.CENTER);
			aboutVerzia = new JLabel();
			aboutVerzia.setText("Verzia 1.0");
			aboutVerzia.setName("aboutVerzia");
			aboutVerzia.setHorizontalAlignment(SwingConstants.CENTER);
			aboutObsah = new JPanel();
			aboutObsah.setLayout(new BorderLayout());
			aboutObsah.setPreferredSize(new Dimension(200, 100));
			aboutObsah.add(aboutVerzia, BorderLayout.CENTER);
			aboutObsah.add(aboutCopyright, BorderLayout.SOUTH);
		}

		return aboutObsah;
	}

	/**
	 * This method initializes jMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */

	

}
