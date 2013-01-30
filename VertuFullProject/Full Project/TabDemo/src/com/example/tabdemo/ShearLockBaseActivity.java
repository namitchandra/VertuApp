package com.example.tabdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.actionbarsherlock.app.SherlockActivity;
import com.nostra13.universalimageloader.core.ImageLoader;


public abstract class ShearLockBaseActivity extends SherlockActivity {

	protected ImageLoader imageLoader2 = ImageLoader.getInstance();

	private boolean instanceStateSaved;

	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		instanceStateSaved = true;
	}

	@Override
	protected void onDestroy() {
		if (!instanceStateSaved) {
			imageLoader2.stop();
		}
		super.onDestroy();
	}
}
