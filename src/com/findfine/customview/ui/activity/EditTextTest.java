package com.findfine.customview.ui.activity;

import com.findfine.customview.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditTextTest extends BaseActivity implements OnClickListener {

	private EditText etFirst;
	private EditText etSecond;
	private Button btnCopy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_edittext_test);
		
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
		
		etFirst = (EditText) findViewById(R.id.et_first);
		etSecond = (EditText) findViewById(R.id.et_second);
		btnCopy = (Button) findViewById(R.id.btn_copy);
	}
	
	@Override
	public void setListener() {
		super.setListener();
		
		btnCopy.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_copy:
			String str = etFirst.getText().toString();
			etSecond.setText(str);
			break;

		default:
			break;
		}
	}
}
