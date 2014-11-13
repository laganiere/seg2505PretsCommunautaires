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
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;


import ca.uottawa.eecs.seg2505.objetpret.model.Objet;

public class ChoisirObjetActivity extends ActionBarActivity {
	

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
	
	private void init() {
		Objet obj = getObjet();
		
		TextView nom = (TextView) findViewById(R.id.textViewNom);
		nom.setText(obj.getNom());
		
		TextView description = (TextView) findViewById(R.id.textViewDescription);
		description.setText(obj.getDescription());
		description.setMovementMethod(new ScrollingMovementMethod());

		TextView disbonibilite = (TextView) findViewById(R.id.textViewDisponibilite);
		disbonibilite.setText("");
	}
	
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
	
	public void onValiderDisponibilite(View view) {
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
				Boolean available = Math.random() > 0.5 ? true : false;
				if (available) {
					status = getString(R.string.message_objet_disponible);
					disponibilite.setTextColor(Color.BLUE);
				} else {
					status = getString(R.string.message_objet_non_disponible);
					disponibilite.setTextColor(Color.RED);
				} //if
				
			}
		} //if
	
		disponibilite.setText(status);
	}
	
	private Objet getObjet() {
		Objet obj = new Objet(null);
		obj.setNom("Une Pelle");
		obj.setDescription(
		        "la jolie pelle que mon arrière grand mère m'a légué\n"+
		        "Il faudrait faire attention de ne pas l'abimé\n"+
		        "NOTEZ que c'est une pelle de qualité supérieure là.\n"+
		        "Bon et si on mettais une super longue linge de texte qu'est qui arriverait?  Est qu'il va y avoir un scroll horizontal?\n"+
		        "Plus \n et plus \n et plus\n de texte"
		);
		return obj;	
	}
	
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
