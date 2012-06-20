package com.linkmongrel.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MeActivity extends Activity {

	private TextView name;
	private TextView address;
	private TextView city;
	private TextView state;
	private TextView phone;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.me);

		name = (TextView) this.findViewById(R.id.nameText);
		address = (TextView) this.findViewById(R.id.addressText);
		city = (TextView) this.findViewById(R.id.cityText);
		state = (TextView) this.findViewById(R.id.stateText);
		phone = (TextView) this.findViewById(R.id.phoneText);

	}
	
	@Override
	 protected void onResume() { 
	    name.setText(MainActivity.list.get(0));
		address.setText(MainActivity.list.get(1));
		city.setText(MainActivity.list.get(2));
		state.setText(MainActivity.list.get(3));
		phone.setText(MainActivity.list.get(4));
	 super.onResume();
	}
	
}

	