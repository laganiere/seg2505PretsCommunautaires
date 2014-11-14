package ca.uottawa.eecs.seg2505.objetpret.controlleur;

import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class PretControlleur {

	protected DBFacade dbFacade = null;

	public PretControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	public List<Emprunt> getDemandesDePret() {
		return null;
	}

	public void setAccepte(Emprunt demande, boolean accepte) {
		
	}
	
	public void ajouterEmprunt(Emprunt emprunt) {
		
	}
	
	public List<Emprunt> getObjetsEmpruntes(Utilisateur utilisateur) {
		return null;
	}
	
	public List<Emprunt> getObjetsPretes(Utilisateur utilisateur) {
		return null;
	}
	
	public void confirmerRetour(Emprunt emprunt) {
		
	}
}
