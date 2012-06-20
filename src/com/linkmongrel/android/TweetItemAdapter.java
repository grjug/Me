package com.linkmongrel.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TweetItemAdapter extends ArrayAdapter<Tweet> {
	private ArrayList<Tweet> tweets;
	private Activity activity;
	private TwitterImageHelper imgFetcher;

	public TweetItemAdapter(Activity a, TwitterImageHelper i,
			int textViewResourceId, ArrayList<Tweet> tweets) {
		super(a, textViewResourceId, tweets);
		this.tweets = tweets;
		activity = a;

		imgFetcher = i;
	}

	public static class ViewHolder {
		public TextView username;
		public TextView message;
		public TextView date;
		public ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		ViewHolder holder;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.listview, null);
			holder = new ViewHolder();
			holder.username = (TextView) v.findViewById(R.id.username);
			holder.message = (TextView) v.findViewById(R.id.message);
			holder.date = (TextView) v.findViewById(R.id.date);
			holder.image = (ImageView) v.findViewById(R.id.avatar);
			v.setTag(holder);
		} else
			holder = (ViewHolder) v.getTag();

		final Tweet tweet = tweets.get(position);
		if (tweet != null) {
			holder.username.setText(tweet.username);
			holder.message.setText(tweet.message);
			holder.date.setText(tweet.date);
			if (tweet.image_url != null) {
				holder.image.setTag(tweet.image_url);
				Bitmap bitmap = imgFetcher.loadImage(this, holder.image);
				if (bitmap != null) {
					holder.image.setImageBitmap(bitmap);
				}
			} else {
				holder.image.setImageResource(R.drawable.ic_launcher);
			}
		}
		return v;
	}
}