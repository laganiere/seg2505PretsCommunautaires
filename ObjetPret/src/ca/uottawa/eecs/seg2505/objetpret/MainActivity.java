package ca.uottawa.eecs.seg2505.objetpret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
    public void onInscription(View view) {
    	Intent intent = new Intent(this, InscriptionActivity.class);
    	startActivity(intent);
    }
    
    public void onAjouter(View view) {
    	Intent intent = new Intent(this, AjouterObjetActivity.class);
    	startActivity(intent);
    }
    
    public void onDisponibilite(View view) {
    	Intent intent = new Intent(this, DisponibiliteObjetActivity.class);
    	startActivity(intent);
    }
    
    public void onConfirmer(View view) {
    	Intent intent = new Intent(this, ConfirmerEmpruntActivity.class);
    	startActivity(intent);
    }
    
    public void onRetirer(View view) {
    	Intent intent = new Intent(this, RetirerObjetActivity.class);
    	startActivity(intent);
    }
    
    public void onProfil(View view) {
    	Intent intent = new Intent(this, ModifierProfilActivity.class);
    	startActivity(intent);
    }
    
    public void onRechercher(View view) {
    	Intent intent = new Intent(this, RechercherObjetActivity.class);
    	startActivity(intent);
    }
    
    public void onChoisir(View view) {
    	Intent intent = new Intent(this, ChoisirObjetActivity.class);
    	startActivity(intent);
    }
    
    public void onDemander(View view) {
    	Intent intent = new Intent(this, DemandePretActivity.class);
    	startActivity(intent);
    }
    
    public void onRetourner(View view) {
    	Intent intent = new Intent(this, ConfirmerRetourActivity.class);
    	startActivity(intent);
    }
    
    public void onEvaluer(View view) {
    	Intent intent = new Intent(this, EvaluationActivity.class);
    	startActivity(intent);
    }
    
    public void onLogin(View view) {
    	Intent intent = new Intent(this, LoginActivity.class);
    	startActivity(intent);
    }
}
