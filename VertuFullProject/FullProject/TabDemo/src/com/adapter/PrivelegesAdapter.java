package com.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tabdemo.ImageLoader;
import com.example.tabdemo.R;
import com.model.ArticleListmodel;

public class PrivelegesAdapter extends ArrayAdapter<ArticleListmodel> {

	int layoutResourceId;    
    Context context; 
    ImageLoader imageloader;
 List<ArticleListmodel> items = null;
	
	
    public PrivelegesAdapter(Context context, int layoutResourceId, List<ArticleListmodel> items) {
		super(context, layoutResourceId, items);
		// TODO Auto-generated constructor stub
		
		 Log.d("Holdert : ","Initailizw");
	        Log.d("Holdert : ",items.toString());
	       
	        this.items = items;
			this.layoutResourceId = layoutResourceId;
			this.context = context;
			imageloader=new ImageLoader(context);
			
			 Log.d("setArticleimage Url",items.get(1).getImage_url());
			Log.d("setArticletitle",items.get(1).getArticle_title());
	}

	
	
	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
        ViewHolder holder = null;
        Log.d("Holdert DGDASG : ",items.toString());

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new ViewHolder();
            holder.title = (TextView)row.findViewById(R.id.title);
            holder.carImageView = (ImageView)row.findViewById(R.id.carImageView);
            holder.createDate = (TextView)row.findViewById(R.id.ctreateDate);
            holder.endDate = (TextView)row.findViewById(R.id.endDate);

            row.setTag(holder);
        
		}   else {
            holder = (ViewHolder)row.getTag();
        }
        Log.d("Holdert : ",items.toString());
       
        
        
        
        ArticleListmodel info = this.items.get(position);
        holder.title.setText(info.getArticle_title());
        holder.createDate.setText("Start "+info.getCreated());
        holder.endDate.setText("End "+info.getEnddate());

      //  info.loadImage(this);
        
        Log.d("Image Url : ",""+info.getImage_url());
        
        holder.carImageView.setTag(""+info.getImage_url());
        imageloader.DisplayImage(""+info.getImage_url(), (Activity) context,
				holder.carImageView);
        
        
      /*  
        holder.pic.setImageBitmap(info.getImage());*/

        
        return row;
	}

	static class ViewHolder {
		TextView title;
		TextView createDate;
		TextView endDate;
		ImageView carImageView;
//		ImageView opus;
	}

}

