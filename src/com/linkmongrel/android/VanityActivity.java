package com.linkmongrel.android;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class VanityActivity extends Activity {

	private WebView mWebView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.vanity);

	    mWebView = (WebView) findViewById(R.id.webview);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl("https://www.google.com/search?q=" + MainActivity.list.get(0));
	}
	
	@Override
	 protected void onResume() { 
		mWebView.loadUrl("https://www.google.com/search?q=" + MainActivity.list.get(0));
	 super.onPause();
	}

}