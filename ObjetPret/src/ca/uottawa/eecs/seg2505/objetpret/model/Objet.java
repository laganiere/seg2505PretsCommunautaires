package ca.uottawa.eecs.seg2505.objetpret.model;

import ca.uottawa.eecs.seg2505.objetpret.Constantes;


public class Objet {
	private String ID = Constantes.VIDE;
	private String nom = Constantes.VIDE;
	private String description = Constantes.VIDE;
	private boolean disponible = false;
	private int tauxJournalier = -1;
	private int depotGarantie = -1;
	private Preteur preteur = null;

	public Objet(Preteur preteur) {
		this.preteur = preteur;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public int getTauxJournalier() {
		return tauxJournalier;
	}

	public void setTauxJournalier(int tauxJournalier) {
		this.tauxJournalier = tauxJournalier;
	}

	public int getDepotGarantie() {
		return depotGarantie;
	}

	public void setDepotGarantie(int depotGarantie) {
		this.depotGarantie = depotGarantie;
	}

	public Preteur getPreteur() {
		return preteur;
	}

	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}

}