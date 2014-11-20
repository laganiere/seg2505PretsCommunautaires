package ca.uottawa.eecs.seg2505.objetpret.model;

import java.io.Serializable;

import ca.uottawa.eecs.seg2505.objetpret.Constantes;

public class Emprunt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4505206704962286678L;

	public enum Statut{DEMANDE, ACCEPTE, REFUSE, CONFIRME, RETOURNE}
	public enum Evaluateur{AUCUN, PRETEUR, EMPRUNTEUR, TOUS}
	private String dateEmprunt = Constantes.VIDE;
	// duree: nombre de jours
	private int duree = 1;
	private Statut statut=Statut.DEMANDE;
	private Evaluateur eval = Evaluateur.AUCUN;
	private Objet objet = null;
	private Utilisateur utilisateur = null;
	private Utilisateur preteur = null;
	private String ID = Constantes.VIDE;
	
	
	public Emprunt() {
	}
	
	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
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

	public Utilisateur getPreteur() {
		return preteur;
	}

	public void setPreteur(Utilisateur preteur) {
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

	// retourne si cet emprunt a recu une evaluation ou pas
	// on permet une seule change pour l'evaluation pour le moment
	public boolean estEvalueParPreteur() {
		return eval.equals(Evaluateur.PRETEUR) 
				|| eval.equals(Evaluateur.TOUS);
	}
	
	public boolean estEvalueParEMPRUNTEUR() {
		return eval.equals(Evaluateur.EMPRUNTEUR) 
				|| eval.equals(Evaluateur.TOUS);
	}

	public void setEvalueParPreteur(boolean estEvalue) {
		if (estEvalue) {
			if (eval.equals(Evaluateur.AUCUN)) {
				eval = Evaluateur.PRETEUR;
			} else if (eval.equals(Evaluateur.EMPRUNTEUR)) {
				eval = Evaluateur.TOUS;
			}
		} else {
			if (eval.equals(Evaluateur.PRETEUR)) {
				eval = Evaluateur.AUCUN;
			} else if (eval.equals(Evaluateur.TOUS)){
				eval = Evaluateur.EMPRUNTEUR;
			}
		}
	}
	
	public void setEvalueParEmprunteur(boolean estEvalue) {
		if (estEvalue) {
			if (eval.equals(Evaluateur.AUCUN)) {
				eval = Evaluateur.EMPRUNTEUR;
			} else if (eval.equals(Evaluateur.PRETEUR)) {
				eval = Evaluateur.TOUS;
			}
		} else {
			if (eval.equals(Evaluateur.EMPRUNTEUR)) {
				eval = Evaluateur.AUCUN;
			} else if (eval.equals(Evaluateur.TOUS)){
				eval = Evaluateur.PRETEUR;
			}
		}
	}
}
