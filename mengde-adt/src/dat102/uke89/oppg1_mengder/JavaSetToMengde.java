package dat102.uke89.oppg1_mengder;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JavaSetToMengde<T> implements MengdeADT<T> {

	private Set<T> mengde;
	
	public JavaSetToMengde() {
        mengde = new HashSet<>(); 
        //bruker HashSet, fordi Set er et grensesnitt og det ikke går an å lage objekter direkte fra denne
    }

	@Override
	public boolean erTom() {
		return mengde.isEmpty();
	}

	@Override
	public boolean inneholder(T element) {
		return mengde.contains(element);
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
//		Set<T> annenJavaSet = new HashSet<>(annenMengde.tilTabell());
//		Her fikk jeg et typeinferanseproblem når jeg ikke spesifiserte typen til HashSettet
		
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
		return annenmengde.containsAll(mengde);
        
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
		return mengde.equals(annenmengde);	
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
	    return Collections.disjoint(mengde, annenmengde);		
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
		
		Set<T> snittSet = new HashSet<>(mengde);
	    snittSet.retainAll(annenmengde);
	    
	    JavaSetToMengde<T> snittMengde = new JavaSetToMengde<>();
	    for (T element : snittSet) {
	        snittMengde.leggTil(element);
	    }
	    
	    return snittMengde;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
		
		Set<T> unionSet = new HashSet<>(mengde);
		unionSet.addAll(annenmengde);
		
	    JavaSetToMengde<T> unionMengde = new JavaSetToMengde<>();
	    for (T element : unionSet) {
	        unionMengde.leggTil(element);
	    }
	    
	    return unionMengde;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		Set<T> annenmengde = new HashSet<>(Arrays.asList(annenMengde.tilTabell()));
		
		Set<T> minusSet = new HashSet<>(mengde);		
		minusSet.removeAll(annenmengde);
		
		JavaSetToMengde<T> minusMengde = new JavaSetToMengde<>();
	    
	    for (T element : minusSet) {
	        minusMengde.leggTil(element);
	    }

	    return minusMengde;
	}

	@Override
	public void leggTil(T element) {
		mengde.add(element);
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
//		mengde.addAll(annenMengde.tilTabell());
	    mengde.addAll(Arrays.asList(annenMengde.tilTabell())); 
	    //addAll forventer en Collection. Arrays.asList-metoden konverterer arrayet til en List, som implementerer Collection.

	}

	@Override
	public T fjern(T element) {
		mengde.remove(element);
		return element;
	}

	@Override
	public T[] tilTabell() {
		return (T[]) mengde.toArray();
	}

	@Override
	public int antallElementer() {
		return mengde.size();
	}
	
	
	
	
	
	
}
