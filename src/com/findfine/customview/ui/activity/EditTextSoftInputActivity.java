package com.findfine.customview.ui.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
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
	
	private Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edit_text_soft_input);
		
//		ScrollView scrollView = new ScrollView(context);
//		scrollView.fullScroll(View.fo);doScrollY
		
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
	}
	
	@Override
	public void setListener() {
		super.setListener();
		btnTest.setOnClickListener(this);
		
		rlParent.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				rlParent.getWindowVisibleDisplayFrame(rect);
//				Log.e("onGlobalLayout", "rect === " + rect.toString());
				if (mScreenHeight != rect.bottom) {
					keyBoardHeight = mScreenHeight - rect.bottom;
					
					int[] location = new int[2];
					btn_2.getLocationInWindow(location);
					move = keyBoardHeight - (mScreenHeight - location[1] - btn_2.getHeight());
					
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							customScrollView.scrollTo(0, move);
						}
					}, 100);
					Log.e("onGlobalLayout", "move === " + move);
				}
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_test:
			Rect rect = new Rect();
			int[] location = new int[2];
			btnTest.getLocationInWindow(location);
			Rect frame = new Rect();
			getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
			int statusBarHeight = frame.top;
//			Log.i("getGlobalVisibleRect", "rect === " + rect.toString());
			Log.i("getGlobalVisibleRect", "location === " + location[0] + "-" + location[1]);
			customScrollView.scrollTo(0, rect.top - statusBarHeight);
			break;

		default:
			break;
		}
	}
}
