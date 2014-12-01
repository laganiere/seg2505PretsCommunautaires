package ca.uottawa.eecs.seg2505.objetpret;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import ca.uottawa.eecs.seg2505.objetpret.model.Objet;

public class RechercherObjetActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rechercher_objet);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rechercher_objet, menu);
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

	public void onClick_searchButton(View view) {
		// things
		EditText searchInput = (EditText)findViewById(R.id.searchInput);
		ListView searchResults = (ListView)findViewById(R.id.searchResults);
		
		// delegateur & grab objects
		Delegateur delegateur = Delegateur.getInstance();
		List<Objet> resultsList = delegateur.rechercherObjets(searchInput.getText().toString());
		
		// list to plug into ListView
		List<Map<String, String>> values = new ArrayList<Map<String, String>>();
		for(Objet obj : resultsList) {
			Map<String, String> item = new HashMap<String, String>(2);
			item.put("nom", obj.getNom());
			item.put("description", obj.getDescription());
			values.add(item);
		}
		
		// plugging
		searchResults.setAdapter(new SimpleAdapter(this, values,
				android.R.layout.simple_list_item_2,
				new String[] {"nom", "description"},
				new int[] {android.R.id.text1, android.R.id.text2}
		));
	}
	
	public void onClick_selectButton(View view) {
		Intent intent = new Intent(this, ChoisirObjetActivity.class);
    	startActivity(intent); // goto object
	}
	
	public void onClick_cancelButton(View view) {
		finish(); // goodbye
	}
}
