package ca.uottawa.eecs.seg2505.objetpret;

import java.util.ArrayList;
import java.util.List;

import ca.uottawa.eecs.seg2505.adapters.DemandeAdapter;
import ca.uottawa.eecs.seg2505.objetpret.controlleur.PretControlleur;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DemandePretActivity extends Activity {
	List<Emprunt> listeDeDemande=new ArrayList();
	DemandeAdapter aAdpt;
	Context context;
	int pos;
	Delegateur delegateur=Delegateur.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listeDeDemande=delegateur.getDemandesDePret();
		onCreateHelper();
	}
	protected void onCreateHelper(){
		setContentView(R.layout.activity_demande_pret);
		context=this;
		
		ListView lv = (ListView) findViewById(R.id.listView);
		
		
		aAdpt = new DemandeAdapter(this, listeDeDemande);
		
		lv.setAdapter(aAdpt);
		
		registerForContextMenu(lv);
		
		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			     public void onItemClick(AdapterView<?> parentAdapter, View view, int position, long id) {
			    	 pos=position;
			    	 Emprunt demande = listeDeDemande.get(position);
			    	 setContentView(R.layout.activity_demande_pret_helper);
			    	 TextView objet= (TextView) findViewById(R.id.objet);
			    	 TextView emprunteur= (TextView) findViewById(R.id.emprunteur);
			    	 TextView date = (TextView) findViewById(R.id.date);
			    	 TextView description = (TextView) findViewById(R.id.description);
			    	 TextView duree = (TextView) findViewById(R.id.duree);
			    	 RatingBar cote = (RatingBar) findViewById(R.id.cote);
			    	 objet.setText(demande.getObjet().getNom());
			    	 emprunteur.setText(demande.getUtilisateur().getPrenom()+" "+demande.getUtilisateur().getNom());
			    	 date.setText(demande.getDateEmprunt().toString().substring(0, 11));
			    	 description.setText(demande.getObjet().getDescription());
			    	 duree.setText(demande.getDuree() + " jour(s)");
			    	 cote.setRating((float)demande.getPreteur().getCoteUtilisateur());
			     }
			});
		
	}
	public void buttonClick(View view){
	 	String message="";
	 	switch(view.getId()){
		 	case (R.id.accepter_button):
		 		message=getString(R.string.demande_accept);
		 		delegateur.setAccepte(listeDeDemande.get(pos), true);
		 		listeDeDemande.remove(pos);
		 	break;
			case (R.id.refuser_button):
				message=getString(R.string.demande_refuse);
			delegateur.setAccepte(listeDeDemande.get(pos), false);
				listeDeDemande.remove(pos);
	 		break;
			case (R.id.retourner_button):
				message=getString(R.string.demande_retour);
			break;
	 	}
	 	onCreateHelper();
	 	Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) menuInfo;
		
		Emprunt demandePret = (Emprunt) aAdpt.getItem(aInfo.position);
		
		menu.setHeaderTitle(demandePret.getObjet().getNom());
		menu.add(1,1,1, getString(R.string.accepter));
		menu.add(2,2,2, getString(R.string.refuser));
		menu.add(3,3,3, getString(R.string.pas_reponse));
	}
		
	@Override 
	public boolean onContextItemSelected(MenuItem item){
		int itemId = item.getItemId();
		AdapterContextMenuInfo aInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		if(itemId==2 || itemId==1){
			String message="";
			aAdpt.notifyDataSetChanged();
			if(itemId==2){
				delegateur.setAccepte(listeDeDemande.get(aInfo.position), false);
				message=getString(R.string.demande_refuse);
			}
			else{
				delegateur.setAccepte(listeDeDemande.get(aInfo.position), true);
				message=getString(R.string.demande_accept);
			}
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
			listeDeDemande.remove(aInfo.position);
		}
		return true;
	}
}
