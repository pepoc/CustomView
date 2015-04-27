package com.findfine.customview.ui.views;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class AnimatorView extends View {
	
	private Paint paint;
	
	private Path path;
	
	private PathMeasure pathMeasure = null;
	
	private float moveX = 500, moveY = 200;
	
	private ValueAnimator valueAnimator;
	
	private float fraction;

	public AnimatorView(Context context) {
		super(context);
		
		init();
	}
	
	public AnimatorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}

	private void init() {
		
		pathMeasure = new PathMeasure();
		
		valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(), 0f, 1f);
		valueAnimator.setDuration(2000);
		valueAnimator.setRepeatCount(1);
		valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
		
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Float animatedValue = (Float) animation.getAnimatedValue();
				fraction = animatedValue.floatValue();
				invalidate();
				Log.i("lalala", animatedValue.toString());
			}
		});
		
		paint = new Paint();
		paint.setColor(Color.RED);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
		
		path = new Path();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		path.reset();
//		path.moveTo(500, 0);
//		path.quadTo(moveX, moveY, getWidth() - 500, getHeight() - 500);
		path.moveTo(getWidth() / 2, 200);
		path.cubicTo(0, 0, (getWidth() / 2) - (getWidth() / 4) - 100, 400, getWidth() / 2, 510);
		path.cubicTo((getWidth() / 2) + (getWidth() / 4) + 100, 400, getWidth(), 0, getWidth() / 2, 200);
		canvas.drawPath(path, paint);
		
		float[] pos = new float[2];
		pathMeasure.setPath(path, false);
		pathMeasure.getPosTan(pathMeasure.getLength() * fraction, pos, null);
		canvas.drawCircle(pos[0], pos[1], 10, paint);
		Log.i("onDraw", "pathMeasure.getLength() = " + pathMeasure.getLength());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
//		case MotionEvent.ACTION_DOWN:
//			Log.i("onTouchEvent", "ACTION_DOWN --- moveX = " + event.getX() + " moveY = " + event.getY());
//			break;
		case MotionEvent.ACTION_MOVE:
			moveX = event.getX();
			moveY = event.getY();
			invalidate();
//			Log.i("onTouchEvent", "ACTION_MOVE --- moveX = " + moveX + " moveY = " + moveY);
			break;

		default:
			break;
		}
//		return super.onTouchEvent(event);
		return true;
	}
	
	public void startAnimator() {
		valueAnimator.start();
	}
	
	class MyTypeEvaluator implements TypeEvaluator<Float> {

		@Override
		public Float evaluate(float fraction, Float startValue,
				Float endValue) {
			Log.i("lalala", "fraction = " + fraction + " startValue = " + startValue + " endValue = " + endValue);
			return fraction;
		}
		
	}

}
