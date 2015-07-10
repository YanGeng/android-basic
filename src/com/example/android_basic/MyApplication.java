package com.example.android_basic;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class MyApplication extends Application {
	public static RequestQueue queue;
	
	public void onCreate() {
		super.onCreate();
		queue = Volley.newRequestQueue(getApplicationContext());
	}
	
	public static RequestQueue getHttpQueue() {
		return queue;
	}
}
