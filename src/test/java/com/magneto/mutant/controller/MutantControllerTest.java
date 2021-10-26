package com.magneto.mutant.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.magneto.mutant.entity.Registro;
import com.magneto.mutant.models.Dna;
import com.magneto.mutant.models.Metrics;
import com.magneto.mutant.repository.impl.RepositoryServiceImpl;

class MutantControllerTest {

	@Autowired
	Metrics metrics;
	
	@Autowired
	Dna dna;
	
	RepositoryServiceImpl service = Mockito.mock(RepositoryServiceImpl.class);
	
	@Autowired
	MutantController mutantController = new MutantController(service);
	
	@Test
	void testGetMetrics() {
		when(service.countMutants()).thenReturn(5);
		when(service.countHumans()).thenReturn(10);
		Metrics metricsMock = mutantController.getMetrics();
		Assertions.assertEquals(5, metricsMock.getCount_mutant_dna());
		Assertions.assertEquals(10, metricsMock.getCount_human_dna());
		Assertions.assertEquals((float)0.5, metricsMock.getRatio());
	}

	@Test
	void testMutant() {
		mutantController = new MutantController(service);
		
		ResponseEntity<String> mutantOk = new ResponseEntity<>("200 - OK", HttpStatus.OK);
		ResponseEntity<String> humanOk = new ResponseEntity<>("403 - FORBIDDEN", HttpStatus.FORBIDDEN);
		ResponseEntity<String> duplicated = new ResponseEntity<>("409 - DNA YA INGRESADO", HttpStatus.CONFLICT);
		//ResponseEntity<String> error = new ResponseEntity<>("500 - ERROR INTERNO", HttpStatus.INTERNAL_SERVER_ERROR);
		ResponseEntity<String> invalid = new ResponseEntity<>("400 - FORMATO DNA INVALIDO", HttpStatus.BAD_REQUEST);

		String[] stringMutant = {"AAAA","CCCC","GGGG","TTTT"};
		String[] stringNoMutant = {"ACAC","GTGT","ACAC","GTGT"};
		Dna dna = new Dna(stringMutant);
		Registro reg = new Registro(dna);
		Registro regSalida = new Registro(dna);
		regSalida.setIdDna(5);
		
		// Prueba insert mutant
		when(service.yaRegistrado(reg.getDna())).thenReturn(false);
		when(service.save(reg)).thenReturn(regSalida);
		Assertions.assertEquals(mutantOk, mutantController.mutant(dna));
		
		// Prueba insert human
		dna.setDna(stringNoMutant);
		Assertions.assertEquals(humanOk, mutantController.mutant(dna));
		
		// Prueba formato invalido
		dna = new Dna();
		Assertions.assertEquals(invalid, mutantController.mutant(dna));
		
		// Prueba error no se hace porque no puedo forzar ese error
		//reg.setType("error");
		//dna = new Dna();
		//Assertions.assertEquals(error, mutantController.mutant(dna));

		// Prueba registro duplicado
		dna.setDna(stringMutant);
		when(service.yaRegistrado(reg.getDna())).thenReturn(true);
		Assertions.assertEquals(duplicated, mutantController.mutant(dna));
		
		dna.setDna(stringMutant);
		
	}

}
