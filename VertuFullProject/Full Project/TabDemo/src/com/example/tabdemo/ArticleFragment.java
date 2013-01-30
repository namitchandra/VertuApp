package com.example.tabdemo;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.adapter.ArticlesAdapter;



/**
 * @author mwho
 *
 */
public class ArticleFragment extends Fragment implements WebServiceFinished {
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	ListView article_list;
	ProgressDialog pDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_section_launchpad, container, false);

		
		article_list = (ListView) rootView.findViewById(R.id.articlelist);
	if(TabsFragmentActivity.articleListArray ==null ){	
		UILApplication.getApplication().setDelegate(this);
		
		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage(Html
		.fromHtml("<b>Please Wait</b><br/><br/>...loading data"));
		pDialog.setIndeterminate(false);
		pDialog.setCancelable(false);
		pDialog.show();
		UILApplication.getApplication().callArticlApi();
		
	}
	else{
		
		ArticlesAdapter article_adapter = new ArticlesAdapter(
				getActivity(), R.layout.row_layout, TabsFragmentActivity.articleListArray);
		Log.d("add adepter", "success");
		article_list.setAdapter(article_adapter);		
	}
		
		
	
		article_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(),
						ArticleDetailActivity.class);
				i.putExtra("currentPosition", arg2);
				startActivity(i);
			}
		
		});
			
		return rootView;
	}

	@Override
	public void taskFinished() {
		
		ArticlesAdapter article_adapter = new ArticlesAdapter(
				getActivity(), R.layout.row_layout, TabsFragmentActivity.articleListArray);
		Log.d("add adepter", "success");
		article_list.setAdapter(article_adapter);
		// TODO Auto-generated method stub
		pDialog.dismiss();
	}
}