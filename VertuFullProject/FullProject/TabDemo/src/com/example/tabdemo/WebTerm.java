package com.example.tabdemo;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockActivity;

public class WebTerm extends SherlockActivity  {

	public String Url="http://www.vertu.com/en/terms.aspx?device=handset#vertuDigitalserviceTerms";
	
	private RibbonMenuView rbmView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_term);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        
       WebView wb=(WebView) findViewById(R.id.webView1);
       wb.loadUrl(Url);
        
        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_title, null);
		actionBar.setCustomView(customActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		Button home_btn = (Button) findViewById(R.id.arrow);
		
		

		home_btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();

				Intent i = new Intent(WebTerm.this,TabsFragmentActivity.class);
				startActivity(i);
				
				//rbmView.toggleMenu();
			}
		});
		

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
}
}
