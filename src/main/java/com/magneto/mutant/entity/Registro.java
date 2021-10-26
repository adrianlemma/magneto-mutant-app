package com.magneto.mutant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.magneto.mutant.models.Dna;

@Entity
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDna;
	
	@Column(unique = true)
	@NotNull
	private String dna;
	
	@Column
	@NotNull
	private String type;
	
	@Column
	@NotNull
	private int n;
	
	// Constructors
	public Registro() { }
	
	public Registro(Dna dna) {
		this.dna = dna.getDnaString();
		this.type = dna.getType();
		this.n = dna.getN();
	}
	
	
	// Getters and Setters
	public int getIdDna() {
		return idDna;
	}

	public void setIdDna(int idDna) {
		this.idDna = idDna;
	}

	public String getDna() {
		return dna;
	}

	public void setDna(String dna) {
		this.dna = dna;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}