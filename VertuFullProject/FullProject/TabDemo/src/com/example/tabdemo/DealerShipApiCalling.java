package com.example.tabdemo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.model.ArticleListmodel;

interface SuccessDealerShipListener {

	void listOfArticle(List<LocationServiceModel> articleList);

	void failedToGet(String message);
}

public class DealerShipApiCalling {

	parserProxy json;
	List<LocationServiceModel> Locationservice;
	SuccessDealerShipListener successArticleListener;

	public void setDelegate(SuccessDealerShipListener delegate){
		
		successArticleListener = delegate;
		
		
	}
	
	public void setArticleListener(
			SuccessDealerShipListener successArticleListener2) {
		this.successArticleListener = successArticleListener2;

	}

	private void parseArrticle(JSONObject articleDic) {

		LocationServiceModel dealerList = new LocationServiceModel();
		Log.d("ParseArticle",
				"------------------------------------------------------");

		try {

			Log.d("Parsing ", articleDic.getString("address"));

			dealerList.setAddress(articleDic.getString("address"));
			dealerList.setCategories(articleDic.getString("categories"));

			dealerList.setCity(articleDic.getString("city"));
			dealerList.setCitybrief_subtitle_and_category(articleDic
					.getString("citybrief_subtitle_and_category"));
			dealerList.setLat(articleDic.getString("lat"));
			dealerList.setLon(articleDic.getString("lon"));

			dealerList.setDealership(articleDic.getString("dealership"));
			dealerList.setServices(articleDic.getString("services"));

			dealerList.setService_name(articleDic.getString("service_name"));

			dealerList.setMerchandising(articleDic.getString("merchandising"));

			dealerList.setExt_id(articleDic.getString("ext_id"));

			Locationservice.add(dealerList);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public DealerShipApiCalling() {

		json = new parserProxy();
	}

	public void getDealerShip() {

		Log.d("Get Article Method",
				"------------------------------------------------------");

		json.setOnGetResponselistener(new JsonRequestResponse() {
			@Override
			public void successJsonResponse(String key, String response) {
				// TODO Auto-generated method stub
				try {

					if (response.equalsIgnoreCase("Empty Value")) {

						successArticleListener.failedToGet("No Records");

						return;
					}

					Log.d("Convert To Object", key + response);
					JSONArray articleArray = null;
					try {

						articleArray = new JSONArray(response);

					} catch (Exception e) {

						Log.d("Failed To Object", key + e.getMessage());

						// TODO: handle exception
					}

					Locationservice = new ArrayList<LocationServiceModel>();

					if (articleArray.length() > 0) {

						for (int i = 0; i < articleArray.length(); i++) {
							JSONObject jsonObjArticle = articleArray
									.getJSONObject(i);
							parseArrticle(jsonObjArticle);
						}

						Log.d("After Confert", key);
						successArticleListener.listOfArticle(Locationservice);

					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void failedToGetJsonResponse(String key, String message) {
				// TODO Auto-generated method stub

			}
		});

		json.parse("http://vertuferrari.herokuapp.com/lang/en/dealerships",
				"get_dealership");
	}

}
