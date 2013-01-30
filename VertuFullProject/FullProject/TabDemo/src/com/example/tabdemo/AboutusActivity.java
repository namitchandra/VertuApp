package com.example.tabdemo;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;


public class AboutusActivity extends SherlockActivity implements iRibbonMenuCallback {

	public String Url="http://www.vertu.com/en/terms.aspx?device=handset#vertuDigitalserviceTerms";
	
	private RibbonMenuView rbmView;
	TextView term;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutus);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
        term=(TextView) findViewById(R.id.term);
        
        rbmView = (RibbonMenuView) findViewById(R.id.ribbonMenuView1);
		rbmView.setMenuClickCallback(this);
		rbmView.setMenuItems(R.menu.ribbon_menu);
        
        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_title, null);
		actionBar.setCustomView(customActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		Button home_btn = (Button) findViewById(R.id.arrow);
		
		term.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent(AboutusActivity.this,WebTerm.class);
				startActivity(i);
			}
		});

		home_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();

				Intent i = new Intent(AboutusActivity.this,TabsFragmentActivity.class);
				startActivity(i);
				
				//rbmView.toggleMenu();
			}
		});
		

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
	@Override
	public void RibbonMenuItemClick(int itemId) {
		// TODO Auto-generated method stub
		
		Intent i;
		switch (itemId) {

		case R.id.ribbon_menu_home4:
			i = new Intent(AboutusActivity.this, AboutusActivity.class);
			startActivity(i);
			break;

		case R.id.ribbon_menu_home2:
			i = new Intent(AboutusActivity.this, F12Berlinitta.class);
			startActivity(i);
			break;
		case R.id.ribbon_menu_home3:
			i = new Intent(AboutusActivity.this, InParthenerShip.class);
			startActivity(i);
			break;

		default:
			Toast.makeText(getApplicationContext(), "Click" + itemId,
					Toast.LENGTH_LONG).show();
			break;
		}
		
	}

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_aboutus, menu);
        return true;
    }*/
}
