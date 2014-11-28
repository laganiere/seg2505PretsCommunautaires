package ca.uottawa.eecs.seg2505.objetpret.controlleur;

import java.util.Date;
import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class ObjetControlleur {

	protected DBFacade dbFacade = null;

	public ObjetControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	public void ajouterObjet(Objet objet) {
		// TODO Auto-generated method stub
		
	}

	public List<Objet> rechercherObjets(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean retirerObjet(Objet objet) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public boolean retirerObjet(List<Objet> objet) {return dbFacade.retirerObjet(objet);	}


	public List<Objet> listeObjetUtilisateur(Utilisateur utilisateur) {
		return dbFacade.listeObjetUtilisateur(utilisateur);
	}


	public boolean changerDisponibilitePeriode(Objet objet, Date date,
			boolean estDisponible) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean estDisponible(Objet objet, Date date) {
		return dbFacade.estDisponible(objet, date);
	}
	
}
