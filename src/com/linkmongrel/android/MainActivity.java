package com.linkmongrel.android;

import java.util.ArrayList;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

public class MainActivity extends TabActivity {
	
	private static int MESSAGE_REQUEST = 1;
	
	public static ArrayList<String> list = new ArrayList<String>();
	private String name = "Dan Mikita";
	private String address = "4360 Timber Ridge Trial";
	private String city = "Wyoming";
	private String state = "MI";
	private String phone = "616-262-5733";
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		list.add(name);
		list.add(address);
		list.add(city);
		list.add(state);
		list.add(phone);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, MeActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("me")
				.setIndicator("Me", res.getDrawable(R.drawable.ic_tab_me))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, VanityActivity.class);
		spec = tabHost
				.newTabSpec("vanity")
				.setIndicator("Vanity", res.getDrawable(R.drawable.ic_tab_vanity))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, MapMeActivity.class);
		spec = tabHost
				.newTabSpec("map")
				.setIndicator("Map", res.getDrawable(R.drawable.ic_tab_maps))
				.setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent().setClass(this, TwitterActivity.class);
		spec = tabHost
				.newTabSpec("twitter")
				.setIndicator("Twitter", res.getDrawable(R.drawable.ic_tab_maps))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {

		if (requestCode == MESSAGE_REQUEST) {
			String data[] = intent.getStringArrayExtra("data");
			if (resultCode == Activity.RESULT_OK && data != null) {
				name = data[0];
				address = data[1];
				city = data[2];
				state = data[3];
				phone = data[4];
				list.clear();
				list.add(name);
				list.add(address);
				list.add(city);
				list.add(state);
				list.add(phone);
			}
		}

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.options, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.edit:
	        	Intent intent = new Intent(MainActivity.this, EditActivity.class);
				startActivityForResult(intent, MESSAGE_REQUEST);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}