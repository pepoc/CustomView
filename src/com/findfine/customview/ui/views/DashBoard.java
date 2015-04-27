package com.findfine.customview.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class DashBoard extends View {
	
	private Paint paint = null;
	private RectF rectF;
	private Matrix matrix;

	public DashBoard(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		init();
	}

	public DashBoard(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		init();
	}

	public DashBoard(Context context) {
		super(context);
		
		init();
	}
	
	private void init() {
		paint = new Paint();
		rectF = new RectF(50, 100, 500, 500);
		matrix = new Matrix();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(20);
		paint.setAntiAlias(true);
		
		
		canvas.drawArc(rectF, 0, -180, false, paint);
		matrix.postRotate(45, 500, 500);
	}
}
