package com.valejandrov.webflux.app.entity;

public class Estadistica {

	private int mutantes;
	private int humanos;
	private String ratio;
	
	public String getRatio() {
		return ratio;
	}
	public void setRatio(String ratio) {
		this.ratio = ratio;
	}
	public int getMutantes() {
		return mutantes;
	}
	public void setMutantes(int mutantes) {
		this.mutantes = mutantes;
	}
	public int getHumanos() {
		return humanos;
	}
	public void setHumanos(int humanos) {
		this.humanos = humanos;
	}
	
}
