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



public class ArticlesAdapter extends ArrayAdapter<ArticleListmodel> {

    int layoutResourceId;    
    Context context; 
    ImageLoader imageloader;
 List<ArticleListmodel> items = null;
	
	public ArticlesAdapter(Context context, int layoutResourceId, List<ArticleListmodel> items) {
		
        super(context, layoutResourceId, items);
       
        Log.d("Holdert : ","Initailizw");
        Log.d("Holdert : ",items.toString());
       
        this.items = items;
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		imageloader=new ImageLoader(context);
		/*
		 Log.d("setArticleimage Url",items.get(1).getImage_url());
		Log.d("setArticletitle",items.get(1).getArticle_title());*/
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
            holder.name = (TextView)row.findViewById(R.id.name);
            holder.pic = (ImageView)row.findViewById(R.id.image);
            holder.date = (TextView)row.findViewById(R.id.date);

            row.setTag(holder);
        
		}   else {
            holder = (ViewHolder)row.getTag();
        }
        Log.d("Holdert : ",items.toString());
       
        
        
        
        ArticleListmodel info = this.items.get(position);
        holder.name.setText(info.getArticle_title());
        holder.date.setText(info.getPublished());
//        info.loadImage(this);
//        holder.pic.setImageBitmap(info.getImage());
        Log.d("Image Url : ",""+info.getImage_url());
        
        holder.pic.setTag(""+info.getImage_url());
        imageloader.DisplayImage(""+info.getImage_url(), (Activity) context,
				holder.pic);
        
        
      /*  
        holder.pic.setImageBitmap(info.getImage());*/

        
        return row;
	}

	static class ViewHolder {
		TextView name;
		TextView date;
		ImageView pic;
//		ImageView opus;
	}

}
