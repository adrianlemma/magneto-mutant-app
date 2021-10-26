package com.magneto.mutant.models;

public class Metrics {

	private int count_mutant_dna;
	private int count_human_dna;
	private float ratio;
	
	// Constructor
	public Metrics(int count_mutant_dna, int count_human_dna) {
		super();
		this.count_mutant_dna = count_mutant_dna;
		this.count_human_dna = count_human_dna;
		this.ratio = calculateRatio(count_mutant_dna, count_human_dna);
	}

	// Getters and Setters
	public int getCount_mutant_dna() {
		return count_mutant_dna;
	}

	public void setCount_mutant_dna(int count_mutant_dna) {
		this.count_mutant_dna = count_mutant_dna;
		this.ratio = calculateRatio(count_mutant_dna, this.count_human_dna);
	}

	public int getCount_human_dna() {
		return count_human_dna;
	}

	public void setCount_human_dna(int count_human_dna) {
		this.count_human_dna = count_human_dna;
		this.ratio = calculateRatio(this.count_mutant_dna, count_human_dna);
	}

	public float getRatio() {
		return ratio;
	}
	
	/***
	 * Se calcula el ratio de mutantes entre humanos
	 * @param mutant
	 * @param human
	 * @return float (mutant / human)
	 */
	private float calculateRatio(int mutant, int human) {
		if(count_human_dna > 0)
			return (float)count_mutant_dna / (float)count_human_dna;

		return 0;
	}
	
}
