package ca.uottawa.eecs.seg2505.objetpret;

import java.util.List;

import ca.uottawa.eecs.seg2505.objetpret.model.Objet;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter<T> extends ArrayAdapter<T> {
	// Declare Variables
	private Context context;
	private List<Objet> list;
	private SparseBooleanArray mSelectedItemsIds;
	private int dummyPosition;

	public ListViewAdapter(Context context, int resourceId, List<Objet> objet) {
		super(context, resourceId);
		mSelectedItemsIds = new SparseBooleanArray();
		this.context = context;
		this.list = objet;
		dummyPosition = list.indexOf("");
	}

	public View getView(int position, View view, ViewGroup parent) {

		TextView textView = new TextView(context);
		textView.setText(list.get(position).getNom());
		textView.setTextAppearance(context,
				android.R.style.TextAppearance_Large);
		if (mSelectedItemsIds.get(position, false)) {
			textView.setBackgroundColor(Color.rgb(51, 181, 229));

		} else {
			textView.setBackgroundColor(Color.TRANSPARENT);
		}
		if (position == getDummyPosition())
			textView.setVisibility(View.GONE);

		return textView;

	}

	public void remove(Objet object) {
		list.remove(object);
		notifyDataSetChanged();
	}

	public String getDescription(int position) {
		return list.get(position).getDescription();
	}

	public List<Objet> getString() {
		return list;
	}

	public void toggleSelection(int position) {
		selectView(position, !mSelectedItemsIds.get(position));

	}

	public void removeSelection() {
		mSelectedItemsIds = new SparseBooleanArray();
		notifyDataSetChanged();
	}

	public void selectView(int position, boolean value) {
		if (value)
			mSelectedItemsIds.put(position, value);
		else
			mSelectedItemsIds.delete(position);
		notifyDataSetChanged();
	}

	public int getSelectedCount() {
		return mSelectedItemsIds.size() - 1;
	}

	public SparseBooleanArray getSelectedIds() {
		return mSelectedItemsIds;
	}

	public int getDummyPosition() {
		return dummyPosition;
	}

	@Override
	public int getCount() {
		return list.size() - 1;

	}

	public Objet getObjet(int position) {
		return list.get(position);
	}

	public String getName(int position) {
		return list.get(position).getNom();
	}

}
