package com.example.adapter;

import java.util.List;

import com.example.android_basic.R;
import com.example.model.NewsInfo;
import com.example.utils.HttpUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends BaseAdapter {

	private Context context;
	private List<NewsInfo> newsList;
	
	public NewsAdapter(Context context, List<NewsInfo> newsList){
		this.context = context;
		this.newsList = newsList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null){
			convertView = LayoutInflater.from(context).inflate(R.layout.news_items, null);
		}
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		TextView tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);
		TextView tvTime = (TextView) convertView.findViewById(R.id.tvTime);
		ImageView ivPic = (ImageView) convertView.findViewById(R.id.ivPic);
		
		NewsInfo news = newsList.get(position);
		tvTitle.setText(news.getTitle());
		tvDesc.setText(news.getDesc());
		tvTime.setText(news.getTime());
		
		String pic_url = news.getPic_url();
		HttpUtils.setPicBitmap(ivPic, pic_url);
		
		return convertView;
	}

}
