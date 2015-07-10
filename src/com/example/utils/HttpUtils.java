package com.example.utils;

import org.json.JSONArray;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android_basic.MyApplication;

public class HttpUtils {
	public static void getNewsJSON(final String url, final Handler handler, String tag){	
		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

			@Override
			public void onResponse(JSONArray arg0) {
				// TODO Auto-generated method stub
				Log.d("TAG", arg0.toString());
				Message msg = new Message();
				msg.obj = arg0.toString();
				
				handler.sendMessage(msg);
			}
		}, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.e("TAG", arg0.getMessage(), arg0);
			}
		});
		jsonArrayRequest.setTag(tag);
		
		RequestQueue mQueue = MyApplication.getHttpQueue();
		mQueue.add(jsonArrayRequest);
		
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				HttpURLConnection conn;
//				InputStream is;
//				try {
//					conn = (HttpURLConnection) new URL(url).openConnection();
//					conn.setRequestMethod("GET");
//					is = conn.getInputStream();
//					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//					String line = "";
//					StringBuilder result = new StringBuilder();
//					while ( (line = reader.readLine()) != null ){
//						result.append(line);
//					}
//					Message msg = new Message();
//					msg.obj = result.toString();
//					
//					handler.sendMessage(msg);
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//			}
//		}).start();
	}
	
	public static void setPicBitmap(final ImageView ivPic, final String pic_url){
		ImageRequest imageRequest = new ImageRequest(pic_url, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap arg0) {
				// TODO Auto-generated method stub
				ivPic.setImageBitmap(arg0);
			}
		}, 0, 0, Config.RGB_565, new Response.ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
				// TODO Auto-generated method stub
				Log.e("IMG", "error image");
			}
		});
		
		RequestQueue mQueue = MyApplication.getHttpQueue();
		mQueue.add(imageRequest);

//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					HttpURLConnection conn = (HttpURLConnection) new URL(pic_url).openConnection();
//					conn.connect();
//					InputStream is = conn.getInputStream();
//					Bitmap bitmap = BitmapFactory.decodeStream(is);
//					ivPic.setImageBitmap(bitmap);
//					is.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				} 
//			}
//		}).start();
	}
}
