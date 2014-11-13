package ca.uottawa.eecs.seg2505.objetpret.model;

import ca.uottawa.eecs.seg2505.objetpret.Constantes;

public class Emprunt {
	public enum Statut{DEMANDE, ACCEPTE, REFUSE, CONFIRME, RETOURNE}
	private String dateEmprunt = Constantes.VIDE;
	// duree: nombre de jours
	private int duree = 1;
	private Statut statut=Statut.DEMANDE;
	private Objet objet = null;
	private Utilisateur utilisateur = null;
	private Preteur preteur = null;
	
	
	
	public Emprunt() {
	}

	public String getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(String dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Objet getObjet() {
		return objet;
	}

	public void setObjet(Objet objet) {
		this.objet = objet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Preteur getPreteur() {
		return preteur;
	}

	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}
	public void setStatutDemande(){
		statut=Statut.DEMANDE;
	}
	public boolean isDemande(){
		return statut.equals(Statut.DEMANDE);
	}
	public void setStatutAccepte(){
		statut=Statut.ACCEPTE;
	}
	public boolean isAccepte(){
		return statut.equals(Statut.ACCEPTE);
	}
	public void setStatutRefuse(){
		statut=Statut.REFUSE;
	}
	public boolean isRefuse(){
		return statut.equals(Statut.REFUSE);
	}
	public void setStatutConfirme(){
		statut=Statut.CONFIRME;
	}
	public boolean isConfirme(){
		return statut.equals(Statut.CONFIRME);
	}
	public void setStatutRetourne(){
		statut=Statut.RETOURNE;
	}
	public boolean isRetourne(){
		return statut.equals(Statut.RETOURNE);
	}
}
