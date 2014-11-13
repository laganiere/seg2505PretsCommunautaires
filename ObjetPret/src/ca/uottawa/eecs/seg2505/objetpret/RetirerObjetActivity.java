package ca.uottawa.eecs.seg2505.objetpret;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.model.Objet;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

public class RetirerObjetActivity extends ActionBarActivity {
	ListView listView;
	List<Objet> list = new ArrayList<Objet>();
	ListViewAdapter<View> adapter;
	int p = -1;
	Button button;
	ActionMode actionMode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_retirer_objet);

		listView = (ListView) findViewById(R.id.listViewRetirer);
		button = (Button) findViewById(R.id.buttonSelectAll);
		button.setBackgroundColor(Color.TRANSPARENT);

		initList();

		// adapteur qui servira a mettre l'information dans le listView
		adapter = new ListViewAdapter<View>(this,
				android.R.layout.simple_list_item_1, list);

		// Assign adapter to ListView
		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {
				if (adapter.getDummyPosition() == position) {
					return;
				}
				adapter.selectView(adapter.getDummyPosition(), true);
				listView.setItemChecked(adapter.getDummyPosition(), true);
				// Calls toggleSelection method from ListViewAdapter Class
				adapter.toggleSelection(position);
				// Set the CAB title according to total checked items
				mode.setTitle(adapter.getSelectedCount() + " Selected");

			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getTitle().toString()) {
				case "Supprimer":
					adapter.selectView(adapter.getDummyPosition(), false);
					if (adapter.getSelectedCount() >= 0) {
						dialog(adapter.getContext(), adapter.getSelectedIds());
					}
					// Close CAB
					mode.finish();
					return true;
				default:
					adapter.toggleSelection(adapter.getDummyPosition());
					return false;
				}

			}

			@SuppressLint("NewApi")
			@Override
			public boolean onCreateActionMode(final ActionMode mode, Menu menu) {
				menu.add("Supprimer");
				actionMode = mode;
				button.setVisibility(Button.VISIBLE);
				return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				adapter.removeSelection();
				button.setVisibility(Button.GONE);
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
		});

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentAdapter, View view,
					int position, long id) {
				dialog(adapter.getContext(), position,
						adapter.getName(position),
						list.get(position).getDescription(), false);

			}

		});
	}
	
	public void onRetour(View view){
		Intent intent = new Intent(this, MainActivity.class);
    	startActivity(intent);
	}


	private void dialog(Context context, final int position, String title,
			String message, final boolean action) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// set the title and the message of the alert
		builder.setTitle(title);
		builder.setMessage(message);

		// Add the buttons
		builder.setPositiveButton("Supprimer",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						if (action) {
							adapter.remove(adapter.getObjet(position));
						} else {
							dialog(adapter.getContext(),
									position,
									"Attention!",
									"Voulez-vous vraiment supprimer l'objet \""
											+ adapter.getName(position) + "\"?",
									true);
						}
					}
				});
		builder.setNegativeButton("Annuler",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		// create the alert and show it
		AlertDialog dialog = builder.create();
		dialog.show();

	}

	private void dialog(Context context, final SparseBooleanArray selected) {

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		// set the title and the message of the alert
		builder.setTitle("Attention!");
		builder.setMessage("Voulez-vous vraiment supprimer les objets sélectionnés?");

		// Add the buttons
		builder.setPositiveButton("Supprimer",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

						// Captures all selected ids with a loop
						for (int i = (selected.size() - 1); i >= 0; i--) {
							if (selected.valueAt(i)) {
								Objet selecteditem = adapter.getObjet(selected
										.keyAt(i));
								// Remove selected items following the ids
								adapter.remove(selecteditem);
							}

						}
					}
				});
		builder.setNegativeButton("Annuler",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
		// create the alert and show it
		AlertDialog dialog = builder.create();
		dialog.show();

	}

	private void initList() {
		list.add(addObjet("Scie", "Sert à couper du bois"));
		list.add(addObjet("Marteau", "Sert à planter des clous"));
		list.add(addObjet("Tondeuse à gazon", "Sert à couper du gazon"));
		list.add(addObjet("Pelle", "Sert à creuser des trous"));
		list.add(addObjet("Tournevis", "Sert à visser des vis"));
		list.add(addObjet("Voiture", "Pertmet de te déplacer du point A au point B"));
		list.add(
				addObjet("Remorque",
				"S'atache après une voiture, permet de transporter des objet trop gros pour entrer dans la voiture"));
		list.add(addObjet("Souffleur à feuille",
				"Crée du vent artificiel, pour souffler des objets"));
		list.add(addObjet("tente", "Sert d'abris pour dormir à l'extérieur"));
		list.add(addObjet("Béquille",
				"Aide à marcher lorsque nous avons une jambe cassée"));
		list.add(addObjet("Perceuse", "Sert à faire des trous"));
		list.add(addObjet("", ""));

	}

	private Objet addObjet(String nom, String description) {
		Objet objet = new Objet(null);
		objet.setNom(nom);
		objet.setDescription(description);
		return objet;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		menu.add("Supprimer");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		if (item.getTitle().toString().equals("Supprimer")) {
			if (adapter.getCount() > 0) {
				adapter.selectView(adapter.getDummyPosition(), true);
				listView.setItemChecked(adapter.getDummyPosition(), true);
				actionMode.setTitle(adapter.getSelectedCount() + " Selected");
			}
		}

		return true;
	}

	public void onSelectAll(View view) {
		if (checkSelected()) {
			for (int i = 0; i < adapter.getCount(); i++) {
				adapter.selectView(i, false);
			}
		} else {
			for (int i = 0; i < adapter.getCount(); i++) {
				adapter.selectView(i, true);
			}
		}
		actionMode.setTitle(adapter.getSelectedCount() + " Selected");
	}

	private boolean checkSelected() {

		SparseBooleanArray array = adapter.getSelectedIds();
		for (int i = 0; i < adapter.getCount(); i++) {
			if (!array.get(i, false)) {
				return false;
			}
		}
		return true;
	}

}
