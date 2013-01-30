package com.example.tabdemo;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class WeatherAdapter extends ArrayAdapter<HashMap<String, Object>> {

	Context context;
	int layoutResourceId;

	ArrayList<HashMap<String, Object>> searchResults;

	// ArrayList that will hold the original Data
	ArrayList<HashMap<String, Object>> originalValues;

	public WeatherAdapter(Context context, int layoutResourceId,
			ArrayList<HashMap<String, Object>> searchResults) {
		super(context, layoutResourceId, searchResults);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.searchResults = searchResults;
	}

	
	
	 @Override
     public int getCount(){
           return searchResults!=null ? searchResults.size() : 0;
     }
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		WeatherHolder holder = null;

		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new WeatherHolder();
			holder.servicename = (TextView) row.findViewById(R.id.servicename);
			holder.address = (TextView) row.findViewById(R.id.address);
			holder.status = (TextView) row.findViewById(R.id.status);

			row.setTag(holder);
		} else {
			holder = (WeatherHolder) row.getTag();
		}

		String servicename = searchResults.get(position).get("servicename")
				.toString();
		String Address = searchResults.get(position).get("Address").toString();
		String Dealer_Status = searchResults.get(position).get("Dealer_Status")
				.toString();
		String Service_Status = searchResults.get(position)
				.get("Service_Status").toString();

		String merchandising = searchResults.get(position).get("merchandising")
				.toString();

		// set the data to be displayed
		holder.address.setText(Address);

		holder.servicename.setText(servicename);

		if (Service_Status.equalsIgnoreCase("Yes")) {
			if (Dealer_Status.equalsIgnoreCase("Yes")) {

				if (merchandising.equalsIgnoreCase("Yes")) {
					holder.status.setText(getContext().getResources()
							.getString(R.string.Deler_Service_mact));
				} else {
					holder.status.setText(getContext().getResources()
							.getString(R.string.Deler_Service));
				}
			}

			else {

				if (merchandising.equalsIgnoreCase("Yes")) {
					holder.status.setText(getContext().getResources()
							.getString(R.string.Service_mact));
				} else {
					holder.status.setText(getContext().getResources()
							.getString(R.string.ServiceStatus));
				}

			}
		} else {

			if (Dealer_Status.equalsIgnoreCase("Yes")) {

				if (merchandising.equalsIgnoreCase("Yes")) {
					holder.status.setText(getContext().getResources()
							.getString(R.string.Deler_mact));
				} else {
					holder.status.setText(getContext().getResources()
							.getString(R.string.Dealerstatus));
				}

			} else {

				if (merchandising.equalsIgnoreCase("Yes")) {
					holder.status.setText(getContext().getResources()
							.getString(R.string.mact));
				} else {
					holder.status.setText("");
				}

			}
		}

		return row;
	}

	static class WeatherHolder {
		TextView servicename;
		TextView address;
		TextView status;
	}
}