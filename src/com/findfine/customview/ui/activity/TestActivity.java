package com.findfine.customview.ui.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.Header;
import org.json.JSONObject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

public class TestActivity extends BaseActivity {

	private Button btn;
	private static final String IMAGE_UNSPECIFIED = "image/*";
	private AsyncHttpClient client;
	private String uploadToken;
	private File picture;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		btn = new Button(context);
		btn.setText("Upload");
		client = new AsyncHttpClient();
		
		ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		setContentView(btn, params);
		init();
		setListener();
	}
	
	@Override
	public void init() {
		super.init();
	}
	
	@Override
	public void setListener() {
		super.setListener();
		
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				getHeaderFromGallery();
				picture = new File(Environment.getExternalStorageDirectory() + "/Findfine", System.currentTimeMillis() + ".jpg");
				String url = "http://findfine.com.cn/php-sdk-6.1.13/demo/up.php";
				RequestParams params = new RequestParams();
				params.put("filename", picture.getName());
				client.post(url, params, new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						uploadToken = result;
						Log.i("onSuccess", result);
						getHeaderFromGallery();
					}
					
					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
						Log.i("onFailure", arg0 + "");
					}
				});
			}
		});
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode != 1000 || resultCode != RESULT_OK) {
			return ;
		}
		File file = copyBitmapToTempFile(data.getData());
		String filePath = file.getAbsolutePath();
		String key = picture.getName();
		UploadManager uploadManager = new UploadManager();
		uploadManager.put(filePath, key, uploadToken, new UpCompletionHandler() {
			
			@Override
			public void complete(String key, ResponseInfo info, JSONObject response) {
				if (info.isOK()) {
					Log.i("qiniu", "success");
				} else {
					Log.i("qiniu", "error");
				}
			}
		}, null);
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void getHeaderFromGallery() {
		Intent intent = new Intent(Intent.ACTION_PICK, null);
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				IMAGE_UNSPECIFIED);
		startActivityForResult(intent, 1000);
	}
	
	public File copyBitmapToTempFile(Uri uri) {
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/Findfine");
		if (!file.exists()) {
			file.mkdirs();
		}
		
		FileOutputStream out = null;
		InputStream is = null;
		try {
			is = context.getContentResolver().openInputStream(uri);
			out = new FileOutputStream(picture);
			byte[] buffer = new byte[1024];
			while (is.read(buffer) != -1) {
				out.write(buffer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return picture;
	}
	
}
