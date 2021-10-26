package com.magneto.mutant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magneto.mutant.entity.Registro;
import com.magneto.mutant.models.Dna;
import com.magneto.mutant.models.Metrics;
import com.magneto.mutant.repository.impl.RepositoryServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class MutantController {
	
	@Autowired
	private RepositoryServiceImpl service;

	// Constructor que recive el servicio, solo para test con Mockito
	public MutantController(RepositoryServiceImpl service2) {
		this.service = service2;
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("/stats")
	public Metrics getMetrics() {
		return new Metrics(service.countMutants(), service.countHumans());
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/mutant")
	public ResponseEntity<String> mutant(@RequestBody Dna dna) {
		
		if(dna.getDna() == null)
			return new ResponseEntity<>("400 - FORMATO DNA INVALIDO", HttpStatus.BAD_REQUEST);
		
		Registro reg = new Registro(dna);

		if(service.yaRegistrado(reg.getDna()))  	// DUPLICADO
			 return new ResponseEntity<>("409 - DNA YA INGRESADO", HttpStatus.CONFLICT);

		reg = service.save(reg);

		if(dna.getType().equals("mutant"))			// MUTANT
			return new ResponseEntity<>("200 - OK", HttpStatus.OK);
		else if(dna.getType().equals("no mutant")) 	// HUMAN
			return new ResponseEntity<>("403 - FORBIDDEN", HttpStatus.FORBIDDEN);
		else
			return new ResponseEntity<>("500 - ERROR INTERNO", HttpStatus.INTERNAL_SERVER_ERROR);

		
	}
}