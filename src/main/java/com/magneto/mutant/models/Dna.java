package com.magneto.mutant.models;

public class Dna {

	private String[] dna;
	
	// Constructor
	public Dna(String[] dna) {
		super();
		setDna(dna);
	}
	
	public Dna() {
		super();
	}

	// Getters and Setters
	public String[] getDna() {
		return dna;
	}

	public void setDna(String[] dna) {
		if(!isValidDna(dna)) {
			this.dna = null;
			return;
		}
		for(int i = 0; i < dna.length; i++) {
			dna[i] = dna[i].toUpperCase();
		}
		this.dna = dna;
	}

	public int getN() {
		return dna.length;
	}
	
	public String getType() {
		return isMutant(dna);
	}
	
	public String getDnaString() {
		
		String aux = "";
		for(int i = 0; i < dna.length; i++) {
			aux = aux + dna[i];
		}
		return aux;
	}
	
	
	/***
	 * Valida si el DNA ingresado es una matriz cuadrada de minimo 4x4
	 * También valida que solo contenga los caracteres validos {A,C,G,T}
	 * 
	 * @param String[] dna 
	 * @return boolean
	 */
	private boolean isValidDna(String[] dna) {
		for (String linea: dna) {
			linea = linea.toUpperCase();
			for(int i = 0; i < linea.length(); i++) {
				if(linea.charAt(i) != 'A' && linea.charAt(i) != 'C'
					&& linea.charAt(i) != 'G' && linea.charAt(i) != 'T') {
					return Boolean.FALSE;
				}
			}
			if(linea.length() != dna.length || linea.length() < 4) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
	
	
	/***
	 * Valida si el DNA es de un mutante o un homano
	 * Se guardan en Strings las filas, colmnas y diagonales
	 * Y se valida cuntas veces contienen 4 caracteres consecutivos iguales
	 * 
	 * @param String[] dna
	 * @return String {"human" o "mutant"}
	 */
	private String isMutant(String[] dna) {
		String aux = "";
		int alto, ancho, contador = 0;
		
		for(int i = 0; i < dna.length; i++) {
			// Busqueda en Horizontal
			contador += contarSecuencias(dna[i]);
			
			// Busqueda Vertial
			for(int j = 0; j < dna.length; j++) {
				aux = aux + dna[j].charAt(i);
			}
			contador += contarSecuencias(aux);
			aux = "";
		}
		
		// Busqueda Diagonal
		alto = ancho = dna.length;
		aux = "";
		String aux2 = "";
		for ( int diag = 4 - ancho; diag <= alto - 4; diag++) {
			for (int vertical = Math.max(0, diag), horizontal = -Math.min(0, diag);
			  vertical < alto && horizontal < ancho; vertical++, horizontal++) {
				
				aux2 = aux2 + dna[vertical].charAt(ancho - horizontal - 1);
				aux = aux + dna[vertical].charAt(horizontal);
			}
			contador += contarSecuencias(aux);
			contador += contarSecuencias(aux2);
			aux = "";
			aux2 = "";
		}
		//System.out.println("CANTIDAD DE SECUENCIAS DE 4 O MAS: " + Integer.toString(contador));
		if(contador > 1)
			return "mutant";
		else
			return "no mutant";
	}
	
	
	/***
	 * Cuenta cuantas secuencias de 4 o mas letras consecutivas e iguales
	 * Hay en una linea (correspondiente a una horizontal, vertical o diagonal del DNA)
	 * Tiene que haber minimo 2 para que se considere DNA de mutante
	 * 
	 * @param str
	 * @return int (cantidad de secuencias consecutivas)
	 */
	private int contarSecuencias(String str) {
		int contador = 0;
		int resultado = 0;
		for(int i = 1; i < str.length(); i++) {
			if(str.charAt(i - 1) == str.charAt(i))
				contador++;
			else
				contador = 0;
			if(contador >= 3) {
				contador = 0;
				resultado++;
			}
		}
		return resultado;
	}
}
