package com.example.tabdemo;


import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.adapter.PrivelegesAdapter;
import com.model.ArticleListmodel;

public class PrivalegesFragment extends Fragment implements WebServiceFinished {

	ListView privleges_list;
	ProgressDialog pDialog;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.tab_frag2_layout, container, false);
		
		
		privleges_list = (ListView) rootView.findViewById(R.id.priveleagesList);
		if(TabsFragmentActivity.privalegesListArray ==null ){	
			UILApplication.getApplication().setDelegate1(this);
			
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage(Html
			.fromHtml("<b>Please Wait</b><br/><br/>...loading data"));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
			UILApplication.getApplication().callArticlApi();
			
		}
		else{
		
			
			PrivelegesAdapter article_adapter = new PrivelegesAdapter(
					getActivity(), R.layout.privaleges_row_layout, TabsFragmentActivity.privalegesListArray);
			privleges_list.setAdapter(article_adapter);
			
		}
		privleges_list.setOnItemClickListener(new OnItemClickListener() {
		
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						// TODO Auto-generated method stub
					
						Intent i = new Intent(getActivity(),
								PrivelegesDetailView.class);
						i.putExtra("currentPosition", arg2);
						startActivity(i);
					}
				
				});
				
			
		return rootView;
	}
	
	
	@Override
	public void taskFinished() {
		// TODO Auto-generated method stub
		PrivelegesAdapter article_adapter = new PrivelegesAdapter(
				getActivity(), R.layout.privaleges_row_layout, TabsFragmentActivity.privalegesListArray);
		privleges_list.setAdapter(article_adapter);
		
		pDialog.dismiss();
	}
}


/**
 * @author mwho
 *
 */
