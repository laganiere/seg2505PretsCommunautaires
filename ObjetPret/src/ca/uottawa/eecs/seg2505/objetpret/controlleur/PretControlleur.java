package ca.uottawa.eecs.seg2505.objetpret.controlleur;

import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.db.DBFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;

public class PretControlleur {

	protected DBFacade dbFacade = null;

	public PretControlleur(DBFacade dbFacade) {
		this.dbFacade = dbFacade;
	}

	public List<Emprunt> getDemandesDePret() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setAccepte(Emprunt demande, boolean accepte) {
		// TODO Auto-generated method stub
		
	}
}
