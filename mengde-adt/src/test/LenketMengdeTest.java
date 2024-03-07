package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dat102.uke89.oppg1_mengder.LenketMengde;

class LenketMengdeTest {

	private LenketMengde<String> mengde1, mengde2, mengde3, mengde4;
	
	@BeforeEach
	void setUp() throws Exception {
		mengde1 = new LenketMengde<>(); //tom mengde
		
		mengde2 = new LenketMengde<>();
		mengde2.leggTil("Java");
		
		mengde3 = new LenketMengde<>();
		mengde3.leggTil("Java");
		mengde3.leggTil("Per");
		
		mengde4 = new LenketMengde<>();
		mengde4.leggTil("Java");

	}

//	Metoder å teste: erTom, inneholder, erDelmengdeAv, erLik, snitt, union, 
//	minus, leggTilAlleFra, fjern, tilTabell, antallElementer
	
	@Test
	void erTomTest() {
		assertTrue(mengde1.erTom());
	}
	
	@Test 
	void inneholderTest() {
		assertTrue(mengde2.inneholder("Java"));
		assertFalse(mengde2.inneholder("Per"));
	}
	 
	@Test
	void erDelMengdeAvTest() {
		assertTrue(mengde2.erDelmengdeAv(mengde3));
		assertFalse(mengde3.erDelmengdeAv(mengde2));
	} 
	
	@Test
	void erLik() {
		assertFalse(mengde4.erLik(mengde1));
		assertFalse(mengde3.erLik(mengde2));
	}
	
	@Test
	void snittTest() {
		assertEquals(1, mengde2.snitt(mengde3).antallElementer());
        assertTrue(mengde2.snitt(mengde3).inneholder("Java"));
        
        assertEquals(1, mengde3.snitt(mengde4).antallElementer());
        assertTrue(mengde3.snitt(mengde4).inneholder("Java"));
        
        assertEquals(0, mengde2.snitt(mengde1).antallElementer());
	}
	
	@Test
	void unionTest() {
		assertEquals(1, mengde1.union(mengde2).antallElementer());
        assertTrue(mengde1.union(mengde2).inneholder("Java"));
        
        assertEquals(2, mengde2.union(mengde3).antallElementer());
        assertTrue(mengde2.union(mengde3).inneholder("Java"));
        assertTrue(mengde2.union(mengde3).inneholder("Per"));
        
        assertEquals(2, mengde3.union(mengde4).antallElementer());
        assertTrue(mengde3.union(mengde4).inneholder("Java"));
        assertTrue(mengde3.union(mengde4).inneholder("Per"));
	}
	
	@Test
	void minusTest() {
		assertTrue(mengde1.minus(mengde2).erTom());
		assertTrue(mengde2.minus(mengde3).erTom());
		assertEquals(1, mengde3.minus(mengde4).antallElementer());
        assertTrue(mengde3.minus(mengde4).inneholder("Per"));

	}

	@Test
	void leggTilAlleFraTest() { //her tester vi samtidig om tilTabell funker som den skal
		mengde1.leggTilAlleFra(mengde2);
        assertEquals(1, mengde1.antallElementer());
        
        mengde2.leggTilAlleFra(mengde3);
        assertEquals(2, mengde2.antallElementer());
        assertTrue(mengde2.inneholder("Java"));
        assertTrue(mengde2.inneholder("Per"));
	}
	
	@Test
	void fjernTest() {
		mengde2.fjern("Java");
		assertEquals(0, mengde2.antallElementer());
		
		mengde3.fjern("Per");
		assertEquals(1, mengde3.antallElementer());
	}


}
