package com.example.tabdemo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Tab4Fragment extends Fragment implements OnMarkerClickListener,
		OnInfoWindowClickListener, OnMarkerDragListener {

	private static final LatLng BRISBANE = new LatLng(22.47093, 75.0235);
	private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
	private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
	private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
	private static final LatLng NY = new LatLng(40.6397, 73.7789);
	private static final LatLng SFO = new LatLng(37.6190, 122.3749);
	private static final LatLng LONDON = new LatLng(51.4775, 0.4614);

	LatLng myLocation;

	/** Demonstrates customizing the info window and/or its contents. */
	class CustomInfoWindowAdapter implements InfoWindowAdapter {

		// These a both viewgroups containing an ImageView with id "badge" and
		// two TextViews with id
		// "title" and "snippet".
		private final View mWindow;
		private final View mContents;

		CustomInfoWindowAdapter() {
			mWindow = getActivity().getLayoutInflater().inflate(
					R.layout.custom_info_window, null);
			mContents = getActivity().getLayoutInflater().inflate(
					R.layout.custom_info_contents, null);

		}

		@Override
		public View getInfoWindow(Marker marker) {
			// if (mOptions.getCheckedRadioButtonId() !=
			// R.id.custom_info_window) {
			// // This means that getInfoContents will be called.
			// return null;
			// }
			render(marker, mWindow);
			return mWindow;
		}

		@Override
		public View getInfoContents(Marker marker) {
			// if (mOptions.getCheckedRadioButtonId() !=
			// R.id.custom_info_contents) {
			// // This means that the default info contents will be used.
			// return null;
			// }
			render(marker, mContents);
			return mContents;
		}

		private void render(Marker marker, View view) {
			int badge;
			// Use the equals() method on a Marker to check for equals. Do not
			// use ==.
			/*
			 * if (marker.equals(mBrisbane)) { badge = R.drawable.badge_qld; }
			 * else if (marker.equals(mAdelaide)) { badge = R.drawable.badge_sa;
			 * } else if (marker.equals(mSydney)) { badge =
			 * R.drawable.badge_nsw; } else if (marker.equals(mMelbourne)) {
			 * badge = R.drawable.badge_victoria; }
			 * 
			 * 
			 * else if (marker.equals(mPerth)) { badge = R.drawable.badge_wa; }
			 * 
			 * 
			 * else { // Passing 0 to setImageResource will clear the image
			 * view. badge = 0; }
			 */
			/*
			 * ((ImageView) view.findViewById(R.id.badge))
			 * .setImageResource(R.drawable.ferrari_logo_marker);
			 */

			String title = marker.getTitle();
			TextView titleUi = ((TextView) view.findViewById(R.id.title));
			if (title != null) {
				// Spannable string allows us to edit the formatting of the
				// text.
				SpannableString titleText = new SpannableString(title);
				/*
				 * titleText.setSpan(new ForegroundColorSpan(Color.RED), 0,
				 * titleText.length(), 0);
				 */
				titleUi.setText(titleText);
			} else {
				titleUi.setText("");
			}

			String snippet = marker.getSnippet();
			/*
			 * TextView snippetUi = ((TextView)
			 * view.findViewById(R.id.snippet)); if (snippet != null) {
			 * SpannableString snippetText = new SpannableString(snippet);
			 * snippetText.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0,
			 * 10, 0); snippetText.setSpan(new ForegroundColorSpan(Color.BLUE),
			 * 12, 21, 0); snippetUi.setText(snippetText); } else {
			 * snippetUi.setText(""); }
			 */
		}
	}

	private GoogleMap mMap;

	/*
	 * private Marker[] marker; private Marker mSydney; private Marker
	 * mBrisbane; private Marker mAdelaide; private Marker mMelbourne;
	 */
	public Marker[] mrker;
	String[] data;
	ListView lv;

	String searchString;

	private Marker mPerth;
	private Marker mSydney;
	private Marker mBrisbane;
	private Marker mAdelaide;
	private Marker mMelbourne;
	private TextView mTopText;

	public LatLng[] value;
	LocationServiceModel info;
	DealerShipApiCalling dealerApicaling;

	// Weather listdata[];

	ArrayList<Weather> listdata;
	double distance;
	ArrayList<HashMap<String, Object>> searchResults;

	// ArrayList that will hold the original Data
	ArrayList<HashMap<String, Object>> originalValues;
	TextView dt;
	double current_lat, current_long;
	List<LocationServiceModel> articleList1;

	// ArrayList<HashMap<String, Object>> searchResults;

	// ArrayList that will hold the original Data
	// ArrayList<HashMap<String, Object>> originalValues;

	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in
																		// Meters
	WeatherAdapter adapter;
	private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in
	private static final int DealerShips = 1;
	private static final int Service = 2;
	private static final int Merchandishing = 3;
	private static final int All = 4; // Milliseconds
	protected LocationManager locationManager;
	protected Button retrieveLocationButton;

	public boolean search_status = false;

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 *      android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		final View rootView = inflater.inflate(R.layout.newmarkerdemo, container,
				false);
		
		final View footerBg = (View)rootView.findViewById(R.id.footerbg);


		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		ActionItem nextItem = new ActionItem(DealerShips, "DealerShips",
				getResources().getDrawable(R.drawable.menu_down_arrow));
		ActionItem prevItem = new ActionItem(Service, "Service Centres",
				getResources().getDrawable(R.drawable.menu_up_arrow));
		ActionItem searchItem = new ActionItem(Merchandishing,
				"Merchandishing stores", getResources().getDrawable(
						R.drawable.menu_search));
		ActionItem infoItem = new ActionItem(All, "All", getResources()
				.getDrawable(R.drawable.menu_info));
		// create QuickAction. Use QuickAction.VERTICAL or
		// QuickAction.HORIZONTAL param to define layout
		// orientation
		final QuickAction quickAction = new QuickAction(getActivity(),
				QuickAction.VERTICAL);
		// add action items into QuickAction
		quickAction.addActionItem(nextItem, R.drawable.modal_filter_top_option);
		quickAction.addActionItem(prevItem,
				R.drawable.modal_filter_middle_option);
		quickAction.addActionItem(searchItem,
				R.drawable.modal_filter_middle_option);
		quickAction.addActionItem(infoItem,
				R.drawable.modal_filter_middle_option);

		// Set listener for action item clicked
		quickAction
				.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos,
							int actionId) {
						ActionItem actionItem = quickAction.getActionItem(pos);
						// here we can filter which action item was clicked with
						// pos or actionId parameter
						if (actionId == DealerShips) {

							// Toast.makeText(getActivity().getApplicationContext(),
							// "Let's do some search action",
							// Toast.LENGTH_SHORT).show();

							FilterArraylist(listdata, "dealer");

							quickAction.dismiss();

						}

						else if (actionId == Service) {

							FilterArraylist(listdata, "service");
							quickAction.dismiss();

							// Toast.makeText(getActivity().getApplicationContext(),
							// "I have no info this time",
							// Toast.LENGTH_SHORT).show();
						}

						else if (actionId == Merchandishing) {

							FilterArraylist(listdata, "merchandising");
							quickAction.dismiss();

							// Toast.makeText(getActivity().getApplicationContext(),
							// "I have no info this time",
							// Toast.LENGTH_SHORT).show();
						} else {

							FilterArraylist(listdata, "all");
							quickAction.dismiss();
							// Toast.makeText(getActivity().getApplicationContext(),
							// actionItem.getTitle() + " selected",
							// Toast.LENGTH_SHORT).show();
						}
					}
				});

		quickAction.setOnDismissListener(new QuickAction.OnDismissListener() {
			@Override
			public void onDismiss() {
//				Toast.makeText(getActivity().getApplicationContext(),
//						"Dismissed", Toast.LENGTH_SHORT).show();
			}
		});

		ImageView footer_ooption_btn = (ImageView) rootView
				.findViewById(R.id.footer_options_btn);

		
		
		footer_ooption_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				quickAction.show(footerBg);

			}
		});

		final EditText searchBox = (EditText) rootView
				.findViewById(R.id.searchBox);

		ImageView footer_search_btn = (ImageView) rootView
				.findViewById(R.id.footer_search_btn);

		footer_search_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				search_status = !search_status;
				if (search_status)
					searchBox.setVisibility(View.VISIBLE);
				else
					searchBox.setVisibility(View.GONE);

			}
		});

		locationManager = (LocationManager) getActivity().getSystemService(
				Context.LOCATION_SERVICE);

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				MINIMUM_TIME_BETWEEN_UPDATES,
				MINIMUM_DISTANCE_CHANGE_FOR_UPDATES, new MyLocationListener());

		showCurrentLocation();

		myLocation = new LatLng(current_lat, current_long);

		lv = (ListView) rootView.findViewById(R.id.list);
		dt = (TextView) rootView.findViewById(R.id.dist);

		// addMarkersToMap();

		// originalValues = new ArrayList<HashMap<String, Object>>();

		// temporary HashMap for populating the
		// Items in the ListView

		dealerApicaling = new DealerShipApiCalling();
		dealerApicaling.setArticleListener(new SuccessDealerShipListener() {
			public void listOfArticle(List<LocationServiceModel> articleList) {
				// TODO Auto-generated method stub

				data = new String[articleList.size()];

				value = new LatLng[articleList.size()];

				// listdata = new Weather[articleList.size()];
				listdata = new ArrayList<Weather>(articleList.size());

				articleList1 = articleList;
				mrker = new Marker[articleList1.size()];

				for (int i = 0; i < data.length; i++) {
					info = articleList.get(i);
					data[i] = new String(info.address);

					Location locationA = new Location("point A");
					locationA.setLatitude(current_long);
					locationA.setLongitude(current_lat);
					Location locationB = new Location("point B");
					locationB.setLatitude(Double.parseDouble(articleList1
							.get(i).lat));
					locationB.setLongitude(Double.parseDouble(articleList1
							.get(i).lon));

					distance = locationA.distanceTo(locationB);
					distance = distance / 1609.34;
					listdata.add(new Weather(info.service_name, info.services,
							info.address, info.dealership, (int) distance,
							Double.parseDouble(articleList1.get(i).lat), Double
									.parseDouble(articleList1.get(i).lon),
							articleList1.get(i).merchandising));

					value[i] = new LatLng(Double.parseDouble(info.lat), Double
							.parseDouble(info.lon));
				}

			}

			@Override
			public void failedToGet(String message) {
				// TODO Auto-generated method stub

				// AlertDialog dialog =new
				// AlertDialog.Builder(getActivity()).create();
				//
				// dialog.setTitle("Sucess");
				// dialog.setMessage(message);
				// dialog.show();
			}

		});

		/*
		 * ArrayAdapter<String> ar = new ArrayAdapter<String>(getActivity()
		 * .getApplicationContext(), android.R.layout.simple_list_item_1, data);
		 */

		dealerApicaling.getDealerShip();

		// List<String>list = Arrays.asList(listdata);

		// total number of rows in the ListView
		// Log.i("Size= ", String.valueOf(articleList1.size()));

		/*
		 * for (int i = 0; i < articleList1.size(); i++) { temp = new
		 * HashMap<String, Object>();
		 * 
		 * temp.put("servicename", listdata.get(i).servicename);
		 * temp.put("Address", listdata.get(i).Address); temp.put("lat",
		 * String.valueOf(listdata.get(i).lat)); temp.put("longi",
		 * String.valueOf(listdata.get(i).longi)); temp.put("Dealer_Status",
		 * listdata.get(i).Dealer_Status); temp.put("Service_Status",
		 * listdata.get(i).Service_Status);
		 * 
		 * // add the row to the ArrayList originalValues.add(temp); } //
		 * searchResults=OriginalValues initially searchResults = new
		 * ArrayList<HashMap<String, Object>>(originalValues);
		 */
		/*
		 * Collections.sort(listdata, new Comparator<Weather>() { public int
		 * compare(Weather lhs, Weather rhs) { return
		 * Integer.valueOf(lhs.distance).compareTo(
		 * Integer.valueOf(rhs.distance)); } });
		 */

		originalValues = new ArrayList<HashMap<String, Object>>();

		// temporary HashMap for populating the
		// Items in the ListView
		HashMap<String, Object> temp;

		// total number of rows in the ListView
		int noOfPlayers = data.length;
		
		Collections.sort(listdata, new Comparator<Weather>() {
			public int compare(Weather lhs, Weather rhs) {
				return Integer.valueOf(lhs.distance).compareTo(
						Integer.valueOf(rhs.distance));
			}
		});


		for (int i = 0; i < noOfPlayers; i++) {
			temp = new HashMap<String, Object>();

			temp.put("servicename", listdata.get(i).servicename);
			temp.put("Service_Status", listdata.get(i).Service_Status);
			temp.put("Dealer_Status", listdata.get(i).Dealer_Status);
			temp.put("Address", listdata.get(i).Address);
			temp.put("lat", listdata.get(i).lat);
			temp.put("longi", listdata.get(i).longi);
			temp.put("merchandising", articleList1.get(i).merchandising);

			// add the row to the ArrayList
			originalValues.add(temp);
		}
		// searchResults=OriginalValues initially
		searchResults = new ArrayList<HashMap<String, Object>>(originalValues);

		
		Location locationA = new Location("point A");

		locationA.setLatitude(current_long);

		locationA.setLongitude(current_lat);

		Location locationB = new Location("point B");

		/*
		 * locationB.setLatitude((listdata.get(0).lat));
		 * 
		 * locationB.setLongitude((listdata.get(0).longi));
		 */

		locationB.setLatitude(listdata.get(0).lat);

		locationB.setLongitude(listdata.get(0).longi);

		distance = locationA.distanceTo(locationB);

		dt.setText("Near You - "
				+ String.valueOf((int) (distance / 1609.34) + " miles"));

		adapter = new WeatherAdapter(getActivity(), R.layout.maplistrow,
				searchResults);

		lv.setAdapter(adapter);

		searchBox.addTextChangedListener(new TextWatcher() {

			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// get the text in the EditText
				String searchString = searchBox.getText().toString();
				int textLength = searchString.length();
				searchResults.clear();

				for (int i = 0; i < originalValues.size(); i++) {
					String playerName = originalValues.get(i)
							.get("servicename").toString();
					String address = originalValues.get(i).get("Address")
							.toString();
					if (textLength <= playerName.length()
							|| textLength <= address.length()) {
						// compare the String in EditText with Names in the
						// ArrayList
						// if
						// (searchString.equalsIgnoreCase(playerName.substring(
						// 0, textLength)))
						//
						if (Pattern
								.compile(searchString, Pattern.CASE_INSENSITIVE)
								.matcher(playerName).find()
								|| Pattern
										.compile(searchString,
												Pattern.CASE_INSENSITIVE)
										.matcher(address).find())

							searchResults.add(originalValues.get(i));

						/*
						 * if (searchString.contains(playerName.substring( 0,
						 * textLength)))
						 * searchResults.add(originalValues.get(i));
						 */
					}

					Collections.sort(listdata, new Comparator<Weather>() {
						public int compare(Weather lhs, Weather rhs) {
							return Integer.valueOf(lhs.distance).compareTo(
									Integer.valueOf(rhs.distance));
						}
					});
				}

				adapter.notifyDataSetChanged();
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			public void afterTextChanged(Editable s) {

			}
		});

		/*
		 * searchBox.addTextChangedListener(new TextWatcher() {
		 * 
		 * public void onTextChanged(CharSequence s, int start, int before, int
		 * count) { // get the text in the EditText String searchString =
		 * searchBox.getText().toString(); int textLength =
		 * searchString.length(); searchResults.clear();
		 * 
		 * for (int i = 0; i < originalValues.size(); i++) { String playerName =
		 * originalValues.get(i) .get("servicename").toString(); if (textLength
		 * <= playerName.length()) { // compare the String in EditText with
		 * Names in the // ArrayList if
		 * (searchString.equalsIgnoreCase(playerName.substring( 0, textLength)))
		 * searchResults.add(originalValues.get(i)); } }
		 * 
		 * adapter.notifyDataSetChanged(); }
		 * 
		 * public void beforeTextChanged(CharSequence s, int start, int count,
		 * int after) {
		 * 
		 * }
		 * 
		 * public void afterTextChanged(Editable s) {
		 * 
		 * } });
		 */
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub

				Location locationA = new Location("point A");

				locationA.setLatitude(current_long);

				locationA.setLongitude(current_lat);

				Location locationB = new Location("point B");

				// locationB.setLatitude(listdata.get(arg2).lat);
				//
				// locationB.setLongitude(listdata.get(arg2).longi);

				locationB.setLatitude(Double.parseDouble(searchResults
						.get(arg2).get("lat").toString()));

				locationB.setLongitude(Double.parseDouble(searchResults
						.get(arg2).get("longi").toString()));

				distance = locationA.distanceTo(locationB);

				// Toast.makeText(getActivity(),
				// String.valueOf(distance / 1609.34),
				// Toast.LENGTH_LONG).show();

				dt.setText("Near You - "
						+ String.valueOf((int) (distance / 1609.34) + " miles"));

				/*
				 * mMap.addMarker(new MarkerOptions().position( new
				 * LatLng(Double.parseDouble(searchResults
				 * .get(arg2).get("lat").toString()),
				 * Double.parseDouble(searchResults
				 * .get(arg2).get("lat").toString()))).title(
				 * listdata.get(arg2).servicename));
				 */

				LatLng lg = new LatLng(Double.parseDouble(searchResults
						.get(arg2).get("lat").toString()), (Double
						.parseDouble(searchResults.get(arg2).get("longi")
								.toString())));

				/*
				 * LatLng lg = new LatLng(Double.parseDouble(searchResults
				 * .get(arg2).get("lat").toString()),
				 * Double.parseDouble(searchResults
				 * .get(arg2).get("lat").toString()));
				 */

				LatLngBounds bounds = new LatLngBounds.Builder().include(lg)
						.build();

				mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));

				mMap.moveCamera(CameraUpdateFactory.zoomTo(10));

			}
		});

		try {
			MapsInitializer.initialize(getActivity());
		} catch (GooglePlayServicesNotAvailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setUpMapIfNeeded();

		if (container == null) {
			// We have different layouts, and in one of them this
			// fragment's containing frame doesn't exist. The fragment
			// may still be created from its saved state, but there is
			// no reason to try to create its view hierarchy because it
			// won't be displayed. Note this is not needed -- we could
			// just run the code below, where we would create and return
			// the view hierarchy; it would just never be used.
			return rootView;
		}

		return rootView;
	}

	private void setUpMapIfNeeded() {
		// Do a null check to confirm that we have not already instantiated the
		// map.
		if (mMap == null) {
			// Try to obtain the map from the SupportMapFragment.
			mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(
					R.id.map)).getMap();
			// Check if we were successful in obtaining the map.
			if (mMap != null) {
				setUpMap();
			}
		}
	}

	private void setUpMap() {

		// Hide the zoom controls as the button panel will cover it.
		mMap.getUiSettings().setZoomControlsEnabled(true);
		// mMap.getUiSettings().setMyLocationButtonEnabled(true);

		// Add lots of markers to the map.

		addMarkersToMap();

		// Log.i("Marker Size", String.valueOf(marker.length));

		// Setting an info window adapter allows us to change the both the
		// contents and look of the
		// info window.
		mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

		// Set listeners for marker events. See the bottom of this class for
		// their behavior.
		mMap.setOnMarkerClickListener(this);
		mMap.setOnInfoWindowClickListener(this);
		mMap.setOnMarkerDragListener(this);

		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		// mMap.clear();

		// Pan to see all markers in view.
		// Cannot zoom to bounds until the map has a size.
		final View mapView = getFragmentManager().findFragmentById(R.id.map)
				.getView();

		if (mapView.getViewTreeObserver().isAlive()) {
			mapView.getViewTreeObserver().addOnGlobalLayoutListener(
					new OnGlobalLayoutListener() {
						@SuppressLint("NewApi")
						// We check which build version we are using.
						@Override
						public void onGlobalLayout() {
							int zoomLevel = (int) mMap.getMinZoomLevel();

							LatLngBounds bounds = new LatLngBounds.Builder()
									.include(NY).include(SFO).include(LONDON)
									.include(BRISBANE).include(myLocation)
									.build();
							// .include(PERTH).include(SYDNEY)
							// .include(ADELAIDE).include(BRISBANE)
							// .include(MELBOURNE).build();

							// mMap.clear();
							// mMap.getMinZoomLevel();

							if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
								mapView.getViewTreeObserver()
										.removeGlobalOnLayoutListener(this);
							} else {
								mapView.getViewTreeObserver()
										.removeOnGlobalLayoutListener(this);
							}
							mMap.moveCamera(CameraUpdateFactory
									.newLatLngBounds(bounds, zoomLevel));

						}
					});

		}
	}

	private void addMarkersToMap() {
		// for (int i = 0; i < mrker.length; i++) {

		/*
		 * mrker[i] = mMap.addMarker(new MarkerOptions() .position(BRISBANE)
		 * .title("Brisbane") .snippet("Population: 2,074,200")
		 * .icon(BitmapDescriptorFactory
		 * .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		 */

		mMap.clear();
		for (int i = 0; i < mrker.length; i++) {

			/*
			 * Log.i("servivcename", listdata.get(i).servicename) ;
			 * Log.i("value", value[i].toString()) ;
			 */
			// mMap.addMarker(new MarkerOptions()
			// .position(value[i])
			// .title(listdata.get(i).servicename)
			// .icon(BitmapDescriptorFactory
			// .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

			mMap.addMarker(new MarkerOptions()
					.position(value[i])
					.title(listdata.get(i).servicename)
					.icon(BitmapDescriptorFactory
							.fromResource(R.drawable.marker_user_location)));

			/*
			 * String sn = listdata.get(i).servicename; LatLng pos = new
			 * LatLng(listdata.get(i).lat, listdata.get(i).longi);
			 * 
			 * mrker[i] = mMap.addMarker(new
			 * MarkerOptions().position(pos).title( "test"));
			 */

		}

		// Marker m = mMap.addMarker(new
		// MarkerOptions().position(BRISBANE).title("Indore"));

		// }

		// Uses a colored icon.
		/*
		 * mBrisbane = mMap.addMarker(new MarkerOptions() .position(BRISBANE)
		 * .title("Brisbane") .snippet("Population: 2,074,200")
		 * .icon(BitmapDescriptorFactory
		 * .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
		 * 
		 * // Uses a custom icon. mSydney = mMap.addMarker(new
		 * MarkerOptions().position(SYDNEY)
		 * .title("Sydney").snippet("Population: 4,627,300")
		 * .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
		 * 
		 * // Creates a draggable marker. Long press to drag. mMelbourne =
		 * mMap.addMarker(new MarkerOptions().position(MELBOURNE)
		 * .title("Melbourne").snippet("Population: 4,137,400")
		 * .draggable(true));
		 * 
		 * // A few more markers for good measure. mPerth = mMap.addMarker(new
		 * MarkerOptions().position(PERTH)
		 * .title("Perth").snippet("Population: 1,738,800")); mAdelaide =
		 * mMap.addMarker(new MarkerOptions().position(ADELAIDE)
		 * .title("Adelaide").snippet("Population: 1,213,000"));
		 * 
		 * // Creates a marker rainbow demonstrating how to create default
		 * marker // icons of different // hues (colors). int
		 * numMarkersInRainbow = 12; for (int i = 0; i < numMarkersInRainbow;
		 * i++) { mMap.addMarker(new MarkerOptions() .position( new LatLng(-30 +
		 * 10 Math.sin(i * Math.PI / (numMarkersInRainbow - 1)), 135 - 10 *
		 * Math.cos(i * Math.PI / (numMarkersInRainbow - 1)))) .title("Marker "
		 * + i) .icon(BitmapDescriptorFactory.defaultMarker(i * 360 /
		 * numMarkersInRainbow))); }
		 */
	}

	/*
	 * private void addMarkersToMap() { // Uses a colored icon.
	 * 
	 * for (int i = 0; i < marker.length; i++) {
	 * 
	 * Log.i("Size=", listdata.get(i).servicename); Log.i("Size=",
	 * value[i].toString());
	 * 
	 * 
	 * marker[i] = mMap.addMarker(new MarkerOptions() .position(value[i])
	 * .title(searchResults.get(i).get("service_name").toString())
	 * .snippet("Population: 2,074,200") .icon(BitmapDescriptorFactory
	 * .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
	 * 
	 * 
	 * marker[i] = mMap.addMarker(new MarkerOptions() .position(value[i])
	 * .title(listdata.get(i).servicename) .snippet("Population: 2,074,200")
	 * .icon(BitmapDescriptorFactory
	 * .defaultMarker(BitmapDescriptorFactory.HUE_RED))); }
	 * 
	 * }
	 */
	/*
	 * marker[i] = mMap.addMarker(new MarkerOptions() .position(value[i])
	 * .title(articleList1.get(i).service_name)
	 * .snippet("Population: 2,074,200") .icon(BitmapDescriptorFactory
	 * .fromResource(R.drawable.marker_user_location))); }
	 * 
	 * 
	 * marker[i] = mMap.addMarker(new MarkerOptions() .position(value[i])
	 * .title(articleList1.get(i).service_name)
	 * .snippet("Population: 2,074,200") .icon(BitmapDescriptorFactory
	 * .defaultMarker(BitmapDescriptorFactory.HUE_RED)));
	 * 
	 * 
	 * }
	 * 
	 * 
	 * mBrisbane = mMap.addMarker(new MarkerOptions() .position(BRISBANE)
	 * .title("Brisbane") .snippet("Population: 2,074,200")
	 * .icon(BitmapDescriptorFactory
	 * .defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	 * 
	 * // Uses a custom icon. mSydney = mMap.addMarker(new
	 * MarkerOptions().position(SYDNEY)
	 * .title("Sydney").snippet("Population: 4,627,300")
	 * .icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow)));
	 * 
	 * // Creates a draggable marker. Long press to drag. mMelbourne =
	 * mMap.addMarker(new MarkerOptions().position(MELBOURNE)
	 * .title("Melbourne").snippet("Population: 4,137,400") .draggable(true));
	 * 
	 * // A few more markers for good measure. mPerth = mMap.addMarker(new
	 * MarkerOptions().position(PERTH)
	 * .title("Perth").snippet("Population: 1,738,800")); mAdelaide =
	 * mMap.addMarker(new MarkerOptions().position(ADELAIDE)
	 * .title("Adelaide").snippet("Population: 1,213,000"));
	 * 
	 * // Creates a marker rainbow demonstrating how to create default marker //
	 * icons of different // hues (colors).
	 * 
	 * int numMarkersInRainbow = 12; for (int i = 0; i < numMarkersInRainbow;
	 * i++) { mMap.addMarker(new MarkerOptions() .position( new LatLng(-30 + 10
	 * Math.sin(i * Math.PI / (numMarkersInRainbow - 1)), 135 - 10 * Math.cos(i
	 * * Math.PI / (numMarkersInRainbow - 1)))) .title("Marker " + i)
	 * .icon(BitmapDescriptorFactory.defaultMarker(i * 360 /
	 * numMarkersInRainbow))); }
	 */

	@Override
	public void onMarkerDrag(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragEnd(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMarkerDragStart(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInfoWindowClick(Marker marker) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		// TODO Auto-generated method stub
		/*
		 * if (marker.equals(marker)) { final Handler handler = new Handler();
		 * final long start = SystemClock.uptimeMillis(); Projection proj =
		 * mMap.getProjection(); Point startPoint =
		 * proj.toScreenLocation(PERTH); startPoint.offset(0, -100); final
		 * LatLng startLatLng = proj.fromScreenLocation(startPoint); final long
		 * duration = 1500;
		 * 
		 * final Interpolator interpolator = new BounceInterpolator();
		 * 
		 * handler.post(new Runnable() {
		 * 
		 * @Override public void run() { long elapsed =
		 * SystemClock.uptimeMillis() - start; float t =
		 * interpolator.getInterpolation((float) elapsed / duration); double lng
		 * = t * PERTH.longitude + (1 - t) startLatLng.longitude; double lat = t
		 * * PERTH.latitude + (1 - t) startLatLng.latitude;
		 * marker.setPosition(new LatLng(lat, lng));
		 * 
		 * if (t < 1.0) { // Post again 16ms later. handler.postDelayed(this,
		 * 16); } } }); }
		 */
		// We return false to indicate that we have not consumed the event and
		// that we wish
		// for the default behavior to occur (which is for the camera to move
		// such that the
		// marker is centered and for the marker's info window to open, if it
		// has one).
		return false;
	}

	protected void showCurrentLocation() {

		Location location = locationManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);

		if (location != null) {
			/*
			 * String message = String.format(
			 * "Current Location \n Longitude: %1$s \n Latitude: %2$s",
			 * location.getLongitude(), location.getLatitude());
			 * Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
			 */
			current_lat = location.getLongitude();
			current_long = location.getLatitude();
		} else {

			current_lat = 51.4775;
			current_long = 0.4614;

		}

	}

	private class MyLocationListener implements LocationListener {

		public void onLocationChanged(Location location) {
			String message = String.format(
					"New Location \n Longitude: %1$s \n Latitude: %2$s",
					location.getLongitude(), location.getLatitude());
		}

		public void onStatusChanged(String s, int i, Bundle b) {
			
		}

		public void onProviderDisabled(String s) {
			Toast.makeText(getActivity(),
					"Provider disabled by the user. GPS turned off",
					Toast.LENGTH_LONG).show();

			AlertDialog dialog = new AlertDialog.Builder(getActivity())
					.create();

			dialog.setTitle("Location Service is turned Off");
			dialog.setMessage("Please turn on location service to capture current location, otherwise the app uses the default location as London ");
			dialog.show();

		}

		public void onProviderEnabled(String s) {
			Toast.makeText(getActivity(),
					"Provider enabled by the user. GPS turned on",
					Toast.LENGTH_LONG).show();
		}

	}

	public void FilterArraylist(ArrayList<Weather> wh, String type)

	{

		ArrayList<Weather> temp = new ArrayList<Weather>();

		int id = 5;
		if (type.equalsIgnoreCase("dealer")) {
			id = 0;
		} else if (type.equalsIgnoreCase("service")) {
			id = 1;
		} else if (type.equalsIgnoreCase("merchandising")) {
			id = 2;
		}

		switch (id) {
		case 0:

			searchResults.clear();
			for (int i = 0; i < originalValues.size(); i++) {
				if (originalValues.get(i).get("Dealer_Status").toString()
						.equalsIgnoreCase("Yes")) {
						searchResults.add(originalValues.get(i));
				}
			}

			adapter.notifyDataSetChanged();
			break;

		case 1:
			searchResults.clear();

			for (int i = 0; i < wh.size(); i++) {
				if (originalValues.get(i).get("Service_Status").toString()
						.equalsIgnoreCase("Yes")) {
						searchResults.add(originalValues.get(i));
				}
			}
			adapter.notifyDataSetChanged();
			break;

		case 2:

			searchResults.clear();
			for (int i = 0; i < wh.size(); i++) {
				if (originalValues.get(i).get("merchandising").toString()
						.equalsIgnoreCase("Yes")) {

					searchResults.add(originalValues.get(i));

				}

			}
			adapter.notifyDataSetChanged();

			break;

		default:
			searchResults.clear();
			for (int i = 0; i < wh.size(); i++) {
				temp.add(wh.get(i));

				searchResults.add(originalValues.get(i));
			}
			adapter.notifyDataSetChanged();
			break;

		}

		
	}
	
	
	public class GetLocatService extends AsyncTask<URL, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
//			pDialog = new ProgressDialog(UILApplication.this);
//			pDialog.setMessage(Html
//			.fromHtml("<b>Wait</b><br/><br/>...Please Wait"));
//			pDialog.setIndeterminate(false);
//			pDialog.setCancelable(false);
//			pDialog.show();
}

@Override
protected Void doInBackground(URL... params) {
	// TODO Auto-generated method stub

	ArticleApicaling articleApiCalling = new ArticleApicaling();
	articleApiCalling.getArticle();
	Log.d("Done Back Ground", "Back Ground Task Done");
	return null;
}

@Override
protected void onPostExecute(Void v) {
	Log.d("Task Finished", "Task Finished");
	
	//pDialog.dismiss();
	
	
}

}
	

}
