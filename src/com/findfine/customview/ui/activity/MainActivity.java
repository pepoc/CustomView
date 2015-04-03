package com.findfine.customview.ui.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.findfine.customview.R;
import com.findfine.customview.ui.views.AnimatorView;
import com.findfine.customview.ui.views.CustomView;


public class MainActivity extends BaseActivity implements OnClickListener {

    private Button btnTest;
	private RelativeLayout rlViewParent;
	private AnimatorView animatorView;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        init();
		setListener();
    }
	
	@Override
	public void init() {
		super.init();
		btnTest = (Button) findViewById(R.id.btn_test);
    	rlViewParent = (RelativeLayout) findViewById(R.id.rl_view_parent);
    	
//    	initValutAnimator();
//    	initCustomView();
	}
    
    @Override
    public void setListener() {
    	super.setListener();
    	btnTest.setOnClickListener(this);
    }
    
    @Override
    public void onClick(View v) {
    	switch (v.getId()) {
		case R.id.btn_test:
//			animatorView.startAnimator();
			
//			Intent intent = new Intent(context, EditTextSoftInputActivity.class);
//			startActivity(intent);
			
			Intent intent = new Intent(context, GridViewDemoActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
    }
    
    /**
     * 属性动画，贝赛尔曲线
     */
    private void initValutAnimator() {
    	animatorView = new AnimatorView(context);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	rlViewParent.addView(animatorView, params);
    }
    
    /**
     * 自定义View
     */
    private void initCustomView() {
    	final CustomView customView = new CustomView(context);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	rlViewParent.addView(customView, params);
    	customView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				Rect rect = new Rect();
				customView.getWindowVisibleDisplayFrame(rect);
				Log.e("onGlobalLayout", "rect === " + rect.toString());
			}
		});
    }

}
