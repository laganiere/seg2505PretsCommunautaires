package ca.uottawa.eecs.seg2505.objetpret.db;

import java.util.Date;
import java.util.List;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public interface DBFacade {
	/**
	 * Methode pour sauvegarder l'utlisateur
	 * 
	 * @param user
	 *            L'utilisateur qu'on veut sauvegarder
	 * @return Si l'utilisateur a ete sauvegarder
	 */
	public boolean sauvegarderUtilisateur(Utilisateur user);

	/**
	 * Methode pour verifier si le nom d'utilisateur est disponible
	 * 
	 * @param username
	 *            Le nom d'utilisateur qu'on veut verifier
	 * @return Si le nom d'utilisateur est disponible
	 */
	public boolean isUsernameAvailable(String username);

	/**
	 * Methode pour verifier si le email est disponible
	 * 
	 * @param email
	 *            Le email qu'on veut verifier
	 * @return Si le email est disponible
	 */
	public boolean isEmailAvailable(String email);

	/**
	 * Methode pour ajouter un objet à notre base de données
	 * 
	 * @param objet
	 *            L'objet à ajouter
	 */
	public void ajouterObjet(Objet objet);

	public List<Emprunt> getEmpruntsRecuNonEvalues(Utilisateur utilisateur);

	public List<Emprunt> getEmpruntsDonneeNonEvalues(Utilisateur utilisateur);

	public void addEvaluation(Utilisateur utilisateur, int rating);

	public void addEvaluationPreteur(Utilisateur preteur, int rating);

	public boolean modifierUtilisateur(Utilisateur user);

	public List<Objet> rechercherObjets(String keyword);

	public boolean retirerObjet(Objet objet);

	public boolean changerDisponibilitePeriode(Objet objet, Date date,
			boolean estDisponible);

	public boolean estDisponible(Objet objet, Date date);

	/**
	 * Methode pour avoir la liste des demandes de prêt (non acceptees) pour les
	 * objets de l'utilisateur courant
	 **/
	public List<Emprunt> getDemandesDePret(Utilisateur utilisateur);

	/**
	 * Methode pour definir l'etat d'une demande de prêt comme acceptee ou
	 * refusee
	 * 
	 * @param demande
	 *            - l'Emprunt represantant la demande de pret a traiter
	 * @param accepte
	 *            - <code>true</code> si la demande est acceptee,
	 *            <code>false</code> si elle est refusee
	 **/
	public void sauvegarderEmprunt(Emprunt demande);

	public void ajouterEmprunt(Emprunt emprunt);

	public List<Emprunt> getObjetsEmpruntes(Utilisateur utilisateur);

	public List<Emprunt> getObjetsPretes(Utilisateur utilisateur);

	public void confirmerRetour(Emprunt emprunt);

	public Utilisateur login(String username, String password);

	public Utilisateur getUtilisateurCourant();
	
	public List<Objet> listeObjetUtilisateur(Utilisateur utilisateur);
	
	public boolean retirerObjet(List<Objet> objet);
}