package com.example.tabdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SampleListFragment extends ListFragment {

	SampleAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		adapter = new SampleAdapter(getActivity());
		adapter.add(new SampleItem("Home", R.drawable.home_icon_white));

		adapter.add(new SampleItem("F12 Berlinetta",
				R.drawable.berlinetta_icon_white));
		adapter.add(new SampleItem("Vertu & Ferrari",
				R.drawable.in_partnership_icon_white));
		adapter.add(new SampleItem("About", R.drawable.about_icon_white));
		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// SampleActivity.tag.setVisibility(View.VISIBLE);
		Toast.makeText(getActivity(),
				"Click -" + adapter.getItem(position).tag, 1).show();

		switch (position) {
		case 0:
			startActivity(new Intent(getActivity(), TabsFragmentActivity.class));
			break;
		case 3:
			
			startActivity(new Intent(getActivity(), AboutusActivity.class));
			break;
		default:
			startActivity(new Intent(getActivity(), TabsFragmentActivity.class));
			break;
		}
		

	}

	private class SampleItem {
		public String tag;
		public int iconRes;

		public SampleItem(String tag, int iconRes) {
			this.tag = tag;
			this.iconRes = iconRes;
		}
	}

	public class SampleAdapter extends ArrayAdapter<SampleItem> {

		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.row, null);
			}
			ImageView icon = (ImageView) convertView
					.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView
					.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}

	}

}
