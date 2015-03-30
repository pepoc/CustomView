package com.findfine.customview.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class BaseActivity extends Activity {
	
	public Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		init();
		setListener();
	}
	
	public void init() {
		// TODO Auto-generated method stub

	}
	
	public void setListener() {
		// TODO Auto-generated method stub

	}
}
