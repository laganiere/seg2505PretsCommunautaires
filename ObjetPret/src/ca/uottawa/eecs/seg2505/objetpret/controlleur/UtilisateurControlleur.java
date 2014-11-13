package ca.uottawa.eecs.seg2505.objetpret.controlleur;

import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class UtilisateurControlleur {

	protected DBFacade dbFacade = null;

	public UtilisateurControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	public Utilisateur getUtilisateurCourant() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean sauvegarderUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isUsernameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isEmailAvailable(String email) {
		// TODO Auto-generated method stub
		return false;
	}
}
