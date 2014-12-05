package ca.uottawa.eecs.seg2505.objetpret;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import ca.uottawa.eecs.seg2505.objetpret.model.Utilisateur;

public class InscriptionActivity extends ActionBarActivity {

	static boolean checked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inscription);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inscription, menu);
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

	public void onCheckbox(View view) {
		if (checked == false) {
			checked = true;
		} else {
			checked = false;
		}
	}

	public void onSInscrire(View view) {
		EditText tUsername = (EditText) findViewById(R.id.editText1);
		final String username = tUsername.getText().toString();

		EditText tEmail = (EditText) findViewById(R.id.editText3);
		final String email = tEmail.getText().toString();

		EditText tPassword = (EditText) findViewById(R.id.editText4);
		final String password = tPassword.getText().toString();

		EditText tRepeatPassword = (EditText) findViewById(R.id.editText5);
		String repeatPassword = tRepeatPassword.getText().toString();

		if (Delegateur.getInstance().isUsernameAvailable(username) == false) {

			Toast.makeText(getApplicationContext(),
					"Le nom d'utilisateur n'est pas disponible.",
					Toast.LENGTH_LONG).show();

			Intent intent = new Intent(this, InscriptionActivity.class);
			startActivity(intent);

		}

		else {
			if (Delegateur.getInstance().isEmailAvailable(email) == false) {
				Toast.makeText(getApplicationContext(),
						"Le courriel n'est pas disponible.", Toast.LENGTH_LONG)
						.show();

				Intent intent = new Intent(this, InscriptionActivity.class);
				startActivity(intent);

			} else {
				if (password.equals(repeatPassword) == false) {
					Toast.makeText(getApplicationContext(),
							"Les mots de passe ne correspondent pas.",
							Toast.LENGTH_LONG).show();
				} else {
					if (checked == true) {
						EditText tNom = (EditText) findViewById(R.id.editText2);
						String nom = tNom.getText().toString();
						Utilisateur u = new Utilisateur();
						u.setNomUtilisateur(username);
						u.setEmail(email);
						u.setPassword(password);
						u.setNom(nom);

						Delegateur.getInstance().sauvegarderUtilisateur(u);

						Intent intent = new Intent(getApplicationContext(),
								ModifierProfilActivity.class); // trouver
																// Context au
																// lieu de this
						startActivity(intent);
					} else {
						Toast.makeText(this,
								"Confirmez que vous avez 18 ans ou plus.",
								Toast.LENGTH_LONG).show();
					}
				}
			}

		}
	}

	public void onAnnuler(View view) {
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
