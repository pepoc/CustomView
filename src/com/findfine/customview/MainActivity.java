package com.findfine.customview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;


public class MainActivity extends Activity implements OnClickListener {

	private Context context;
    private Button btnTest;
	private RelativeLayout rlViewParent;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        
        init();
        setListener();
    }
    
    private void init() {
    	btnTest = (Button) findViewById(R.id.btn_test);
    	rlViewParent = (RelativeLayout) findViewById(R.id.rl_view_parent);
	}
    
    private void setListener() {
    	btnTest.setOnClickListener(this);
	}
    
    @Override
    public void onClick(View v) {
    	switch (v.getId()) {
		case R.id.btn_test:
			startValutAnimator();
			break;

		default:
			break;
		}
    }
    
    private void startValutAnimator() {
    	AnimatorView animatorView = new AnimatorView(context);
    	RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    	rlViewParent.addView(animatorView, params);
    	
    	animatorView.startAnimator();
    }

}
