package ca.uottawa.eecs.seg2505.objetpret.db;

import java.util.Date;
import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class ParseFacade implements DBFacade {

	@Override
	public boolean sauvegarderUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUsernameAvailable(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmailAvailable(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ajouterObjet(Objet objet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Emprunt> getEmpruntsRecuNonEvalues(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getEmpruntsDonneeNonEvalues(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEvaluation(Utilisateur utilisateur, int rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEvaluationPreteur(Utilisateur preteur, int rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean modifierUtilisateur(Utilisateur user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Objet> rechercherObjets(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean retirerObjet(Objet objet) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean changerDisponibilitePeriode(Objet objet, Date date,
			boolean estDisponible) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean estDisponible(Objet objet, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Emprunt> getDemandesDePret(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAccepte(Emprunt demande, boolean accepte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ajouterEmprunt(Emprunt emprunt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Emprunt> getObjetsEmpruntes(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Emprunt> getObjetsPretes(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void confirmerRetour(Emprunt emprunt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utilisateur login(String username, String password) {
		Utilisateur utilisateur = null;
		try {
			ParseUser user = ParseUser.logIn(username, password);
			if (user != null) {
				utilisateur = ParseObjectAdapter.toUtilisateur(user);
			}
		} catch (ParseException e) {
			utilisateur = null;
		}
		return utilisateur;
	}
	
	public static ParseUser getParseUser(String nomUtilisateur) {
		ParseUser user = ParseUser.getCurrentUser();
		
		if (user != null) {
			if (user.getUsername().equals(nomUtilisateur)) {
				return user;
			}
		}
		
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.whereEqualTo("username", nomUtilisateur);
		
		try {
			List<ParseUser> list = query.find();
			if (list.size() > 0) {
				user = list.get(0);
			}
		} catch (ParseException e) {
			user = null;
		}
		
		return user;
	}
	
	public static ParseObject getParseObjetParID(String objetID) {
		ParseObject objet = null;
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(ParseObjectAdapter.objetClassName);
		
		try {
			objet = query.get(objetID);
		} catch (ParseException e) {
			objet = null;
		}
		
		return objet;
	}
	
	public Utilisateur getUtilisateurCourant() {
		ParseUser user = ParseUser.getCurrentUser();
		if (user != null) {
			return ParseObjectAdapter.toUtilisateur(user);
		}
		return null;
	}

}
