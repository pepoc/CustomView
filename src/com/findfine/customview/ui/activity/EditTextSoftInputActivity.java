package com.findfine.customview.ui.activity;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.findfine.customview.R;

/**
 * 	【A】stateUnspecified：软键盘的状态并没有指定，系统将选择一个合适的状态或依赖于主题的设置   
	【B】stateUnchanged：当这个activity出现时，软键盘将一直保持在上一个activity里的状态，无论是隐藏还是显示   
	【C】stateHidden：用户选择activity时，软键盘总是被隐藏   
	【D】stateAlwaysHidden：当该Activity主窗口获取焦点时，软键盘也总是被隐藏的   
	【E】stateVisible：软键盘通常是可见的   
	【F】stateAlwaysVisible：用户选择activity时，软键盘总是显示的状态   
	【G】adjustUnspecified：默认设置，通常由系统自行决定是隐藏还是显示   
	【H】adjustResize：该Activity总是调整屏幕的大小以便留出软键盘的空间   
	【I】adjustPan：当前窗口的内容将自动移动以便当前焦点从不被键盘覆盖和用户能总是看到输入内容的部分
 * @author yangchen
 *
 */
public class EditTextSoftInputActivity extends BaseActivity implements OnClickListener {

	private ScrollView customScrollView;
	private Button btnTest;
	private EditText etTest;
	private RelativeLayout rlParent;
	private int keyBoardHeight;
	private Button btn_2;
	private int move;
	private ValueAnimator valueAnimator;
	private float fraction;
	private int invisibleHeight;
	private int moveYYY, etTextY;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edit_text_soft_input);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
		rlParent = (RelativeLayout) findViewById(R.id.rl_parent);
		btnTest = (Button) findViewById(R.id.btn_test);
		customScrollView = (ScrollView) findViewById(R.id.custom_scroll_view);
		etTest = (EditText) findViewById(R.id.et_test);
		btn_2 = (Button) findViewById(R.id.btn_2);
		
		valueAnimator = ValueAnimator.ofObject(new MyTypeEvaluator(), 0f, 1f);
		valueAnimator.setDuration(150);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				Float animatedValue = (Float) animation.getAnimatedValue();
				fraction = animatedValue.floatValue();
				if (invisibleHeight > 100) {
					int moveH = (int)((move - moveYYY) * fraction);
					rlParent.scrollTo(0, moveH);
//					Log.i("onAnimationUpdate", moveH + " --------- fraction === " + fraction);
				} else {
					int moveH = (int)((move - moveYYY) * (1 - fraction));
					rlParent.scrollTo(0, moveH);
//					Log.i("onAnimationUpdate", moveH + " --------- fraction === " + fraction);
				}
			}
		});
	}
	
	@Override
	public void setListener() {
		super.setListener();
		btnTest.setOnClickListener(this);
		
		rlParent.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				
				int[] etTestLocation = new int[2];
				etTest.getLocationInWindow(etTestLocation);
				etTextY = etTest.getHeight() + etTestLocation[1];
				
				Rect rect = new Rect();
				rlParent.getWindowVisibleDisplayFrame(rect);
//				rlParent.getScrollY();
				Log.i("getWindowVisibleDisplayFrame", rect.toString() + "  --- rlParent.getScrollY() === " + rlParent.getScrollY());
				
				invisibleHeight = rlParent.getRootView().getHeight() - rect.bottom;
//				if (invisibleHeight > 100) {
				if (rect.bottom < mScreenHeight) {
					keyBoardHeight = mScreenHeight - rect.bottom;
					moveYYY = etTextY - rect.bottom;
					int[] location = new int[2];
					btn_2.getLocationInWindow(location);
					
					move = location[1] + btn_2.getHeight() - rect.bottom;
					
					valueAnimator.start();
					
					Log.e("onGlobalLayout", "--------up-------");
				} else {
					
					Log.e("onGlobalLayout", "--------down-------" + "  --- rlParent.getScrollY() === " + rlParent.getScrollY());
					if (rlParent.getScrollY() > 0) {
						valueAnimator.start();
					}
				}
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_test:
//			Rect rect = new Rect();
//			int[] location = new int[2];
//			btnTest.getLocationInWindow(location);
//			Rect frame = new Rect();
//			getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
//			int statusBarHeight = frame.top;
////			Log.i("getGlobalVisibleRect", "rect === " + rect.toString());
//			Log.i("getGlobalVisibleRect", "location === " + location[0] + "-" + location[1]);
//			customScrollView.scrollTo(0, rect.top - statusBarHeight);
			
//			btnTest.setFocusable(true); 
//			btnTest.setFocusableInTouchMode(true); 
//			btnTest.requestFocus(); 
//			InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE); 
//			imm.showSoftInput(etTest, InputMethodManager.RESULT_SHOWN); 
//			imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
			
			int[] location = new int[2];
			btnTest.getLocationInWindow(location);
			int[] lll = new int[2];
			btnTest.getLocationOnScreen(lll);
			int lalala = lll[1] - location[1];
			
			Log.i("lalala", " --- lll[1] === " + lll[1] + "  --- location[1] === " + location[1]);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("onResume()", "onResume()");
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("onStart()", "onStart()");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.i("onPause()", "onPause()");
	}
	
	class MyTypeEvaluator implements TypeEvaluator<Float> {

		@Override
		public Float evaluate(float fraction, Float startValue,
				Float endValue) {
//			Log.i("lalala", "fraction = " + fraction + " startValue = " + startValue + " endValue = " + endValue);
			return fraction;
		}
		
	}

}
