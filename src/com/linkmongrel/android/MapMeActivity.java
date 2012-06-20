package com.linkmongrel.android;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class MapMeActivity extends MapActivity {
	
	LinearLayout linearLayout;
	MapView mapView;
	MapController mc;
	List<Overlay> mapOverlays;
	Drawable drawable;
	ItemOverlay itemizedOverlay;
	GeoPoint point;
	List<Address> addresses;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.map);
		this.mapView = (MapView)this.findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);
		mc = this.mapView.getController();
		mapOverlays = mapView.getOverlays();
		drawable = this.getResources().getDrawable(R.drawable.androidmarker);
		itemizedOverlay = new ItemOverlay(drawable);
		mc.setZoom(18);
		
		// compute location
				Geocoder gc = new Geocoder(this);
				try {
					if(!MainActivity.list.get(3).equalsIgnoreCase("")) {
						addresses = gc.getFromLocationName(MainActivity.list.get(1) + ", " + MainActivity.list.get(2) + ", " + MainActivity.list.get(3), 1);
					} else {
						Context context = getApplicationContext();
						CharSequence text = "You must set an address in the edit screen.";
						int duration = Toast.LENGTH_LONG;

						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
						addresses = gc.getFromLocationName("1 Campus Drive Allendale MI", 1);
					}
					if(addresses.size() >= 1) {
						point = new GeoPoint(
		                        (int) (addresses.get(0).getLatitude() * 1E6), 
		                        (int) (addresses.get(0).getLongitude() * 1E6));
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if(point != null) {
					mc.animateTo(point);
				}
		
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
		
		mapView.invalidate();
	}
	
	@Override
	 protected void onResume() { 
		// compute location
		Geocoder gc = new Geocoder(this);
		try {
			if(!MainActivity.list.get(3).equalsIgnoreCase("")) {
				addresses = gc.getFromLocationName(MainActivity.list.get(1) + ", " + MainActivity.list.get(2) + ", " + MainActivity.list.get(3), 1);
			} else {
				Context context = getApplicationContext();
				CharSequence text = "You must set an address in the edit screen.";
				int duration = Toast.LENGTH_LONG;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
				addresses = gc.getFromLocationName("1 Campus Drive Allendale MI", 1);
			}
			if(addresses.size() >= 1) {
				point = new GeoPoint(
                        (int) (addresses.get(0).getLatitude() * 1E6), 
                        (int) (addresses.get(0).getLongitude() * 1E6));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(point != null) {
			mc.animateTo(point);
		}	 super.onResume();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

}