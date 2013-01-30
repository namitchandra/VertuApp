package com.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.adapter.ArticlesAdapter;

public class ArticleListmodel {

	private String alternate_text_1;
	private String article_teaser;
	private String article_title;
	private String categories;
	private String coding;;
	private String concierge_email_activation;
	private String created;
	private String enddate;
	private Integer ext_id;
	private String gallery_description;
	private String image1;
	private String image_content_type;
	private String image_file_name;
	private Integer image_file_size;
	private String image_updated_at;
	private String image_url;
	private String images_videos;
	private String language;
	private String metadata_set;
	private String modified;
	private String name;
	private String opportunity;
	private String passion_point_category;
	private String product_code;

	public String getAlternate_text_1() {
		return alternate_text_1;
	}

	public void setAlternate_text_1(String alternate_text_1) {
		this.alternate_text_1 = alternate_text_1;
	}

	public String getArticle_teaser() {
		return article_teaser;
	}

	public void setArticle_teaser(String article_teaser) {
		this.article_teaser = article_teaser;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getCoding() {
		return coding;
	}

	public void setCoding(String coding) {
		this.coding = coding;
	}

	public String getConcierge_email_activation() {
		return concierge_email_activation;
	}

	public void setConcierge_email_activation(String concierge_email_activation) {
		this.concierge_email_activation = concierge_email_activation;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public Integer getExt_id() {
		return ext_id;
	}

	public void setExt_id(Integer ext_id) {
		this.ext_id = ext_id;
	}

	public String getGallery_description() {
		return gallery_description;
	}

	public void setGallery_description(String gallery_description) {
		this.gallery_description = gallery_description;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage_content_type() {
		return image_content_type;
	}

	public void setImage_content_type(String image_content_type) {
		this.image_content_type = image_content_type;
	}

	public String getImage_file_name() {
		return image_file_name;
	}

	public void setImage_file_name(String image_file_name) {
		this.image_file_name = image_file_name;
	}

	public Integer getImage_file_size() {
		return image_file_size;
	}

	public void setImage_file_size(Integer image_file_size) {
		this.image_file_size = image_file_size;
	}

	public String getImage_updated_at() {
		return image_updated_at;
	}

	public void setImage_updated_at(String image_updated_at) {
		this.image_updated_at = image_updated_at;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getImages_videos() {
		return images_videos;
	}

	public void setImages_videos(String images_videos) {
		this.images_videos = images_videos;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getMetadata_set() {
		return metadata_set;
	}

	public void setMetadata_set(String metadata_set) {
		this.metadata_set = metadata_set;
	}

	public String getModified() {
		return modified;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpportunity() {
		return opportunity;
	}

	public void setOpportunity(String opportunity) {
		this.opportunity = opportunity;
	}

	public String getPassion_point_category() {
		return passion_point_category;
	}

	public void setPassion_point_category(String passion_point_category) {
		this.passion_point_category = passion_point_category;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public String getPubdate() {
		return pubdate;
	}

	public void setPubdate(String pubdate) {
		this.pubdate = pubdate;
	}

	public String getPublished() {
		return published;
	}

	public void setPublished(String published) {
		this.published = published;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getReviewed() {
		return reviewed;
	}

	public void setReviewed(String reviewed) {
		this.reviewed = reviewed;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	private String pubdate;
	private String published;
	private String region;
	private String reviewed;
	private String uuid;

	private Bitmap image;

	private ArticlesAdapter sta;

	public Bitmap getImage() {
		return image;
	}

	public ArticlesAdapter getAdapter() {
		return sta;
	}

	public void setAdapter(ArticlesAdapter sta) {
		this.sta = sta;
	}

	public void loadImage(ArticlesAdapter sta) {
		// HOLD A REFERENCE TO THE ADAPTER
		this.sta = sta;
		if (image_url != null && !image_url.equals("")) {
			new ImageLoadTask().execute(image_url);
		}
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	// ASYNC TASK TO AVOID CHOKING UP UI THREAD
	private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

		@Override
		protected void onPreExecute() {
			// Log.i("ImageLoadTask", "Loading image...");
		}

		// param[0] is img url
		protected Bitmap doInBackground(String... param) {
			// Log.i("ImageLoadTask", "Attempting to load image URL: " +
			// param[0]);
			try {

				Bitmap b = getBitmapFromURL(param[0]);
				return b;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		protected void onProgressUpdate(String... progress) {
			// NO OP
		}

		protected void onPostExecute(Bitmap ret) {
			if (ret != null) {
				Log.i("ImageLoadTask", "Successfully loaded " + name + " image");
				image = ret;
				if (sta != null) {
					// WHEN IMAGE IS LOADED NOTIFY THE ADAPTER
					sta.notifyDataSetChanged();
				}
			} else {
				Log.e("ImageLoadTask", "Failed to load " + name + " image");
			}
		}
	}

}
