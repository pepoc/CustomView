package com.findfine.customview.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.findfine.customview.R;

public class GridViewDemoActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
		RelativeLayout relativeLayout = new RelativeLayout(context);
		setContentView(relativeLayout);
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		relativeLayout.setLayoutParams(layoutParams);
		
		GridView gridView = new GridView(context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		relativeLayout.addView(gridView, params);
		gridView.setNumColumns(3);
		
		ImageView imageView = new ImageView(context);
		imageView.setBackgroundResource(R.drawable.ic_launcher);
		RelativeLayout parent = (RelativeLayout) gridView.getParent();
		parent.addView(imageView, 0);
		
		gridView.setAdapter(new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				if (convertView == null) {
					TextView textView = new TextView(context);
					textView.setPadding(0, 50, 0, 50);
					textView.setText("lalala --- " + position);
					convertView = textView;
				}
				return convertView;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public int getCount() {
				return 30;
			}
		});
	}
	
	@Override
	public void setListener() {
		super.setListener();
	}
}
