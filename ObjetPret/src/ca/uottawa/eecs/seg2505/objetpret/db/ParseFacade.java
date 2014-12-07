package ca.uottawa.eecs.seg2505.objetpret.db;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.util.Log;
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
		// query
		ParseQuery<ParseObject> query = new ParseQuery("Objet");
		query.whereStartsWith("objetNom", keyword);
		
		// results
		List<ParseObject> resultsParse = null;
		try {
			resultsParse = query.find();
		}
		catch(ParseException e) {
			// ???
		}
		
		// to List<Objet>
		List<Objet> resultsList = new ArrayList<Objet>();
		if(resultsParse != null)
			for(ParseObject obj : resultsParse)
				resultsList.add(ParseObjectAdapter.toObjet(obj));
		
		// bye bye
		return resultsList;
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
		try {
    		ParseQuery<ParseObject> query = ParseQuery.getQuery("Emprunt");
    		
    		query.whereEqualTo("empruntObjet", objet.getID());
    		query.whereEqualTo("empruntDateEmprunt", date);
    		
    		List<ParseObject> results = query.find();
    		
    		if (results.isEmpty()) {
    			return true;
    		} else {
    			return false;
    		} //if
		} catch (ParseException e) {
			Log.e(ParseObjectAdapter.erreurTag, "Impossible de valider la disponibilité");
			return false;
		} // try
	}

	@Override
	public List<Emprunt> getDemandesDePret(Utilisateur utilisateur) {
		try {
			ParseQuery<ParseObject> query = ParseQuery.getQuery("Emprunt");
			query.whereEqualTo("empruntPreteur", utilisateur.getNomUtilisateur());
			query.whereEqualTo("empruntStatut", "DEMANDE");
			List<ParseObject> resultats;
			List<Emprunt> emprunts = new ArrayList<Emprunt>();
			resultats = query.find();
			for(ParseObject res: resultats){
				emprunts.add(ParseObjectAdapter.toEmprunt(res));
				return emprunts;
			}
		}catch (Exception e){
			Log.e(ParseObjectAdapter.erreurTag, "erreur de Parse (Parsefacade.sauvegarderEmprunt)");
		}
		return new ArrayList<Emprunt>();// TODO Auto-generated method stub
	}

	@Override
	public void ajouterEmprunt(Emprunt emprunt) {
		ParseObject obj = ParseObjectAdapter.from(emprunt);
		
		try {
			obj.save();
		} catch (ParseException e) {
			Log.e(ParseObjectAdapter.erreurTag, "Impossible de sauvegarder l'emprunt.");
		}
	}
	
	@Override
	public void sauvegarderEmprunt(Emprunt emprunt){
		try{
			ParseObject empruntParse = ParseObjectAdapter.from(emprunt);
			Log.d("ID de l'Objet", emprunt.getID()+" parse: "+empruntParse.getObjectId());
			//enregistrer sur parse
			empruntParse.save();
		}catch(ParseException e){
			Log.e(ParseObjectAdapter.erreurTag, "erreur de Parse (Parsefacade.sauvegarderEmprunt)");
		}
		
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
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				ParseObjectAdapter.objetClassName);
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
	
	public boolean retirerObjet(List<Objet> objet) {
		List<ParseObject> parseObject = new LinkedList<ParseObject>();
		for (int i = 0; i < objet.size(); i++) {
			parseObject.add(getParseObjetParID(objet.get(i).getID()));

		}
		try {
			ParseObject.deleteAll(parseObject);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public List<Objet> listeObjetUtilisateur(Utilisateur utilisateur) {
		
		List<ParseObject> parse;
		List<Objet> objet = new LinkedList<Objet>();
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				ParseObjectAdapter.objetClassName);

		query.whereEqualTo(ParseObjectAdapter.objetPreteur,
				utilisateur.getNomUtilisateur());

		try {
			parse = query.find();
			if (parse.size() > 0) {
				for (int i = 0; i < parse.size(); i++) {
					objet.add(ParseObjectAdapter.toObjet(parse.get(i)));
				}
			}
		} catch (ParseException e) {
			return null;
		}

		return objet;
	}


}