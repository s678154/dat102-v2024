package dat102.uke89.oppg1_mengder;

public class LenketMengde<T> implements MengdeADT<T>{
	
	private Node forste;
	private int antall;
	
	public LenketMengde() {
		forste = null;
		antall = 0;
	}
	
	
	@Override
	public boolean erTom() {
		return antall == 0;
	}


	@Override
	public boolean inneholder(T element) {
		
		Node temp = forste;
		while(temp != null) {
			if (temp.data.equals(element)) {
				return true;
			}
			temp = temp.neste;
		}
		
		return false;
	}


	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Node temp = forste;
		
		while (temp != null) {
			if (!annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;	
	}


	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		// Sjekk om mengdene har samme antall elementer
	    if (antallElementer() != annenMengde.antallElementer()) {
	        return false;
	    }
	    
	    Node temp = forste;
	    while (temp != null) {
	        if (!annenMengde.inneholder(temp.data)) {
	            return false;
	        }
	        temp = temp.neste;
	    }

	    return true;
	    
	}	    


	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Node temp = forste;
		while (temp != null) {
			if(annenMengde.inneholder(temp.data)) {
				return false;
			}
			temp = temp.neste;
		}
		return true;
	}


	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		MengdeADT<T> nymengde = new LenketMengde<T>();
		
		Node temp = forste;
		while (temp != null) {
			if (annenMengde.inneholder(temp.data)) {
				nymengde.leggTil(temp.data);
			}
			temp = temp.neste;	
		}
		return nymengde;
	}


	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		MengdeADT<T> nymengde = new LenketMengde<T>();
		
		nymengde.leggTilAlleFra(annenMengde);
		
		// Legger til de unike elementene i mengde   
		Node temp = forste;
		while (temp != null) {
			if(!nymengde.inneholder(temp.data)) {
				nymengde.leggTil(temp.data);
			}
			temp = temp.neste;
		}
		return nymengde;
	}


	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		MengdeADT<T> nymengde = new LenketMengde<T>();
		
		Node temp = forste;
		while (temp != null) {
			if (!annenMengde.inneholder(temp.data)) {
				nymengde.leggTil(temp.data);
			}
			temp = temp.neste;
		}
		return nymengde;
	}


	@Override
	public void leggTil(T element) {
		//Legger til først i lenken
		Node ny = new Node(element);
		ny.neste = forste;
		forste = ny;
		antall++;	
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
			Node temp = forste;
			Node forrige = null;
			
			while(temp != null) {
				if (element.equals(temp.data)) {
					T e = temp.data;
					 
					// om det er første noden som skal fjernes
	                if (forrige == null) {
	                    forste = temp.neste;
	                } else { 
	                    forrige.neste = temp.neste;
	                }
	                
					antall--;
					return e;
				}
				forrige = temp;
				temp = temp.neste;
			}			
		}
		return null;
	}


	@Override
	public T[] tilTabell() {
		T[] tabell = (T[]) new Object[antall];
	    Node temp = forste;
	    int indeks = 0;

	    while (temp != null) {
	        tabell[indeks] = temp.data;
	        temp = temp.neste;
	        indeks++;
	    }

	    return tabell;
	}


	@Override
	public int antallElementer() {
		return antall;
	}

	
//	--------------------------------------------------
	
private class Node {
		
		private T data;
		private Node neste;

		private Node(T data) {
			this.data = data;
			this.neste = null;
		}
	}

}




