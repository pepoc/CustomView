package com.findfine.customview.ui.activity;

import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.findfine.customview.R;

public class DashBoardActivity extends BaseActivity {

	private ImageView ivIndicator;
	private Button btnStart;
	private TextView tvValue;
	private AnimationSet animationSet;
	private float begin = 0, end;
	
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dash_board);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
//		animationSet = new AnimationSet(true);
//		animationSet.setFillAfter(true);
		
		ivIndicator = (ImageView) findViewById(R.id.iv_indicator);
		btnStart = (Button) findViewById(R.id.btn_start);
		tvValue = (TextView) findViewById(R.id.tv_value);
		
		startAnimation(0);
	}
	
	@Override
	public void setListener() {
		super.setListener();
		
		btnStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				startAnimation(90);
				handler.postDelayed(runnable, 1000);
			}
		});
	}
	
	private void startAnimation(float end) {
		handler.postDelayed(runnable, 1000);
//		end = begin - end;
//		animationSet = new AnimationSet(true);
//		animationSet.setFillAfter(true);
		RotateAnimation rotateAnimation = new RotateAnimation(begin, end, Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 1f);
		rotateAnimation.setDuration(1000);
		rotateAnimation.setFillAfter(true);
//		animationSet.addAnimation(rotateAnimation);
		ivIndicator.startAnimation(rotateAnimation);
		begin = end;
	}
	
	private Runnable runnable = new Runnable() {
		
		@Override
		public void run() {
			Random random = new Random();
			int nextInt = random.nextInt(180);
			startAnimation(nextInt);
			tvValue.setText(nextInt + "");
//			handler.postDelayed(runnable, 1000);
		}
	};
}
