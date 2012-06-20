package com.linkmongrel.android;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class TwitterActivity extends Activity {

	private ArrayList<Tweet> tweets;
	private TwitterImageHelper imgFetcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twitter);
		
		TwitterWebAPITask twtTask = new TwitterWebAPITask(TwitterActivity.this);
        try {
        	twtTask.execute("blah");
        }
        catch (Exception e)
        {
        	twtTask.cancel(true);
        }

		this.imgFetcher = new TwitterImageHelper(this);
		ArrayList<Tweet> tweets = null;
		
	}

	public void setTweets(ArrayList<Tweet> tweets) {
		this.tweets = tweets;
		ListView listView = (ListView) findViewById(R.id.listview);
		listView.setAdapter(new TweetItemAdapter(this, imgFetcher,
				R.layout.listview, this.tweets));
	}

	
}