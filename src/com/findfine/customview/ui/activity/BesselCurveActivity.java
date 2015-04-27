package com.findfine.customview.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.findfine.customview.ui.views.AnimatorView;

public class BesselCurveActivity extends BaseActivity {
	
	private AnimatorView animatorView;
	private FrameLayout rootView;
	private Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		rootView = (FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
		initValutAnimator();
		
		button = new Button(context);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		button.setText("test");
		rootView.addView(button, params);
	}
	
	@Override
	public void setListener() {
		super.setListener();
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				animatorView.startAnimator();
			}
		});
	}
	
	/**
     * 属性动画，贝赛尔曲线
     */
    private void initValutAnimator() {
    	animatorView = new AnimatorView(context);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	rootView.addView(animatorView, params);
//    	animatorView.startAnimator();
    }
}
