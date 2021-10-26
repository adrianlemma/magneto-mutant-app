package com.magneto.mutant.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DnaTest {

	String[] dnaM = {"AAAA","CCCC","GGGG","TTTT"};
	String[] dnaNM = {"ACGTA","ACGTC","GTACG","GTACT","TTCCG"};
	String[] dnaInvalidFormat = {"AAAA","CCCC","GGGG","TT"};
	String[] dnaInvalidChars = {"AAAA","CCCC","GGGG","ZZZZ"};
	Dna dnaNoMutante = new Dna(dnaNM);
	Dna dnaMutante = new Dna(dnaM);
	
	@Test
	void testDnaStringArray() {
		Dna dnaOK = new Dna(dnaM);
		Dna dnaInvalido1 = new Dna(dnaInvalidFormat);
		Dna dnaInvalido2 = new Dna(dnaInvalidChars);
		Assertions.assertNotNull(dnaOK);
		Assertions.assertNull(dnaInvalido1.getDna());
		Assertions.assertNull(dnaInvalido2.getDna());
	}

	@Test
	void testDna() {
		Dna aux = new Dna();
		Assertions.assertNotNull(aux);
	}

	@Test
	void testGetDna() {
		Assertions.assertEquals(dnaM, dnaMutante.getDna());
		Assertions.assertEquals(dnaNM, dnaNoMutante.getDna());
	}

	@Test
	void testSetDna() {
		String[] auxM = {"aaaa","aaaa","aaaa","aaaa"};
		dnaMutante.setDna(auxM);
		Assertions.assertEquals("AAAAAAAAAAAAAAAA",dnaMutante.getDnaString());
	}

	@Test
	void testGetN() {
		Assertions.assertEquals(4, dnaMutante.getN());
		Assertions.assertEquals(5, dnaNoMutante.getN());
	}

	@Test
	void testGetType() {
		Assertions.assertEquals("mutant", dnaMutante.getType());
		Assertions.assertEquals("no mutant", dnaNoMutante.getType());
	}

	@Test
	void testGetDnaString() {
		Assertions.assertEquals("AAAACCCCGGGGTTTT", dnaMutante.getDnaString());
		Assertions.assertEquals("ACGTAACGTCGTACGGTACTTTCCG", dnaNoMutante.getDnaString());
	}

}
