package ca.uottawa.eecs.seg2505.objetpret;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import ca.uottawa.eecs.seg2505.objetpret.db.ParseFacade;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;

public class ChoisirObjetActivity extends ActionBarActivity {
	
	private Objet obj = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choisir_objet);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.choisir_objet, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	/**
	 * Initialise l'interface
	 */
	private void init() {
		
		if (Delegateur.getInstance() == null) {
			Delegateur.setDBFacade(new ParseFacade());
		} //if
		
		Objet obj = getObjet();
		
		TextView nom = (TextView) findViewById(R.id.textViewNom);
		nom.setText(obj.getNom());
		
		TextView description = (TextView) findViewById(R.id.textViewDescription);
		description.setText(obj.getDescription());
		description.setMovementMethod(new ScrollingMovementMethod());

		TextView disbonibilite = (TextView) findViewById(R.id.textViewDisponibilite);
		disbonibilite.setText("");
	}
	
	/**
	 * Permet de choisir la date de début de l'emprunt
	 * est lancée lorsqu'on clique sur le champ de date début
	 * @param view
	 */
	public void onClickDebut(View view) {
		
		TextView dateDebutView = (TextView) findViewById(R.id.textViewDateDebut);
		String dateDebut = (String) dateDebutView.getText();
		Date currentDate = getDateFromString(dateDebut);
		
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(currentDate);
        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		DatePickerDialog datePicker = new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int month,
					int day) {
				
				// set selected date to dateDebut
				TextView dateDebut = (TextView) findViewById(R.id.textViewDateDebut);
				String newDateDebut = String.valueOf(year) + "/" + String.valueOf(month+1) +"/"+ String.valueOf(day);
				dateDebut.setText(newDateDebut);
				
				
				// check if date fin has default value and set to next day
				TextView dateFinView = (TextView) findViewById(R.id.textViewDateFin);
				String dateFinString = (String) dateFinView.getText();
				
				if (dateFinString == getString(R.string.label_date_par_defaut)) {
					
					Calendar calendar = Calendar.getInstance();
					Date tempDate = getDateFromString(newDateDebut);
			        calendar.setTime(tempDate);
			        
			        calendar.add(Calendar.DAY_OF_MONTH, 1);
			        
			        int year2 = calendar.get(Calendar.YEAR);
			        int month2 = calendar.get(Calendar.MONTH);
			        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
			        
			        String newDateFin = String.valueOf(year2) + "/" + String.valueOf(month2+1) +"/"+ String.valueOf(day2);
			        dateFinView.setText(newDateFin);
				}
				
			}
		}, year, month, day);
		
		datePicker.show();
	}
	
	/**
	 * Permet de choisir la date de fin de l'emprunt
	 * est lancée lorsqu'on clique sur le champ de date de fin
	 * @param view
	 */
	public void onClickFin(View view) {
		
		TextView dateFinView = (TextView) findViewById(R.id.textViewDateFin);
		String dateFin = (String) dateFinView.getText();
		Date currentDate = getDateFromString(dateFin);
		
		
		Calendar calendar = Calendar.getInstance();  
        calendar.setTime(currentDate);
        
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		DatePickerDialog datePicker = new DatePickerDialog(this, new OnDateSetListener() {
			
			@Override
			public void onDateSet(DatePicker view, int year, int month,
					int day) {
				
				// check if date fin has default value and set to next day
				TextView dateFin = (TextView) findViewById(R.id.textViewDateFin);
				String newDateFin = String.valueOf(year) + "/" + String.valueOf(month+1) +"/"+ String.valueOf(day);
				dateFin.setText(newDateFin);
				
				// check if date fin has default value and set to next day
				TextView dateDebutView = (TextView) findViewById(R.id.textViewDateDebut);
				String dateDebutString = (String) dateDebutView.getText();
				
				if (dateDebutString == getString(R.string.label_date_par_defaut)) {
					
					Calendar calendar = Calendar.getInstance();
					Date tempDate = getDateFromString(newDateFin);
			        calendar.setTime(tempDate);
			        
			        calendar.add(Calendar.DAY_OF_MONTH, -1);
			        
			        int year2 = calendar.get(Calendar.YEAR);
			        int month2 = calendar.get(Calendar.MONTH);
			        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
			        
			        String newDateDebut = String.valueOf(year2) + "/" + String.valueOf(month2+1) +"/"+ String.valueOf(day2);
			        dateDebutView.setText(newDateDebut);
				}

			}
		}, year, month, day);
		
		datePicker.show();
	}
	
	/**
	 * lancée lorsqu'on appui sur le bouton valider disponibilité
	 * @param view
	 */
	public void onValiderDisponibilite(View view) {
		estDisponible(view);
	}
	
	/**
	 * lancée lorsqu'on appui sur le bouton annulée
	 * @param view
	 */
	public void onCancel(View view) {
		finish();
	}
	
	/**
	 * Effectue la réservation si l'objet est disponible
	 * lancée lorsqu'on appui sur le bouton valider réserver
	 * @param view
	 */
	public void onReserver(View view){
		
		Objet objet=getObjet();
		
		boolean disponible = estDisponible(view);
		
		if (disponible) {
		
			Delegateur del = Delegateur.getInstance();
			
			TextView dateFinView = (TextView) findViewById(R.id.textViewDateFin);
			String dateFinString = (String) dateFinView.getText();
	        
	        TextView dateDebutView = (TextView) findViewById(R.id.textViewDateDebut);
			String dateDebutString = (String) dateDebutView.getText();
			
			Date dateFin = getDateFromString(dateFinString);
			Date dateDebut = getDateFromString(dateDebutString);
			
			// number of days between DateFin and DateDebut
			Calendar calendarDebut = Calendar.getInstance();
		    Calendar calendarFin = Calendar.getInstance();
		    calendarDebut.setTime(dateDebut);
		    calendarFin.setTime(dateFin);
		    long diff = calendarFin.getTimeInMillis() - calendarDebut.getTimeInMillis();
		    
		    int duree = (int) diff / (24 * 60 * 60 * 1000); 
		    
			
			Emprunt emprunt = new Emprunt();
			emprunt.setDateEmprunt(dateDebut);
			emprunt.setDuree(duree);
			emprunt.setObjet(objet);
			emprunt.setEvalueParEmprunteur(false);
			emprunt.setEvalueParPreteur(false);
			emprunt.setUtilisateur(del.getUtilisateurCourant());
			emprunt.setPreteur(objet.getPreteur());
			emprunt.setStatutDemande();		
			
			del.ajouterEmprunt(emprunt);
			
			finish();	
			
		} //if
	
	}
	
	/**
	 * Extrait l'objet passé en paramètre, 
	 * si l'objet n'est pas passé on créer une instance vide
	 * 
	 * @return instance de l'objet
	 */
	private Objet getObjet() {
		
		if (this.obj == null) {
			
			// try and get objet passed as parameter
			Intent intent = getIntent();
			Objet obj = (Objet) intent.getSerializableExtra("objet");
			
			// if still null create a mock object
			if (obj == null) { // create empty object
			
				Delegateur del = Delegateur.getInstance();
				obj = new Objet();
				
				obj.setPreteur(del.getUtilisateurCourant());
				obj.setNom("");
				obj.setDescription("");	        
				
			} //if
			
			this.obj = obj;
		} //if
		
		return this.obj;	
	}
	
	/**
	 * Valide que les dates choisi sont valides et affiche le statut 
	 * @param view
	 * @return true si est disponible et false sinon
	 */
	private boolean estDisponible(View view) {
		
		Objet objet=getObjet();
		
		boolean resultat = false;
	
		TextView dateFinView = (TextView) findViewById(R.id.textViewDateFin);
		String dateFinString = (String) dateFinView.getText();
		
        
        TextView dateDebutView = (TextView) findViewById(R.id.textViewDateDebut);
		String dateDebutString = (String) dateDebutView.getText();
		
		
		String status = "";
		String defaultDate = getString(R.string.label_date_par_defaut);
		TextView disponibilite = (TextView) findViewById(R.id.textViewDisponibilite);
		
		if (
			dateDebutString == defaultDate
			|| dateFinString == defaultDate
		) {
			status = getString(R.string.message_date_manquante);
			disponibilite.setTextColor(Color.RED);
		} else {
			       
			Date now = new Date();
			Date dateFin = getDateFromString(dateFinString);
			Date dateDebut = getDateFromString(dateDebutString);
			if (dateDebut.before(now)) {
				status = getString(R.string.message_date_debut_passe);
				disponibilite.setTextColor(Color.RED);
				
			} else if (dateDebut.equals(dateFin) || dateDebut.after(dateFin)) {
				status = getString(R.string.message_date_de_debut_invalide);
				disponibilite.setTextColor(Color.RED);
			} else {
				Delegateur del = Delegateur.getInstance();
				Boolean available = del.estDisponible(objet, dateDebut);
				if (available) {
					status = getString(R.string.message_objet_disponible);
					disponibilite.setTextColor(Color.BLUE);
					resultat = true;
				} else {
					status = getString(R.string.message_objet_non_disponible);
					disponibilite.setTextColor(Color.RED);
				} //if
				
			}
		} //if
	
		disponibilite.setText(status);
		
		return resultat;
	}
	
	/**
	 * Transforme un string yyyy/MM/dd en objet Date
	 * @param view
	 * @return l'objet date
	 */
	private Date getDateFromString(String value) {
		Date result = new Date();
		if (value.length() > 0) {
			SimpleDateFormat dateParser = new SimpleDateFormat("yyyy/MM/dd", Locale.CANADA);
			try {
				result = dateParser.parse(value);
			} catch (ParseException e) {
				// date is not valid just keep current date as value
			}
		} //if
	
		return result;
	}
}
