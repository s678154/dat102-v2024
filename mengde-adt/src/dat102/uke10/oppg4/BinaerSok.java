package dat102.uke10.oppg4;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

public class BinaerSok {

	public static void main(String[] args) {
		
		// Lager en tabell med 10 000 random tall:
		int[] randomtallTabell = genererRandomTabell();
		
		// Oppretter tabell og HashSet med 100 000 elementer:
		int tall = 376; 
		
		HashSet<Integer> hashSet = new HashSet<>();
		Integer[] tabell = new Integer[100000];
		
		for (int i = 0; i < 100000; i++){
			int t = tall;
	        tall = (tall + 45713) % 1000000;
	        hashSet.add(t);
	        tabell[i] = t;
		}
		
	//Måle tiden å søke i HashSet:
		long startTidHashSet = System.currentTimeMillis();
		// Søker i HashSet:
				int tellerHashSet = 0;
				for (int e : randomtallTabell) {
					for(Integer i : hashSet) {
						if (e == i) {
							tellerHashSet++;
							break;
						}
					}
				}
        long sluttTidHashSet = System.currentTimeMillis();
        long bruktTidHashSet = sluttTidHashSet - startTidHashSet;
        
   //Måle tiden å søke i tabell:
        long startTidTabell = System.currentTimeMillis();
        // Sorterer tabellen:
     	Arrays.sort(tabell);
        // Søker i tabell:
     		int tellerTabell = 0;
     		
     		for (int e : randomtallTabell) {
                 int resultat = Arrays.binarySearch(tabell, e);
                 
                 if (resultat >= 0) {
         			tellerTabell++;
         		}
     		}
        long sluttTidTabell = System.currentTimeMillis();
        long brukttidTabell = sluttTidTabell - startTidTabell;
        
        System.out.println("Antall ganger tallet ble funnet i HashSet: " + tellerHashSet);
        System.out.println("Tid brukt på søk i HashSet: " + bruktTidHashSet + " ms");
       
        System.out.println("Antall ganger tallet ble funnet i sortert tabell: " + tellerTabell);
        System.out.println("Tid brukt på søk i sortert tabell: " + brukttidTabell + " ms");
		
	}	

	
	public static int[] genererRandomTabell() {
		int[] tab = new int[10000];
		Random r = new Random();
		for (int i = 0 ; i < 10000 ; i++) {
			int randomTall = r.nextInt(10000);
			tab[i] = randomTall;
		}
		
		return tab;
	}
}
