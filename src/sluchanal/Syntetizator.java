package sluchanal;

import javax.sound.midi.*;
import java.io.File;

public class Syntetizator {

	public static Sequencer sekvencer;
	public static Synthesizer syntetizator;
	public static MidiChannel[] kanaly; 
	public static Instrument[] nastroje;
	private int nastroj = 0;
	private int kanal = 0;
	private int hlasitost = 100;
	
	public static final int DLHO = 0;
	public static final int KRATKO = 1;
	
	private int dlzka = 0;
	
	public static final int NARAZ = 0;
	public static final int ODDELENE = 1;
	
	private int typHry = 0;
	
	
	public static void main(String[] args) {
		try {
			Syntetizator s = new Syntetizator();
			s.hraj1(60);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getNastroj() {
		return nastroj;
	}
	
	public void setNastroj(int nastroj) {
		this.nastroj = nastroj;
	}
	
	public int getKanal() {
		return kanal;
	}
	
	public void setKanal(int kanal) {
		this.kanal = kanal;
	}
	
	public int getHlasitost() {
		return hlasitost;
	}
	
	public void setHlasitost(int hlasitost) {
		this.hlasitost = hlasitost;
	}
	
	public int getDlzka() {
		return dlzka;
	}
	
	public void setDlzka(int dlzka) {
		this.dlzka = dlzka;
	}
	
	public int getTypHry() {
		return typHry;
	}
	
	public void setTypHry(int typHry) {
		this.typHry = typHry;
	}
	
	Syntetizator() throws ZvukovaChybaVynimka {
		try {
			sekvencer = MidiSystem.getSequencer();
			syntetizator = MidiSystem.getSynthesizer();
			syntetizator.open();
			kanaly = syntetizator.getChannels();
			nastroje = syntetizator.getDefaultSoundbank().getInstruments();
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI nie sú prístupné.");
			throw new ZvukovaChybaVynimka();
		}
	}
	
	Syntetizator(int nastroj,int kanal,int hlasitost,int dlzka,int typHry) throws ZvukovaChybaVynimka {
		try {
			sekvencer = MidiSystem.getSequencer();
			syntetizator = MidiSystem.getSynthesizer();
			syntetizator.open();
			kanaly = syntetizator.getChannels();
			nastroje = syntetizator.getDefaultSoundbank().getInstruments();
			setNastroj(nastroj);
			setKanal(kanal);
			setHlasitost(hlasitost);
			setDlzka(dlzka);
			setTypHry(typHry);
		} catch (MidiUnavailableException e) {
			System.out.println("MIDI nie sú prístupné.");
			throw new ZvukovaChybaVynimka();
		}
	}
	
	public void zatvor() {
		if (syntetizator.isOpen()) {
			syntetizator.close();
		}
		if (sekvencer.isOpen()) {
			sekvencer.close();
		}
	}
	
	public void hraj(Suzvuk suzvuk) {
		if (suzvuk instanceof Dvojzvuk) {
			hraj2((Dvojzvuk) suzvuk);
		} else if (suzvuk instanceof Trojzvuk) {
			hraj3((Trojzvuk) suzvuk);
		} else if (suzvuk instanceof Stvorzvuk) {
			hraj4((Stvorzvuk) suzvuk);
		}
	}
	
	public void hraj1(int vyska) {
		syntetizator.loadInstrument(nastroje[nastroj]);
		
		kanaly[kanal].noteOn(vyska,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska);
		}
	}
	
	public void hraj2(int vyska1,int vyska2) {
		syntetizator.loadInstrument(nastroje[nastroj]);
		
		kanaly[kanal].noteOn(vyska1,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska1);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska2,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska2);
		}
	}
	
	public void hraj2(Dvojzvuk dvojzvuk) {
		hraj2(dvojzvuk.getVyska(),dvojzvuk.getVyska()+dvojzvuk.getPocet());
	}
	
	public void hraj3(int vyska1,int vyska2,int vyska3) {
		syntetizator.loadInstrument(nastroje[nastroj]);
		
		kanaly[kanal].noteOn(vyska1,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska1);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska2,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska2);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska3,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska3);
		}
	}
	
	public void hraj3(Trojzvuk trojzvuk) {
		hraj3(trojzvuk.getVyska(),trojzvuk.getVyska()+trojzvuk.getPocet1(),trojzvuk.getVyska()+trojzvuk.getPocet2());
	}
	
	public void hraj4(int vyska1,int vyska2,int vyska3,int vyska4) {
		syntetizator.loadInstrument(nastroje[nastroj]);
		
		kanaly[kanal].noteOn(vyska1,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska1);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska2,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska2);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska3,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska3);
		}
		if (typHry == ODDELENE) {
			try { 
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		kanaly[kanal].noteOn(vyska4,hlasitost);
		if (dlzka == KRATKO) {
			kanaly[kanal].noteOff(vyska4);
		}	
	}
	
	public void hraj4(Stvorzvuk stvorzvuk) {
		hraj4(stvorzvuk.getVyska(),stvorzvuk.getVyska()+stvorzvuk.getPocet1(),stvorzvuk.getVyska()+stvorzvuk.getPocet2(),stvorzvuk.getVyska()+stvorzvuk.getPocet3());
	}
	
	public void hrajMidi(String vstup) throws ZvukovaChybaVynimka {
		try {
			File subor = new File(vstup);
			Sequence sekvencia = MidiSystem.getSequence(subor);
			sekvencer.setSequence(sekvencia); 
			sekvencer.start();
		} catch (Exception e) {
			throw new ZvukovaChybaVynimka();
		}
	}
}

class ZvukovaChybaVynimka extends Exception {

	private static final long serialVersionUID = 1L;
	
	public String getMessage() {
		return "Vyskytla sa zvuková chyba!"; 
	}
}
