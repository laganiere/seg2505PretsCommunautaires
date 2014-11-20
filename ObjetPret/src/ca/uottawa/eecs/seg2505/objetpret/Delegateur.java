package ca.uottawa.eecs.seg2505.objetpret;

import java.util.Date;
import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.controlleur.ObjetControlleur;
import ca.uottawa.eecs.seg2505.objetpret.controlleur.PretControlleur;
import ca.uottawa.eecs.seg2505.objetpret.controlleur.UtilisateurControlleur;
import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class Delegateur {
	/**
	 * La reference au Singleton
	 */
	private static Delegateur delegateur = null;
	/**
	 * La specification d'une DBFacade est requise pour le fonctionnement du
	 * systeme
	 */
	private static DBFacade dbFacade = null;

	public static void setDBFacade(DBFacade facade) {
		dbFacade = facade;
	}

	/**
	 * afin d'obtenir le Singleton
	 * 
	 * @return
	 */
	public static Delegateur getInstance() {
		if (delegateur == null && dbFacade != null) {
			delegateur = new Delegateur(dbFacade);
		}
		return delegateur;
	}

	private PretControlleur pretControlleur = null;
	private ObjetControlleur objetControlleur = null;
	private UtilisateurControlleur utilisateurControlleur = null;

	/**
	 * Methode pour assigner une DBFacade au systeme
	 * 
	 * @param dbFacade
	 *            une classe qui implemente DBFacade
	 */
	private Delegateur(DBFacade dbFacade) {
		objetControlleur = new ObjetControlleur(dbFacade);
		pretControlleur = new PretControlleur(dbFacade);
		utilisateurControlleur = new UtilisateurControlleur(dbFacade);
	}

	/**
	 * Methode pour obtenir l'utlisateur courant
	 * 
	 * @return L'utilisateur courant ou null si le login n'est pas fait encore
	 */
	public Utilisateur getUtilisateurCourant() {
		return utilisateurControlleur.getUtilisateurCourant();
	}

	/**
	 * Methode pour sauvegarder l'utlisateur
	 * 
	 * @param user
	 *            L'utilisateur qu'on veut sauvegarder
	 * @return Si l'utilisateur a ete sauvegarder
	 */
	public boolean sauvegarderUtilisateur(Utilisateur user) {
		return utilisateurControlleur.sauvegarderUtilisateur(user);
	}

	/**
	 * Methode pour verifier si le nom d'utilisateur est disponible
	 * 
	 * @param username
	 *            Le nom d'utilisateur qu'on veut verifier
	 * @return Si le nom d'utilisateur est disponible
	 */
	public boolean isUsernameAvailable(String username) {
		return utilisateurControlleur.isUsernameAvailable(username);
	}

	/**
	 * Methode pour verifier si le email est disponible
	 * 
	 * @param email
	 *            Le email qu'on veut verifier
	 * @return Si le email est disponible
	 */

	public boolean isEmailAvailable(String email) {
		return utilisateurControlleur.isEmailAvailable(email);
	}

	/**
	 * Methode pour ajouter un objet à notre base de données
	 * 
	 * @param objet
	 *            L'objet à ajouter
	 */
	public void ajouterObjet(Objet objet) {
		if (objet != null) {
			objetControlleur.ajouterObjet(objet);
		}
	}
	
	public List<Emprunt>getEmpruntsRecuNonEvalues() {
		return utilisateurControlleur.getEmpruntsRecuNonEvalues();
	}
	
	public List<Emprunt>getEmpruntsDonneeNonEvalues() {
		return utilisateurControlleur.getEmpruntsDonneeNonEvalues();
	}
	
	public void addEvaluation(Utilisateur utilisateur, int rating) {
		utilisateurControlleur.addEvaluation(utilisateur, rating);
	}
	
	public void addEvaluationPreteur(Utilisateur preteur, int rating) {
		utilisateurControlleur.addEvaluationPreteur(preteur, rating);
	}
	
	public boolean modifierUtilisateur(Utilisateur user) {
		return utilisateurControlleur.modifierUtilisateur(user);
	}
	
	public List<Objet> rechercherObjets(String keyword) {
		return objetControlleur.rechercherObjets(keyword);
	}
	
	public boolean retirerObjet(Objet objet) {
		return objetControlleur.retirerObjet(objet);
	}
	
	public boolean changerDisponibilitePeriode(Objet objet, Date date, boolean estDisponible) {
		return objetControlleur.changerDisponibilitePeriode(objet, date, estDisponible);
	}
	
	public boolean estDisponible(Objet objet, Date date) {
		return objetControlleur.estDisponible(objet, date);
	}

	/**
	 * Methode pour avoir la liste des demandes de prêt (non acceptees) pour les
	 * objets de l'utilisateur courant
	 **/
	public List<Emprunt> getDemandesDePret() {
		return pretControlleur.getDemandesDePret();
	}

	/**
	 * Methode pour definir l'etat d'une demande de prêt comme acceptee ou
	 * refusee  
	 * @param demande - l'Emprunt represantant la demande de pret a traiter 
	 * @param accepte - <code>true</code> si la demande est acceptee, <code>false</code> si elle est refusee
	 **/
	public void setAccepte(Emprunt demande, boolean accepte){   
	   if(demande != null){        
		   pretControlleur.setAccepte(demande, accepte);    
	   }
	}
	
	public void ajouterEmprunt(Emprunt emprunt) {
		pretControlleur.ajouterEmprunt(emprunt);
	}
	
	public List<Emprunt> getObjetsEmpruntes(Utilisateur utilisateur) {
		return pretControlleur.getObjetsEmpruntes(utilisateur);
	}
	
	public List<Emprunt> getObjetsPretes(Utilisateur utilisateur) {
		return pretControlleur.getObjetsPretes(utilisateur);
	}
	
	public void confirmerRetour(Emprunt emprunt) {
		pretControlleur.confirmerRetour(emprunt);
	}
	
	public boolean login(String username, String password) {
		return utilisateurControlleur.login(username, password);
	}
}