package com.example.android_basic;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.adapter.NewsAdapter;
import com.example.model.NewsInfo;
import com.example.utils.HttpUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class NewsActivity extends Activity implements OnItemClickListener{
	private ListView lvNews;
	private List<NewsInfo> newsList;
	private NewsAdapter adapter;
	
	private static final String GET_NEWS_URL = "http://192.168.0.105:8080/springSample/";
	
	private Handler getNewsHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			String jsonData = (String) msg.obj;
			try {
				JSONArray jsonArray = new JSONArray(jsonData);
				for (int i=0;i<jsonArray.length();i++){
					JSONObject object = jsonArray.getJSONObject(i);
					String title = object.getString("title");
					String desc = object.getString("desc");
					String time = object.getString("time");
					String content_url = object.getString("content_url");
					String pic_url = object.getString("pic_url");
					newsList.add(new NewsInfo(title, desc, time, content_url, pic_url));
				}
				adapter.notifyDataSetChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.news_layout);
		
		lvNews = (ListView) findViewById(R.id.lvNews);
		newsList = new ArrayList<NewsInfo>();
		adapter = new NewsAdapter(this, newsList);
		
		lvNews.setAdapter(adapter);
		lvNews.setOnItemClickListener(this);
		
		
		HttpUtils.getNewsJSON(GET_NEWS_URL, getNewsHandler, "GETNEWS");
//		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(GET_NEWS_URL, new Response.Listener<JSONArray>() {
//
//			@Override
//			public void onResponse(JSONArray arg0) {
//				// TODO Auto-generated method stub
//				Log.d("TAG", arg0.toString());
//				Message msg = new Message();
//				msg.obj = arg0.toString();
//				
//				getNewsHandler.sendMessage(msg);
//			}
//		}, new Response.ErrorListener() {
//
//			@Override
//			public void onErrorResponse(VolleyError arg0) {
//				// TODO Auto-generated method stub
//				Log.e("TAG", arg0.getMessage(), arg0);
//			}
//		});
//		jsonArrayRequest.setTag("GETNEWS");
//		
//		RequestQueue mQueue = MyApplication.getHttpQueue();
//		mQueue.add(jsonArrayRequest);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		MyApplication.getHttpQueue().cancelAll("GETNEWS");
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		NewsInfo newsInfo = newsList.get(position);
		Intent intent = new Intent(this, BrowseNewsActivity.class);
		intent.putExtra("content_url", newsInfo.getContent_url());
		startActivity(intent);
	}
}
