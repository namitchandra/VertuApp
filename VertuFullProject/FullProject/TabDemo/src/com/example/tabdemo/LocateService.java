package com.example.tabdemo;


import java.util.List;

import com.model.ArticleListmodel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;



/**
 * @author mwho
 *
 */
public class LocateService extends Fragment {
	/** (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	TextView dealer_text;
	DealerShipApiCalling dealerApicaling;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(
				R.layout.tab_frag1_layout, container, false);
		
		
		
		
		dealer_text = (TextView) rootView.findViewById(R.id.dealer);
		dealerApicaling = new DealerShipApiCalling();
		dealerApicaling.setArticleListener(new SuccessDealerShipListener() {
			public void listOfArticle(List<LocationServiceModel> articleList) {
				// TODO Auto-generated method stub

			

				LocationServiceModel info =articleList.get(0);
				dealer_text.setText(info.address);
				
			}

			@Override
			public void failedToGet(String message) {
				// TODO Auto-generated method stub

				// AlertDialog dialog =new
				// AlertDialog.Builder(getActivity()).create();
				//
				// dialog.setTitle("Sucess");
				// dialog.setMessage(message);
				// dialog.show();
			}

			
		});
		//articleApiCalling.getArticle();

		dealerApicaling.getDealerShip();
		
		
		if (container == null) {
            // We have different layouts, and in one of them this
            // fragment's containing frame doesn't exist.  The fragment
            // may still be created from its saved state, but there is
            // no reason to try to create its view hierarchy because it
            // won't be displayed.  Note this is not needed -- we could
            // just run the code below, where we would create and return
            // the view hierarchy; it would just never be used.
			//return (LinearLayout)inflater.inflate(R.layout.tab_frag1_layout, container, false);
			return rootView;
           
        }
		
		
		
		//return (LinearLayout)inflater.inflate(R.layout.tab_frag1_layout, container, false);
		return rootView;
		
		
		
		
		
		
		
		
		
		
		
	}
}