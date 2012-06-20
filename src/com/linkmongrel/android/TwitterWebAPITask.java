package com.linkmongrel.android;

import java.util.ArrayList;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class TwitterWebAPITask extends AsyncTask<String, Integer, String>
{
	private ProgressDialog progDialog;
	private Context context;
	private TwitterActivity activity;
	private static final String debugTag = "LastFMWebAPITask";
	
	/**
	 * Construct a task
	 * @param activity
	 */
    public TwitterWebAPITask(TwitterActivity activity) {
		super();
		this.activity = activity;
		this.context = this.activity.getApplicationContext();
	}

	@Override
    protected void onPreExecute() {
        super.onPreExecute(); 
    	progDialog = ProgressDialog.show(this.activity, "Search", "Looking for tweets" , true, false);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
        	Log.d(debugTag,"Background:" + Thread.currentThread().getName());
        	String searchUrl = "http://search.twitter.com/search.json?q=@"
    				+ "android" + "&rpp=100&page=" + "1";

    		HttpClient client = new DefaultHttpClient();
    		HttpGet get = new HttpGet(searchUrl);

    		ResponseHandler<String> responseHandler = new BasicResponseHandler();

    		String responseBody = null;
    		try {
    			responseBody = client.execute(get, responseHandler);
    		} catch (Exception ex) {
    			ex.printStackTrace();
    		}
    		return responseBody;
        } catch (Exception e) {
            return new String();
        }
    }
    
    @Override
    protected void onPostExecute(String result) 
    {
    	
    	ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    	
    	progDialog.dismiss();
        if (result.length() == 0) {
//            this.activity.alert ("Unable to find track data. Try again later.");
            return;
        }
        
        try {
        	JSONObject jsonObject = new JSONObject(result);
    		JSONArray results = jsonObject.getJSONArray("results");
    		for (int i = 0; i < results.length(); i++) {
    			JSONObject t = results.getJSONObject(i);
    			Tweet tweet = new Tweet(((JSONObject) t).get("from_user")
    					.toString(), ((JSONObject) t).get("text").toString(),
    					((JSONObject) t).get("created_at").toString(),
    					((JSONObject) t).get("profile_image_url").toString());
    			tweets.add(tweet);
    		}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.activity.setTweets(tweets);
             
    }
}