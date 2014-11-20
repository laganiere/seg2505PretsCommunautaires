package ca.uottawa.eecs.seg2505.objetpret.model;

import java.io.Serializable;

import ca.uottawa.eecs.seg2505.objetpret.Constantes;

public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5890981082390755703L;
	private String nomUtilisateur = Constantes.VIDE;
	private String prenom = Constantes.VIDE;
	private String nom = Constantes.VIDE;
	private String password = Constantes.VIDE;
	private String email = Constantes.VIDE;
	private String adresse = Constantes.VIDE;
	private String ville = Constantes.VIDE;
	private String codePostal = Constantes.VIDE;
	private String pays = Constantes.VIDE;
	private double coteUtilisateur = -1;
	private Preteur preteur = null;

	public Utilisateur() {

	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public double getCoteUtilisateur() {
		return coteUtilisateur;
	}

	public void setCoteUtilisateur(double coteUtilisateur) {
		this.coteUtilisateur = coteUtilisateur;
	}

	public Preteur getPreteur() {
		return this.preteur;
	}
	
	public boolean isPreteur() {
		return (this.preteur != null);
	}

	public void setPreteur(boolean estPreteur) {
		if (estPreteur
				&& this.preteur == null) {
			this.preteur = new Preteur();
		} else if (!estPreteur) {
			this.preteur = null;
		}
	}
	
	public void setPreteur(Preteur preteur) {
		this.preteur = preteur;
	}
}