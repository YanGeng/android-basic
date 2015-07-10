package com.example.android_basic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Chronometer.OnChronometerTickListener;

public class MainActivity extends Activity {

	Chronometer ch;
	Button start;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ch = (Chronometer) findViewById(R.id.test);

		start = (Button) findViewById(R.id.start);
		start.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View source)
			{
				ch.setBase(SystemClock.elapsedRealtime());
				ch.start();
				start.setEnabled(false);
			}
		});

		ch.setOnChronometerTickListener(new OnChronometerTickListener()
		{
			@Override
			public void onChronometerTick(Chronometer ch)
			{
				if (SystemClock.elapsedRealtime() - ch.getBase() > 20 * 1000)
				{
					ch.stop();
					start.setEnabled(true);
				}
			}
		});
		
		Button bn2 = (Button) findViewById(R.id.button_login);
		bn2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				// intent Activity
				startActivity(intent);
			}
		});
		
		Button bn3 = (Button) findViewById(R.id.button_brower);
		bn3.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this,
						webView.class);
				//		LoginActivity.class);
				// intent Activity
				startActivity(intent);
			}
		});
		
//		Button bn4 = (Button) findViewById(R.id.button4);
//		bn4.setOnClickListener(new OnClickListener()
//		{
//			public void onClick(View v)
//			{
//				Intent intent = new Intent(MainActivity.this,
//						MyScroler.class);
//				//		LoginActivity.class);
//				// intent Activity
//				startActivity(intent);
//			}
//		});
		
		Button bn_news = (Button) findViewById(R.id.button_news);
		bn_news.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, NewsActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// button1 clickHandler
	public void clickHandler(View source) {
		TextView tv = (TextView)findViewById(R.id.textView1);
		tv.setText("Current time: " + new java.util.Date());
	}
}
