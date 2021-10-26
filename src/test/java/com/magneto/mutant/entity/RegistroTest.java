package com.magneto.mutant.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RegistroTest {

	@Test
	void test() {

		// Constructores, Setters y Getters faltantes
		Registro reg = new Registro();
		reg.setIdDna(1);
		reg.setDna("AAAAGGGGCCCCTTTT");
		reg.setN(4);
		reg.setType("mutant");
		Assertions.assertEquals(reg.getIdDna(), 1);
		Assertions.assertEquals(reg.getDna(), "AAAAGGGGCCCCTTTT");
		Assertions.assertEquals(reg.getN(), 4);
		Assertions.assertEquals(reg.getType(), "mutant");
	}

}
