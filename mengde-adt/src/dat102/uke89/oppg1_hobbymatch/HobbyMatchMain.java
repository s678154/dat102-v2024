package dat102.uke89.oppg1_hobbymatch;

import java.util.HashSet;
import java.util.Set;

public class HobbyMatchMain {


	public static double match(Person a, Person b) {
		// Lager et nytt Set og legger til hobbyene som er felles  
		Set<String> felles = new HashSet<>(a.getHobbyer());
	    felles.retainAll(b.getHobbyer());
	     
	    // Beregner antallFelles, antallKunHosDenEne, antallKunHosDenAndre og antallTotalt
	    int antallFelles = felles.size();
        int antallKunHosDenEne = a.getHobbyer().size() - antallFelles;
        int antallKunHosDenAndre = b.getHobbyer().size() - antallFelles;
        int antallTotalt = antallFelles + antallKunHosDenEne + antallKunHosDenAndre;
        
        // Beregner match 
        return antallFelles - (antallKunHosDenEne + antallKunHosDenAndre) / (double) antallTotalt;
	}
	
	public static void main(String[] args) {
		 Person p1 = new Person("P1", "jakt", "sykling", "venner", "data");
		 Person p2 = new Person("P2", "venner", "data", "jogging");
		 Person p3 = new Person("P3", "jogging", "sykling", "biler");
		 
		 double matchP1OgP2 = match(p1, p2);
		 double matchP2OgP3 = match(p2, p3);
		 double matchP3OgP2 = match(p3, p2);
		 double matchP1OgP1 = match(p1, p1);
		 
		 System.out.printf("Match score mellom %s og %s er: %.2f", p1.getNavn(), p2.getNavn(),matchP1OgP2);
		 System.out.println();
		 System.out.printf("Match score mellom %s og %s er: %.2f", p2.getNavn(), p3.getNavn(),matchP2OgP3);
		 System.out.println();
		 System.out.printf("Match score mellom %s og %s er: %.2f", p3.getNavn(), p2.getNavn(),matchP3OgP2);
		 System.out.println();
		 System.out.printf("Match score mellom %s og %s er: %.2f", p1.getNavn(), p1.getNavn(),matchP1OgP1);

	}
	
	
}
