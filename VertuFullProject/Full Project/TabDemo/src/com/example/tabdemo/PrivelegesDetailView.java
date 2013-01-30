package com.example.tabdemo;

import java.util.List;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.model.ArticleListmodel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

public class PrivelegesDetailView  extends ShearLockBaseActivity {

	DisplayImageOptions options;
	ViewPager pager;
	Boolean isPressed;

	int currentImageNo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	

        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detaile_pagger);
        com.actionbarsherlock.app.ActionBar actionBar = getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		View customActionBarView = getLayoutInflater().inflate(
				R.layout.custom_title, null);
		actionBar.setCustomView(customActionBarView);
		actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

		Button img1 = (Button) findViewById(R.id.arrow);

		img1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();
				Intent i = new Intent(PrivelegesDetailView.this,TabsFragmentActivity.class);
				startActivity(i);
			}
		});
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		final ImageView left = (ImageView)findViewById(R.id.back_btn); 
		final ImageView right = (ImageView)findViewById(R.id.next_btn);
		
		left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();
				
				currentImageNo--;
					if(currentImageNo < 0){
					
						currentImageNo=0;
					}else{
						if(currentImageNo==0)
							left.setEnabled(false);
						right.setEnabled(true);
						isPressed =true;
						pager.setCurrentItem(currentImageNo);
					}
			}
		});
		
		right.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// toggle();

				currentImageNo++;
				if(currentImageNo > TabsFragmentActivity.privalegesListArray.size()-1 ){
					
				

					currentImageNo=TabsFragmentActivity.privalegesListArray.size()-1;
				}else{
					
					if(currentImageNo==TabsFragmentActivity.privalegesListArray.size()-1)
						right.setEnabled(false);
					left.setEnabled(true);
					isPressed =true;
					pager.setCurrentItem(currentImageNo);
					}
				}
		});
	
		Bundle bundle = getIntent().getExtras();
		
    	int currentImage = bundle.getInt("currentPosition");
    	currentImageNo =currentImage;
		options = new DisplayImageOptions.Builder()
			.showImageForEmptyUri(R.drawable.image_for_empty_url)
			.resetViewBeforeLoading()
			.cacheOnDisc()
			.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
			.bitmapConfig(Bitmap.Config.RGB_565)
			.displayer(new FadeInBitmapDisplayer(300))
			.build();
		
		pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(new ImagePagerAdapter(TabsFragmentActivity.privalegesListArray));
		pager.setCurrentItem(currentImageNo);
		
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

				if(arg0 >= 0 && arg0 < TabsFragmentActivity.privalegesListArray.size()){
					currentImageNo =arg0;
					if(currentImageNo<= 0){
						left.setEnabled(false);
					}else{
						
						left.setEnabled(true);
					}
					if(currentImageNo>= TabsFragmentActivity.privalegesListArray.size()-1){
						right.setEnabled(false);
						
					}else{
						
						right.setEnabled(true);

						
					}
				}
								
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			
			}
		});
		
		
		
    }
    
    
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getSupportMenuInflater().inflate(R.menu.activity_aboutus, menu);
//        return true;
//    }

  
    
    private class ImagePagerAdapter extends PagerAdapter {

		private List<ArticleListmodel> images;
		private LayoutInflater inflater;

		ImagePagerAdapter(List<ArticleListmodel> images) {
			this.images = images;
			inflater = getLayoutInflater();
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			View rootView = (View) object;
			ImageView imageView = (ImageView) rootView.findViewById(R.id.carImageView);
			BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
			if (drawable != null) {
				Bitmap bitmap = drawable.getBitmap();
				if (bitmap != null) {
					bitmap.recycle();
				}
			}
			((ViewPager) container).removeView(rootView);
		}

		@Override
		public void finishUpdate(View container) {
		}

		@Override
		public int getCount() {
			return images.size();
		}

		@Override
		public Object instantiateItem(View view, int position) {
			
			final View imageLayout = inflater.inflate(R.layout.article_detaile_view, null);
			final ImageView imageView = (ImageView) imageLayout.findViewById(R.id.carImageView);
			final ProgressBar spinner = (ProgressBar) imageLayout.findViewById(R.id.loading);
			final TextView activityname = (TextView) imageLayout.findViewById(R.id.activityname);
			final TextView title = (TextView) imageLayout.findViewById(R.id.title);
			final TextView createDate = (TextView) imageLayout.findViewById(R.id.createdate);
			final TextView endDate = (TextView) imageLayout.findViewById(R.id.enddate);
			final TextView text = (TextView) imageLayout.findViewById(R.id.text);

			activityname.setText("Privileges");
			title.setText(TabsFragmentActivity.privalegesListArray.get(position).getArticle_title());
			createDate.setText("Start " +TabsFragmentActivity.privalegesListArray.get(position).getCreated());
			endDate.setText("End " +TabsFragmentActivity.privalegesListArray.get(position).getEnddate());
			text.setText(TabsFragmentActivity.privalegesListArray.get(position).getArticle_teaser());

			String imageUrl = TabsFragmentActivity.privalegesListArray.get(position).getImage_url();
			imageLoader2.displayImage(imageUrl, imageView, options, new SimpleImageLoadingListener() {
				@Override
				public void onLoadingStarted() {
					spinner.setVisibility(View.VISIBLE);
				}
				@Override
				public void onLoadingFailed(FailReason failReason) {
					String message = null;
					switch (failReason) {
						case IO_ERROR:
							message = "Input/Output error";
							break;
						case OUT_OF_MEMORY:
							message = "Out Of Memory error";
							break;
						case UNKNOWN:
							message = "Unknown error";
							break;
					}
					Toast.makeText(PrivelegesDetailView.this, message, Toast.LENGTH_SHORT).show();
					spinner.setVisibility(View.GONE);
					
					
					imageView.setImageResource(android.R.drawable.ic_delete);
				}
				@Override
				public void onLoadingComplete(Bitmap loadedImage) {
					spinner.setVisibility(View.GONE);
					
					Bitmap bmp = Bitmap.createScaledBitmap(loadedImage,480, loadedImage.getHeight(), false);
					imageView.setImageBitmap(bmp);
				}
			});

			((ViewPager) view).addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view.equals(object);
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View container) {
		}
	}
   
  
}

