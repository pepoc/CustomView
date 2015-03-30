package com.findfine.customview.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;

public class BaseActivity extends Activity {
	
	public Context context;
	public int mScreenWidth;
	public int mScreenHeight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		mScreenWidth = metric.widthPixels;
		mScreenHeight = metric.heightPixels;
	}
	
	public void init() {
		// TODO Auto-generated method stub

	}
	
	public void setListener() {
		// TODO Auto-generated method stub

	}
}
