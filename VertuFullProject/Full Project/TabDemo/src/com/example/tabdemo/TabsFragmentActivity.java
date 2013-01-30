package com.example.tabdemo;

import java.util.HashMap;
import java.util.List;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.model.ArticleListmodel;
import com.slidingmenu.lib.SlidingMenu;

/**
 * @author mwho
 * 
 */

public class TabsFragmentActivity extends SherlockFragmentActivity implements
		TabHost.OnTabChangeListener, iRibbonMenuCallback {

	static List<ArticleListmodel> articleListArray;
	static List<ArticleListmodel> privalegesListArray;

	ViewPager vp;

	ListView article_list;
	ListView listView1;
	LinearLayout title;
	
	Button	home_btn;
	
	public boolean check=true;

	private RibbonMenuView rbmView;
	/*
	 * public TabsFragmentActivity() { super(R.string.hello_world); // TODO
	 * Auto-generated constructor stub }
	 */

	private int[] selectedHeader = { R.drawable.artical_2,
			R.drawable.privilegs_2, R.drawable.social_2, R.drawable.locate_s2 };
	private int[] unselectedHeader = { R.drawable.artical_1,
			R.drawable.privilegs_1, R.drawable.social_1, R.drawable.locate_s1 };

	private SlidingMenu menu;
	private TabHost mTabHost;
	private HashMap mapTabInfo = new HashMap();
	private TabInfo mLastTab = null;

	private class TabInfo {
		private String tag;
		private Class clss;
		private Bundle args;
		private Fragment fragment;

		TabInfo(String tag, Class clazz, Bundle args) {
			this.tag = tag;
			this.clss = clazz;
			this.args = args;
		}

	}

	class TabFactory implements TabContentFactory {

		private final Context mContext;

		/**
		 * @param context
		 */
		public TabFactory(Context context) {
			mContext = context;
		}

		/**
		 * (non-Javadoc)
		 * 
		 * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
		 */
		public View createTabContent(String tag) {
			View v = new View(mContext);
			v.setMinimumWidth(0);
			v.setMinimumHeight(0);
			return v;
		}

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Step 1: Inflate layout
		setContentView(R.layout.tabs_layout);
		
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		// setSlidingActionBarEnabled(false);

		vp = (ViewPager) findViewById(R.id.pager);
		vp.setId("VP".hashCode());
		vp.setAdapter(new ColorPagerAdapter(getSupportFragmentManager()));
		// setContentView(vp);

		// getSlidingMenu().attachToActivity(this, SlidingMenu.SLIDING_CONTENT);

		// Step 2: Setup TabHost
		initialiseTabHost(savedInstanceState);
		if (savedInstanceState != null) {
			mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); // set
																				// the
																				// tab
																				// as
																				// per
																				// the
																				// saved
																				// state
		}

		rbmView = (RibbonMenuView) findViewById(R.id.ribbonMenuView1);
		rbmView.setMenuClickCallback(this);
		rbmView.setMenuItems(R.menu.ribbon_menu);

		com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_title, null);
		actionBar.setCustomView(customActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		home_btn = (Button) findViewById(R.id.arrow);

	
	home_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();
				//img1.setVisibility(View.INVISIBLE);
				
				check=!check;
				if(check)
				home_btn.setBackgroundResource(R.drawable.title_with_arrow);
				else
				{
					home_btn.setBackgroundResource(R.drawable.title_withot_arrow);
				}
				rbmView.toggleMenu();
			}
		});

		

		

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
	 */
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("tab", mTabHost.getCurrentTabTag()); // save the tab
																// selected
		super.onSaveInstanceState(outState);
	}

	private void setSelectedTab(int tab) {

		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			// mTabHost.getTabWidget().setBackgroundColor(Color.parseColor("#000000"));
			if (i == tab) {
				mTabHost.getTabWidget().getChildAt(i)
						.setBackgroundResource(selectedHeader[i]);
			} else {

				mTabHost.getTabWidget().getChildAt(i)
						.setBackgroundResource(unselectedHeader[i]);

			}
			// TextView tv =
			// (TextView)mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			// tv.setTextColor(Color.parseColor("#ffffff"));
			// tv.setTextSize(9.0f);

		}

	}

	/**
	 * Step 2: Setup TabHost
	 */
	private void initialiseTabHost(Bundle args) {
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup();
		TabInfo tabInfo = null;

		TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("tab1").setIndicator(""), (tabInfo = new TabInfo(
				"tab1", LocateService.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("tab2").setIndicator(""), (tabInfo = new TabInfo(
				"tab2", PrivalegesFragment.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("tab3").setIndicator(""), (tabInfo = new TabInfo(
				"tab3", ArticleFragment.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost
				.newTabSpec("tab4").setIndicator(""), (tabInfo = new TabInfo(
				"tab4", ArticleFragment.class, args)));
		this.mapTabInfo.put(tabInfo.tag, tabInfo);
		// Default to first tab
		this.onTabChanged("Tab1");
		//

		mTabHost.setOnTabChangedListener(this);

		vp.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageSelected(int position) {
				switch (position) {
				case 0:
					// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					mTabHost.setCurrentTab(position);
					setSelectedTab(position);
					// actionBar.setSelectedNavigationItem(position);
					break;
				default:
					// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					// actionBar.setSelectedNavigationItem(position);
					mTabHost.setCurrentTab(position);
					setSelectedTab(position);

					break;
				}
			}

		});

		for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
			// mTabHost.getTabWidget().setBackgroundColor(Color.parseColor("#000000"));

			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(unselectedHeader[i]);

			// TextView tv =
			// (TextView)mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
			// tv.setTextColor(Color.parseColor("#ffffff"));
			// tv.setTextSize(9.0f);

		}
		mTabHost.getTabWidget().getChildAt(0)
				.setBackgroundResource(selectedHeader[0]);
		vp.setCurrentItem(0);

		// getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
	}

	/**
	 * @param activity
	 * @param tabHost
	 * @param tabSpec
	 * @param clss
	 * @param args
	 */
	private static void addTab(TabsFragmentActivity activity, TabHost tabHost,
			TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		// Attach a Tab view factory to the spec
		tabSpec.setContent(activity.new TabFactory(activity));
		String tag = tabSpec.getTag();

		// Check to see if we already have a fragment for this tab, probably
		// from a previously saved state. If so, deactivate it, because our
		// initial state is that a tab isn't shown.
		tabInfo.fragment = activity.getSupportFragmentManager()
				.findFragmentByTag(tag);
		if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
			FragmentTransaction ft = activity.getSupportFragmentManager()
					.beginTransaction();
			ft.detach(tabInfo.fragment);
			ft.commit();
			activity.getSupportFragmentManager().executePendingTransactions();
		}

		tabHost.addTab(tabSpec);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
	 */
	public void onTabChanged(String tag) {
		TabInfo newTab = (TabInfo) this.mapTabInfo.get(tag);
		int poss = mTabHost.getCurrentTab();
		vp.setCurrentItem(poss);
		setSelectedTab(poss);

		if (mLastTab != newTab) {
			FragmentTransaction ft = this.getSupportFragmentManager()
					.beginTransaction();
			if (mLastTab != null) {
				if (mLastTab.fragment != null) {
					ft.detach(mLastTab.fragment);
				}
			}
			if (newTab != null) {
				if (newTab.fragment == null) {
					newTab.fragment = Fragment.instantiate(this,
							newTab.clss.getName(), newTab.args);
					ft.add(R.id.realtabcontent, newTab.fragment, newTab.tag);
				} else {
					ft.attach(newTab.fragment);
				}
			}
			mLastTab = newTab;
			ft.commit();
			this.getSupportFragmentManager().executePendingTransactions();
		}
	}

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { //** Create an
	 * option menu from res/menu/items.xml
	 *//*
		 * getMenuInflater().inflate(R.menu.items, menu);
		 *//** Get the action view of the menu item whose id is search */
	/*
	 * View v = (View) menu.findItem(R.id.search).getActionView();
	 *//**
	 * Get the edit text from the action view EditText txtSearch = ( EditText
	 * ) v.findViewById(R.id.txt_search);
	 * 
	 * /** Setting an action listener
	 */
	/*
	 * txtSearch.setOnEditorActionListener(new OnEditorActionListener() {
	 * 
	 * @Override public boolean onEditorAction(TextView v, int actionId,
	 * KeyEvent event) { Toast.makeText(getBaseContext(), "Search : " +
	 * v.getText(), Toast.LENGTH_SHORT).show(); return false; } });
	 * 
	 * return super.onCreateOptionsMenu(menu); }
	 */

	public class ColorPagerAdapter extends FragmentPagerAdapter {

		public ColorPagerAdapter(FragmentManager fm) {
			super(fm);

		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				// The first section of the app is the most interesting -- it
				// offers
				// a launchpad into the other demonstrations in this example
				// application.
				return new ArticleFragment();
			case 1:
				// The first section of the app is the most interesting -- it
				// offers
				// a launchpad into the other demonstrations in this example
				// application.
				return new PrivalegesFragment();
			case 3:
				// The first section of the app is the most interesting -- it
				// offers
				// a launchpad into the other demonstrations in this example
				// application.
				return new Tab4Fragment();

			default:
				// The other sections of the app are dummy placeholders.
				Fragment fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, i + 1);
				fragment.setArguments(args);
				return fragment;
			}
		}
		@Override
		public int getCount() {
			return 4;
		}
		@Override
		public CharSequence getPageTitle(int position) {
			return "Section " + (position + 1);
		}
	}
	public static class DummySectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_section_dummy,
					container, false);
			Bundle args = getArguments();
			((TextView) rootView.findViewById(android.R.id.text1))
					.setText(getString(R.string.dummy_section_text,
							args.getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	@Override
	public void RibbonMenuItemClick(int itemId) {
		//
		// TODO Auto-generated method stub
		Intent i;
		check=!check;
		home_btn.setBackgroundResource(R.drawable.title_with_arrow);
		switch (itemId) {
		case R.id.ribbon_menu_home4:
			i = new Intent(TabsFragmentActivity.this, AboutusActivity.class);
			startActivity(i);
			break;
		case R.id.ribbon_menu_home2:
			i = new Intent(TabsFragmentActivity.this, F12Berlinitta.class);
			startActivity(i);
			break;
		case R.id.ribbon_menu_home3:
			i = new Intent(TabsFragmentActivity.this, InParthenerShip.class);
			startActivity(i);
			break;
		default:
			Toast.makeText(getApplicationContext(), "Click" + itemId,
					Toast.LENGTH_LONG).show();
			break;
		}
	}
}
