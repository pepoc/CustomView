package com.findfine.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

public class CustomView extends RelativeLayout {

	public CustomView(Context context) {
		super(context);
	}

	public CustomView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CustomView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		Log.i("onSizeChanged", "w === " + w + " --- h === " + h + " --- oldw === " + oldw + " --- oldh === " + oldh);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		changed = true;
		t = t - 500;
		b = b + 500;
		super.onLayout(changed, l, t, r, b);
		Log.i("onLayout", "changed === " + changed + " --- l === " + l + " --- t === " + t + " --- r === " + r + " --- b === " + b);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i("onMeasure", "widthMeasureSpec === " + widthMeasureSpec + " --- heightMeasureSpec === " + heightMeasureSpec);
	}

}
