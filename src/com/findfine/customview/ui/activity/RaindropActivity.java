package com.findfine.customview.ui.activity;

import android.graphics.Color;
import android.os.Bundle;

import com.findfine.customview.ui.views.RaindropView;

public class RaindropActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setContentView(R.layout.activity_raindrop);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
		
//		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		RaindropView raindropView = new RaindropView(context);
		raindropView.setBackgroundColor(Color.TRANSPARENT);
		setContentView(raindropView);
	}
	
	@Override
	public void setListener() {
		super.setListener();
	}
}
