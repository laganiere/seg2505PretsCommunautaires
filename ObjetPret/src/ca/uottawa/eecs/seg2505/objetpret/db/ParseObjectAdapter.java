package ca.uottawa.eecs.seg2505.objetpret.db;

import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import ca.uottawa.eecs.seg2505.objetpret.model.Preteur;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

import com.parse.ParseObject;
import com.parse.ParseUser;

public class ParseObjectAdapter {

	// Definition des classes et des attributs dans Parse
	
	// Pour la classe Emprunt
	public static String empruntClassName = "Emprunt";
	public static String empruntDateEmprunt = "empruntDateEmprunt";
	public static String empruntDuree = "empruntDuree";
	public static String empruntStatut = "empruntStatut";
	public static String empruntEstEvalueParEmprunteur = "empruntEstEvalueParEmprunteur";
	public static String empruntEstEvalueParPreteur = "empruntEstEvalueParPreteur";
	public static String empruntObjet = "empruntObjet";
	public static String empruntUtilisateur = "empruntUtilisateur";
	public static String empruntPreteur = "empruntPreteur";
		
	// Pour la classe Objet
	public static String objetClassName = "Objet";
	public static String objetID = "objetID";
	public static String objetNom = "objetNom";
	public static String objetDescription = "objetDescription";
	public static String objetTauxJournalier = "objetTauxJournalier";
	public static String objetDepotGarantie = "objetDepotGarantie";
	public static String objetPreteur = "objetPreteur";
	
	// Pour la classe Utilisateur
	public static String utilisateurClassName = "Utilisateur";
	public static String utilisateurNomUtilisateur = "utilisateurNomUtilisateur";
	public static String utilisateurPrenom = "utilisateurPrenom";
	public static String utilisateurNom = "utilisateurNom";
	public static String utilisateurPassword = "utilisateurPassword";
	public static String utilisateurEmail = "utilisateurEmail";
	public static String utilisateurAdresse = "utilisateurAdresse";
	public static String utilisateurVille = "utilisateurVille";
	public static String utilisateurCodePostal = "utilisateurCodePostal";
	public static String utilisateurPays = "utilisateurPays";
	public static String utilisateurCote = "utilisateurCote";
	
	public static String utilisateurRolePreteur = "RolePreteur";
	public static String utilisateurRolePreteurCote = "RolePreteurCote";
	
	public static String erreurTag = "ParseFacade";
		
	/**
	 * @param emprunt L'emprunt de notre model
	 * @return L'objet ParseObject qui represente l'emprunt
	 */
	public static ParseObject from(Emprunt emprunt) {
		ParseObject o = new ParseObject(empruntClassName);
		
		o.put(empruntDateEmprunt, emprunt.getDateEmprunt());
		o.put(empruntDuree, emprunt.getDuree());
		o.put(empruntObjet, emprunt.getObjet().getID());
		o.put(empruntPreteur, emprunt.getPreteur().getNomUtilisateur());
		o.put(empruntUtilisateur, emprunt.getUtilisateur().getNomUtilisateur());
		o.put(empruntStatut, emprunt.getStatus().toString());
		
		o.put(empruntEstEvalueParEmprunteur, emprunt.estEvalueParEMPRUNTEUR());
		o.put(empruntEstEvalueParPreteur, emprunt.estEvalueParPreteur());
		
		return o;
	}
	
	/**
	 * @param emprunt L'objet de notre model
	 * @return L'objet ParseObject qui represente l'objet du model
	 */
	public static ParseObject from(Objet objet) {
		ParseObject o = new ParseObject(objetClassName);
		o.put(objetDepotGarantie, objet.getDepotGarantie());
		o.put(objetDescription, objet.getDescription());
		o.put(objetNom, objet.getNom());
		if (objet.getPreteur() != null) {
			o.put(objetPreteur, objet.getPreteur().getNomUtilisateur());
		}
		o.put(objetTauxJournalier, objet.getTauxJournalier());
		return o;
	}
	
	/**
	 * @param utilisateur Un utilisateur
	 * @return L'objet ParseUser qui represente l'utilisateur
	 */
	public static ParseUser from(Utilisateur utilisateur) {
		ParseUser user = ParseFacade.getParseUser(utilisateur.getNomUtilisateur());
		if (user == null) {
			user = new ParseUser();
			user.setUsername(utilisateur.getNom());
		}
		
		user.put(utilisateurAdresse, utilisateur.getAdresse());
		user.put(utilisateurCodePostal, utilisateur.getCodePostal());
		user.put(utilisateurCote, utilisateur.getCoteUtilisateur());
		user.put(utilisateurEmail, utilisateur.getEmail());
		user.put(utilisateurNom, utilisateur.getNom());
		user.put(utilisateurPassword, utilisateur.getPassword());
		user.put(utilisateurPays, utilisateur.getPays());
		user.put(utilisateurPrenom, utilisateur.getPrenom());
		user.put(utilisateurVille, utilisateur.getVille());
		
		if (utilisateur.isPreteur()) {
			user.put(utilisateurRolePreteur, true);
			user.put(utilisateurRolePreteurCote, utilisateur.getPreteur().getCote());
		}
		return user;
	}
	
	/**
	 * @param pObject L'objet ParseObject qui represente un Emprunt
	 * @return une instance de Emprunt
	 */
	public static Emprunt toEmprunt(ParseObject pObject) {
		Emprunt emprunt = new Emprunt();
		emprunt.setDateEmprunt(pObject.getDate(empruntDateEmprunt));
		emprunt.setDuree(pObject.getInt(empruntDuree));
		emprunt.setID(pObject.getObjectId());
		
		emprunt.setEvalueParEmprunteur(pObject.getBoolean(empruntEstEvalueParEmprunteur));
		emprunt.setEvalueParPreteur(pObject.getBoolean(empruntEstEvalueParPreteur));

		// set l'utilisateur
		String utilisteurNomUtil = pObject.getString(empruntUtilisateur);
		ParseUser user = ParseFacade.getParseUser(utilisteurNomUtil);
		if (user != null) {
			emprunt.setUtilisateur(toUtilisateur(user));
		}
		// set le preteur
		String preteurNomUtil = pObject.getString(empruntPreteur);
		ParseUser preteur = ParseFacade.getParseUser(preteurNomUtil);
		if (preteur != null) {
			emprunt.setPreteur(toUtilisateur(preteur));
		}
		
		// set l'objet
		String objetID = pObject.getString(empruntObjet);
		ParseObject o = ParseFacade.getParseObjetParID(objetID);
		if (o != null) {
			emprunt.setObjet(toObjet(o));
		}
		
		return emprunt;
	}
	
	/**
	 * @param pObject L'objet ParseObject qui represente un Objet
	 * @return une instance de Objet
	 */
	public static Objet toObjet(ParseObject pObject) {
		Objet objet = new Objet();
		
		objet.setDepotGarantie(pObject.getInt(objetDepotGarantie));
		objet.setDescription(pObject.getString(objetDescription));
		objet.setID(pObject.getObjectId());
		objet.setNom(pObject.getString(objetNom));
		objet.setTauxJournalier(pObject.getInt(objetTauxJournalier));
		
		// set le preteur
		String preteurNomUtil = pObject.getString(objetPreteur);
		ParseUser preteur = ParseFacade.getParseUser(preteurNomUtil);
		if (preteur != null) {
			objet.setPreteur(toUtilisateur(preteur));
		}
		
		return objet;
	}
	
	/**
	 * @param user L'objet ParseUser qui represente un utilisateur
	 * @return une instance d'Utilisateur
	 */
	public static Utilisateur toUtilisateur(ParseUser user) {
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setNom(user.getUsername());
		utilisateur.setAdresse(user.getString(utilisateurAdresse));
		utilisateur.setCodePostal(user.getString(utilisateurCodePostal));
		utilisateur.setCoteUtilisateur(user.getDouble(utilisateurCodePostal));
		utilisateur.setEmail(user.getString(utilisateurEmail));
		utilisateur.setNom(user.getString(utilisateurNom));
		utilisateur.setPassword(user.getString(utilisateurPassword));
		utilisateur.setPays(user.getString(utilisateurPays));
		utilisateur.setPrenom(user.getString(utilisateurPrenom));
		utilisateur.setVille(user.getString(utilisateurVille));

		if (user.getBoolean(utilisateurRolePreteur)) {
			Preteur preteur = new Preteur();
			preteur.setCote(user.getDouble(utilisateurRolePreteurCote));
			
			utilisateur.setPreteur(preteur);
		}
		
		return utilisateur;
	}
}
