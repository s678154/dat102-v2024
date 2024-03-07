package dat102.uke89.oppg1_mengder;

import java.util.Arrays;

public class TabellMengde<T> implements MengdeADT<T> {

	private static final int DEFAULT_KAPASITET = 10;

	private T[] mengde;
	private int antall;
	
	public TabellMengde() {
		this(DEFAULT_KAPASITET);
	}
	
	@SuppressWarnings("unchecked")
	public TabellMengde(int kapasitet) {
		mengde = (T[]) new Object[kapasitet];
		antall = 0;
	}
	
	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {		
		for(int i = 0 ; i < mengde.length ; i++) {
			T e = mengde[i];
			if (element == null) {
				if (e == null) {
					return true;
				}
			}else if (element.equals(e)) {
	            return true;
			}
		}
		return false;
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for(int i = 0; i < mengde.length ; i++) {
			T element = mengde[i];
			if(!annenMengde.inneholder(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		return mengde.equals(annenMengde);
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for(int i = 0; i < mengde.length ; i++) {
			T element = mengde[i];
			if (annenMengde.inneholder(element)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> nymengde = new TabellMengde<T>();
		
		for (int i = 0 ; i < mengde.length ; i++) {
			T element = mengde[i];
			if (annenMengde.inneholder(element)) {
				nymengde.leggTil(element);
			}
		}
		return nymengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> nymengde = new TabellMengde<T>();
		
		
		nymengde.leggTilAlleFra(annenMengde);
		
		// Legger til de unike elementene i mengde   
		for (int i = 0 ; i < mengde.length ; i++) {
			T element = mengde[i];
			if (!nymengde.inneholder(element)) {
				nymengde.leggTil(element);
			}
		}
		return nymengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		
		MengdeADT<T> nymengde = new TabellMengde<T>();
		
		for (int i = 0 ; i < mengde.length ; i++) {
			T element = mengde[i];
			if (!annenMengde.inneholder(element)) {
				nymengde.leggTil(element);
			}
		}
		
		return nymengde;
	}

	@Override
	public void leggTil(T element) {
		//Sjekker at elementet ikke allerede er i mengden
		if (!inneholder(element)) {
			if (antall == mengde.length) {
				utvidMengde();
			}
			mengde[antall] = element;
	        antall++;
		}
		
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] tabell = annenMengde.tilTabell();
		for (int i = 0; i < tabell.length; i++) {
	        T element = tabell[i];
	        if (!inneholder(element)) {
	            leggTil(element);
	        }
		}
	}

	@Override
	public T fjern(T element) {
		if (inneholder(element)) {
			for (int i = 0 ; i < mengde.length ; i++) {
				if (mengde[i].equals(element)) {
					T e = mengde[i];
					mengde[i] = null;
					antall--;
					return e;
				}
			}
		}
		
		return null;
	}

	@Override
	public T[] tilTabell() {
		return Arrays.copyOf(mengde, antall);
	}

	@Override
	public int antallElementer() {
		return antall;
	}
	
	// En hjelpemetode for å utvide størrelsen på mengden ved behov
	private void utvidMengde() {
	    int nyKapasitet = mengde.length * 2;
	    mengde = Arrays.copyOf(mengde, nyKapasitet);
	}
	
	

	
	
}
