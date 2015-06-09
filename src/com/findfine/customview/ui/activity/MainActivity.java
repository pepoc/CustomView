package com.findfine.customview.ui.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.findfine.customview.R;


public class MainActivity extends ListActivity {
	
	private Context context;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        
        SimpleAdapter adapter = new SimpleAdapter(context, getData(), android.R.layout.simple_list_item_1, new String[]{"name"}, new int[]{android.R.id.text1});
        setListAdapter(adapter);
    }
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		List<Map<String, Object>> data = getData();
		Map<String, Object> map = data.get(position);
		startActivity((Intent)(map.get("intent")));
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		addItem(data, getString(R.string.activity_bessel_curve), BesselCurveActivity.class);
		addItem(data, getString(R.string.activity_dash_board), DashBoardActivity.class);
		addItem(data, getString(R.string.activity_soft_input), EditTextSoftInputActivity.class);
		addItem(data, getString(R.string.activity_grid_view), GridViewDemoActivity.class);
		addItem(data, getString(R.string.activity_raindrop), RaindropActivity.class);
		addItem(data, getString(R.string.activity_test), TestActivity.class);
		addItem(data, getString(R.string.activity_edittext_test), EditTextTest.class);
		return data;
	}
	
	private void addItem(List<Map<String, Object>> data, String name, Class<?> clazz) {
		Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", name);
        temp.put("intent", new Intent(context, clazz));
        data.add(temp);
	}
    
}
