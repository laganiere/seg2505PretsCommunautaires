package ca.uottawa.eecs.seg2505.objetpret.controlleur;

import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class UtilisateurControlleur {

	protected DBFacade dbFacade = null;

	public UtilisateurControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	public Utilisateur getUtilisateurCourant() {
		return null;
	}

	public boolean sauvegarderUtilisateur(Utilisateur user) {
		return false;
	}
	
	public boolean modifierUtilisateur(Utilisateur user) {
		return false;
	}

	public boolean isUsernameAvailable(String username) {
		return false;
	}

	public boolean isEmailAvailable(String email) {
		return false;
	}
	
	public List<Emprunt>getEmpruntsRecuNonEvalues() {
		return null;
	}
	
	public List<Emprunt>getEmpruntsDonneeNonEvalues() {
		return null;
	}
	
	public void addEvaluation(Utilisateur utilisateur, int rating) {
		
	}
	
	public void addEvaluationPreteur(Utilisateur preteur, int rating) {
		
	}
	
	public boolean login(String username, String password) {
		boolean result = false;
		if (username != null 
				&& !username.isEmpty()
				&& password != null
				&& !password.isEmpty()) {
			result = dbFacade.login(username, password);
		}
		return result;
	}
}
