package ca.uottawa.eecs.seg2505.adapters;

import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.R.id;
import ca.uottawa.eecs.seg2505.objetpret.R.layout;
import ca.uottawa.eecs.seg2505.objetpret.model.Emprunt;
import ca.uottawa.eecs.seg2505.objetpret.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
 
public class DemandeAdapter extends ArrayAdapter<Emprunt> {
	private final Context context;
	private List<Emprunt> demandePret;
	public DemandeAdapter(Context context, List<Emprunt> demandePret) {
		super(context, R.layout.activity_confirmer_emprunt, demandePret);
		this.context = context;
		this.demandePret = demandePret;
	}
	public int getCount() {
		return demandePret.size();
	}

	public Emprunt getItem(int position) {
		return demandePret.get(position);
	}

	public long getItemId(int position) {
		return demandePret.get(position).hashCode();
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.activity_demande_pret_row, parent, false);
		TextView objetView = (TextView) rowView.findViewById(R.id.objet);
		TextView emprunteurView = (TextView) rowView.findViewById(R.id.emprunteur);
		TextView dateView = (TextView) rowView.findViewById(R.id.date);
		RatingBar coteBar = (RatingBar) rowView.findViewById(R.id.cote);
		
		objetView.setText(demandePret.get(position).getObjet().getNom());
		emprunteurView.setText(demandePret.get(position).getUtilisateur().getPrenom()+" "+demandePret.get(position).getUtilisateur().getNom());
		dateView.setText(demandePret.get(position).getDateEmprunt());
		coteBar.setRating((float)demandePret.get(position).getUtilisateur().getCoteUtilisateur());
		coteBar.setEnabled(false);
		return rowView;
	}
	
}
