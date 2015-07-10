package com.example.android_basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class webView extends Activity {

	EditText url;
	WebView show;
	Button search;
	Button goMain;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview_layout);
		url = (EditText) findViewById(R.id.url);
		show = (WebView) findViewById(R.id.webShow);
		search = (Button) findViewById(R.id.search);
		goMain = (Button) findViewById(R.id.goMain);
		
		WebSettings webSettings = show.getSettings();
        webSettings.setJavaScriptEnabled(true);
        show.setWebViewClient(new WebViewClient());
        
		search.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				String urlStr = url.getText().toString();
				// Load urlStr
				show.loadUrl(urlStr);
			}
		});
		
		goMain.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(webView.this,
						MainActivity.class);
				startActivity(intent);

			}
		});
		
		StringBuilder sb = new StringBuilder();
		// ƴ��һ��HTML����
		sb.append("<html>");
		sb.append("<head>");
		sb.append("<title> 欢迎您 </title>");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<h2> 欢迎您访问<a href=\"http://www.dianping.com\">"
			+ "大众点评网</a></h2>");
		sb.append("<h2> 欢迎您访问<a href=\"http://10.128.39.178:8080/ui/\">"
				+ "TEST</a></h2>");
		sb.append("</body>");
		sb.append("</html>");
		// ʹ�ü򵥵�loadData�����ᵼ�����룬������Android API��Bug
		// show.loadData(sb.toString() , "text/html" , "utf-8");
		// ���ء�����ʾHTML����
		show.loadDataWithBaseURL(null, sb.toString()
			, "text/html" , "utf-8", null);

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if (keyCode == KeyEvent.KEYCODE_SEARCH) {
			String urlStr = url.getText().toString();
			// Load urlStr
			show.loadUrl(urlStr);
			return true;
		} else if ((keyCode == KeyEvent.KEYCODE_BACK) && show.canGoBack()) {
			// 返回键退回
			show.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
		// return false;
	}
	
}
