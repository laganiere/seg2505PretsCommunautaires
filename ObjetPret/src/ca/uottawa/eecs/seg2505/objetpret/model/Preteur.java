package ca.uottawa.eecs.seg2505.objetpret.model;

import java.io.Serializable;


public class Preteur implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7924534199264453436L;
	private double cote = -1;

	public Preteur() {
	}

	public double getCote() {
		return cote;
	}

	public void setCote(double cote) {
		this.cote = cote;
	}

}