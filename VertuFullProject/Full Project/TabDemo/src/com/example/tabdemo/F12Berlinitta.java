package com.example.tabdemo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;

public class F12Berlinitta extends SherlockActivity implements OnTouchListener {

	ImageView img;
	ImageButton btn;
	ImageView redBtn;
	ImageView blackBtn;
	ImageView grayBtn;
	ImageView reflactionView;

	int selectedCar = 0;
	float historyX, historyY;
	int currentImage = 0;
	int[] Target_Image;
	int[] Black = new int[] { R.drawable.b_ferrari_f152_000_0000,
			R.drawable.b_ferrari_f152_000_0001, R.drawable.b_ferrari_f152_000_0002,
			R.drawable.b_ferrari_f152_000_0003, R.drawable.b_ferrari_f152_000_0004,
			R.drawable.b_ferrari_f152_000_0005, R.drawable.b_ferrari_f152_000_0006,
			R.drawable.b_ferrari_f152_000_0007, R.drawable.b_ferrari_f152_000_0008,
			R.drawable.b_ferrari_f152_000_0009, R.drawable.b_ferrari_f152_000_0010,
			R.drawable.b_ferrari_f152_000_0011, R.drawable.b_ferrari_f152_000_0012,
			R.drawable.b_ferrari_f152_000_0013, R.drawable.b_ferrari_f152_000_0014,
			R.drawable.b_ferrari_f152_000_0015, R.drawable.b_ferrari_f152_000_0016,
			R.drawable.b_ferrari_f152_000_0017, R.drawable.b_ferrari_f152_000_0018,
			R.drawable.b_ferrari_f152_000_0019, R.drawable.b_ferrari_f152_000_0020,
			R.drawable.b_ferrari_f152_000_0021, R.drawable.b_ferrari_f152_000_0022,
			R.drawable.b_ferrari_f152_000_0023, R.drawable.b_ferrari_f152_000_0024,
			R.drawable.b_ferrari_f152_000_0025, R.drawable.b_ferrari_f152_000_0026,
			R.drawable.b_ferrari_f152_000_0027, R.drawable.b_ferrari_f152_000_0028,
			R.drawable.b_ferrari_f152_000_0029, R.drawable.b_ferrari_f152_000_0030,
			R.drawable.b_ferrari_f152_000_0031, R.drawable.b_ferrari_f152_000_0032,
			R.drawable.b_ferrari_f152_000_0033, R.drawable.b_ferrari_f152_000_0034,
			R.drawable.b_ferrari_f152_000_0035, R.drawable.b_ferrari_f152_000_0036 };

	int[] Red = new int[] { R.drawable.r_ferrari_f152_000_0000,
			R.drawable.r_ferrari_f152_000_0001,
			R.drawable.r_ferrari_f152_000_0002,
			R.drawable.r_ferrari_f152_000_0003,
			R.drawable.r_ferrari_f152_000_0004,
			R.drawable.r_ferrari_f152_000_0005,
			R.drawable.r_ferrari_f152_000_0006,
			R.drawable.r_ferrari_f152_000_0007,
			R.drawable.r_ferrari_f152_000_0008,
			R.drawable.r_ferrari_f152_000_0009,
			R.drawable.r_ferrari_f152_000_0010,
			R.drawable.r_ferrari_f152_000_0011,
			R.drawable.r_ferrari_f152_000_0012,
			R.drawable.r_ferrari_f152_000_0013,
			R.drawable.r_ferrari_f152_000_0014,
			R.drawable.r_ferrari_f152_000_0015,
			R.drawable.r_ferrari_f152_000_0016,
			R.drawable.r_ferrari_f152_000_0017,
			R.drawable.r_ferrari_f152_000_0018,
			R.drawable.r_ferrari_f152_000_0019,
			R.drawable.r_ferrari_f152_000_0020,
			R.drawable.r_ferrari_f152_000_0021,
			R.drawable.r_ferrari_f152_000_0022,
			R.drawable.r_ferrari_f152_000_0023,
			R.drawable.r_ferrari_f152_000_0024,
			R.drawable.r_ferrari_f152_000_0025,
			R.drawable.r_ferrari_f152_000_0026,
			R.drawable.r_ferrari_f152_000_0027,
			R.drawable.r_ferrari_f152_000_0028,
			R.drawable.r_ferrari_f152_000_0029,
			R.drawable.r_ferrari_f152_000_0030,
			R.drawable.r_ferrari_f152_000_0031,
			R.drawable.r_ferrari_f152_000_0032,
			R.drawable.r_ferrari_f152_000_0033,
			R.drawable.r_ferrari_f152_000_0034,
			R.drawable.r_ferrari_f152_000_0035,
			R.drawable.r_ferrari_f152_000_0036 };
	
	
	int[] Silver = new int[] { R.drawable.s_ferrari_f152_000_0000,
			R.drawable.s_ferrari_f152_000_0001,
			R.drawable.s_ferrari_f152_000_0002,
			R.drawable.s_ferrari_f152_000_0003,
			R.drawable.s_ferrari_f152_000_0004,
			R.drawable.s_ferrari_f152_000_0005,
			R.drawable.s_ferrari_f152_000_0006,
			R.drawable.s_ferrari_f152_000_0007,
			R.drawable.s_ferrari_f152_000_0008,
			R.drawable.s_ferrari_f152_000_0009,
			R.drawable.s_ferrari_f152_000_0010,
			R.drawable.s_ferrari_f152_000_0011,
			R.drawable.s_ferrari_f152_000_0012,
			R.drawable.s_ferrari_f152_000_0013,
			R.drawable.s_ferrari_f152_000_0014,
			R.drawable.s_ferrari_f152_000_0015,
			R.drawable.s_ferrari_f152_000_0016,
			R.drawable.s_ferrari_f152_000_0017,
			R.drawable.s_ferrari_f152_000_0018,
			R.drawable.s_ferrari_f152_000_0019,
			R.drawable.s_ferrari_f152_000_0020,
			R.drawable.s_ferrari_f152_000_0021,
			R.drawable.s_ferrari_f152_000_0022,
			R.drawable.s_ferrari_f152_000_0023,
			R.drawable.s_ferrari_f152_000_0024,
			R.drawable.s_ferrari_f152_000_0025,
			R.drawable.s_ferrari_f152_000_0026,
			R.drawable.s_ferrari_f152_000_0027,
			R.drawable.s_ferrari_f152_000_0028,
			R.drawable.s_ferrari_f152_000_0029,
			R.drawable.s_ferrari_f152_000_0030,
			R.drawable.s_ferrari_f152_000_0031,
			R.drawable.s_ferrari_f152_000_0032,
			R.drawable.s_ferrari_f152_000_0033,
			R.drawable.s_ferrari_f152_000_0034,
			R.drawable.s_ferrari_f152_000_0035,
			R.drawable.s_ferrari_f152_000_0036 };


	int i = 0;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fberlinnta_activity);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_title, null);
		actionBar.setCustomView(customActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		final Button homeBtn = (Button) findViewById(R.id.arrow);
	
		homeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// homeBtn.setBackgroundResource(R.drawable.button);
				Intent i = new Intent(F12Berlinitta.this,
						TabsFragmentActivity.class);
				startActivity(i);
			}
		});
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		try {

			img = (ImageView) findViewById(R.id.carImage);
			redBtn = (ImageView) findViewById(R.id.redCarBtn);
			blackBtn = (ImageView) findViewById(R.id.blackCarBtn);
			grayBtn = (ImageView) findViewById(R.id.grayCarBtn);

			img.setOnTouchListener(this);

			// img.setImageBitmap(decodeSampledBitmapFromResource(getResources(),
			// R.id.carImage, 100, 100));

			Bitmap originalImage = BitmapFactory.decodeResource(getResources(),
					Red[0]);
			img.setImageBitmap(ReflactionImage.createReflectedImage(
					getApplicationContext(), originalImage));
			// comment by vivek
			// img.setBackgroundResource(R.drawable.r_ferrari_f152_000_0000);
			redBtn.setBackgroundResource(R.drawable.black_colour_selected);
			// reflaction(0);
		} catch (Exception e) {

			// AlertDialog alert = new
			// AlertDialog.Builder(getApplicationContext())
			// .create();
			// alert.setTitle("Exception");
			// alert.setMessage(e.getMessage().toString());
			// alert.show();

			// TODO: handle exception
		}

		// Target_Image=Black;

		redBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				selectedCar = 0;
				// img.setBackgroundResource(Red[currentImage]);

				Bitmap originalImage = BitmapFactory.decodeResource(
						getResources(), Red[currentImage]);
				img.setImageBitmap(ReflactionImage.createReflectedImage(
						getApplicationContext(), originalImage));

				blackBtn.setBackgroundResource(R.drawable.black_colour);
				redBtn.setBackgroundResource(R.drawable.red_colour_selected);
				grayBtn.setBackgroundResource(R.drawable.silver_colour);

			}
		});

		blackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				selectedCar = 1;
				// img.setBackgroundResource(Black[currentImage]);
				Bitmap originalImage = BitmapFactory.decodeResource(
						getResources(), Black[currentImage]);
				img.setImageBitmap(ReflactionImage.createReflectedImage(
						getApplicationContext(), originalImage));
				blackBtn.setBackgroundResource(R.drawable.black_colour_selected);
				redBtn.setBackgroundResource(R.drawable.red_colour);
				grayBtn.setBackgroundResource(R.drawable.silver_colour);
				// TODO Auto-generated method stub
			}
		});
		grayBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectedCar = 2;
				// img.setBackgroundResource(Black[currentImage]);
				Bitmap originalImage = BitmapFactory.decodeResource(
						getResources(), Silver[currentImage]);
				img.setImageBitmap(ReflactionImage.createReflectedImage(
						getApplicationContext(), originalImage));

				blackBtn.setBackgroundResource(R.drawable.black_colour);
				redBtn.setBackgroundResource(R.drawable.red_colour);
				grayBtn.setBackgroundResource(R.drawable.silver_colour_selected);
			}
		});
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			// Here u can write code which is executed after the user touch on
			// the screen
			historyX = event.getX();
			historyY = event.getY();

			break;
		}
		case MotionEvent.ACTION_UP: {
			// Here u can write code which is executed after the user release
			// the touch on the screen
			break;
		}
		case MotionEvent.ACTION_MOVE: {
			// Here u can write code which is executed when user move the finger
			// on the screen

			float currentX = event.getX();
			// float currentY=event.getY();

			if (currentX > historyX) {

				// move Right

				if (currentImage >= 36) {
					currentImage = 0;
				}

				if (selectedCar == 0) {
					// img.setBackgroundResource(Red[currentImage]);
					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Red[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));

				} else if (selectedCar == 1) {
					// img.setBackgroundResource(Black[currentImage]);

					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Black[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));

				} else {
					// img.setBackgroundResource(Black[currentImage]);
					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Silver[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));
				}

				historyX = currentX;
				currentImage++;

			} else if (currentX < historyX) {
				// Move Left
				if (currentImage <= 0) {
					currentImage = 36;
				}
				historyX = currentX;
				if (selectedCar == 0) {
					// img.setBackgroundResource(Red[currentImage]);

					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Red[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));

				} else if (selectedCar == 1) {
					// img.setBackgroundResource(Black[currentImage]);
					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Black[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));

				} else {
					Bitmap originalImage = BitmapFactory.decodeResource(
							getResources(), Silver[currentImage]);
					img.setImageBitmap(ReflactionImage.createReflectedImage(
							getApplicationContext(), originalImage));
				}

				currentImage--;
			}

		}
			break;

		}
		return true;

	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
		}
		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

}
