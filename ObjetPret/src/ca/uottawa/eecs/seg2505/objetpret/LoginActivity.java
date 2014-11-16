package ca.uottawa.eecs.seg2505.objetpret;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
	
	public void onLogin(View view) {
		EditText edUsername = (EditText)findViewById(R.id.editTextUsername);
		EditText edPassword = (EditText)findViewById(R.id.editTextPassword);
		
		String username = edUsername.getText().toString();
		String password = edPassword.getText().toString();
		
		ParseUser.logInInBackground(username, password, new LogInCallback() {
			
			@Override
			public void done(ParseUser arg0, ParseException arg1) {
				if (arg1 != null) {
					String message = getResources().getString(R.string.text_login_failed);
					Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
				} else {
					finish();
				}
			}
		});
	}
	
	public void onCancel(View view) {
		finish();
	}
}
