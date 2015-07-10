package com.example.android_basic;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BrowseNewsActivity extends Activity {
	
	private WebView webView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.browse_news);
		
		webView = (WebView) findViewById(R.id.webView);
		webView.setWebViewClient(new WebViewClient());
		webView.setHorizontalScrollBarEnabled(true);
		webView.setHorizontalScrollbarOverlay(true);
		webView.setVerticalScrollbarOverlay(true);
		webView.setVerticalScrollBarEnabled(true);
		
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		
		String pic_url = getIntent().getStringExtra("content_url");
		webView.loadUrl(pic_url);
	}
	
	// Override the onKeyDown, so that we can go to the front page in the webview,
	// without this, we will go back to the front activity in android
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			// 返回键退回
			webView.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
		// return false;
	}
}
