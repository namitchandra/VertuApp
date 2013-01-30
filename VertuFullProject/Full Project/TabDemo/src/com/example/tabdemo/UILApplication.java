package com.example.tabdemo;

import java.net.URL;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.text.Html;
import android.util.Log;

import com.nostra13.example.universalimageloader.downloader.ExtendedImageDownloader;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */


public class UILApplication extends Application {
	
	ProgressDialog pDialog;
	public static UILApplication application;
	private WebServiceFinished delegate;
	private WebServiceFinished delegate1;

	
	public void callArticlApi(){
		
		new GetArrticleList().execute();
		
		
	}
	
	public static UILApplication getApplication(){
		
		return application;
	}
	
	
	public void setDelegate(WebServiceFinished del){
		
		application.delegate =del;
		
	}
	
public void setDelegate1(WebServiceFinished del){
		
		application.delegate1 =del;
		
	}
	
	@Override
	public void onCreate() {
		super.onCreate();

		application=this;
		
		
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
			.threadPriority(Thread.NORM_PRIORITY - 2)
			.memoryCacheSize(2 * 1024 * 1024) // 2 Mb
			.denyCacheImageMultipleSizesInMemory()
			.discCacheFileNameGenerator(new Md5FileNameGenerator())
			.imageDownloader(new ExtendedImageDownloader(getApplicationContext()))
			.tasksProcessingOrder(QueueProcessingType.LIFO)
			.enableLogging() // Not necessary in common
			.build();
		// Initialize ImageLoader with configuration.
		ImageLoader.getInstance().init(config);
	}
	
	
	
	public class GetArrticleList extends AsyncTask<URL, Void, Void> {

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
	// NearMe(lat, longi);
	return null;
}

@Override
protected void onPostExecute(Void v) {
	Log.d("Task Finished", "Task Finished");

	
	//pDialog.dismiss();
	
	if(application.delegate!=null)
	application.delegate.taskFinished();
	if(application.delegate1!=null)
	application.delegate1.taskFinished();

}

}
	
	
	
	
}

 
