package com.example.tabdemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.model.ArticleListmodel;

interface SuccessArticleListener{
	
	void listOfArticle(List<ArticleListmodel> articleList);
	void failedToGet(String message);
}


public class ArticleApicaling {

	List<ArticleListmodel > articlemodelList;
	SuccessArticleListener successArticleListener;
	
	public void setArticleListener(SuccessArticleListener successArticleListener2){
		
		this.successArticleListener=successArticleListener2;
		
	}
	
	private void parseArrticle(JSONObject articleDic){
		
		ArticleListmodel articleList= new ArticleListmodel();
		
		try {
		
			articleList.setArticle_title(articleDic.getString("article_title"));
			articleList.setImage_url(articleDic.getString("image_url"));
			articleList.setPublished(articleDic.getString("published"));
			articleList.setCreated(articleDic.getString("created"));
			articleList.setEnddate(articleDic.getString("enddate"));
			articleList.setArticle_teaser(articleDic.getString("article_teaser"));
		
			
		//	if (((String)articleDic.getString("opportunity")).equalsIgnoreCase("privilege")){
				TabsFragmentActivity.privalegesListArray.add(articleList);

			//}else{
				TabsFragmentActivity.articleListArray.add(articleList);
			//}
		
				
				
		Log.d("Parsing -----", "       ----------------------------------------------      ");
		
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getArticle(){
		
		 JSONParser jsonParser= new JSONParser();
		 String jsonString   = jsonParser.getJSONFromUrl("http://vertuferrari.herokuapp.com/lang/en/articles");
		 TabsFragmentActivity.articleListArray = new ArrayList<ArticleListmodel>();
		TabsFragmentActivity.privalegesListArray = new ArrayList<ArticleListmodel>();
		 
		 JSONArray articleArray = null;
			try {
				articleArray	= new JSONArray(jsonString);
			} catch (Exception e) {
				
				Log.d("Parsing -----", "       ----------------------------------------------      ");
			}
			
			
			if(articleArray.length()>0){
				for(int i=0 ;i<articleArray.length();i++){
					JSONObject jsonObjArticle;
					try {
						jsonObjArticle = articleArray.getJSONObject(i);
						parseArrticle(jsonObjArticle);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
		}
		  
		  
		  
		  
	
	}
	
}
		
		


		

	
