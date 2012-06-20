package com.linkmongrel.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends Activity {
	
	private EditText editName;
	private EditText editAddress;
	private EditText editCity;
	private EditText editState;
	private EditText editPhone;
	private Button saveButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit);
		editName = (EditText) this.findViewById(R.id.editName);
		editAddress = (EditText) this.findViewById(R.id.editAddress);
		editCity = (EditText) this.findViewById(R.id.editCity);
		editState = (EditText) this.findViewById(R.id.editState);
		editPhone = (EditText) this.findViewById(R.id.editPhone);
		saveButton = (Button) this.findViewById(R.id.save);
		
		editName.setText(MainActivity.list.get(0));
		editAddress.setText(MainActivity.list.get(1));
		editCity.setText(MainActivity.list.get(2));
		editState.setText(MainActivity.list.get(3));
		editPhone.setText(MainActivity.list.get(4));
		
		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("data", getData());
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
	}
	
	public String[] getData() {
		String name = editName.getText().toString();
		String address = editAddress.getText().toString();
		String city = editCity.getText().toString();
		String state = editState.getText().toString();
		String phone = editPhone.getText().toString();
		
		String data[] = {name, address, city, state, phone};
		
		return data;
	}

}
