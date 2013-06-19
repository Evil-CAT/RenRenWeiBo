package com.evilcat.renrenweibo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.renren.api.connect.android.AsyncRenren;
import com.renren.api.connect.android.Renren;
import com.renren.api.connect.android.common.AbstractRequestListener;
import com.renren.api.connect.android.exception.RenrenAuthError;
import com.renren.api.connect.android.exception.RenrenError;
import com.renren.api.connect.android.friends.FriendsGetFriendsRequestParam;
import com.renren.api.connect.android.friends.FriendsGetFriendsResponseBean;
import com.renren.api.connect.android.view.RenrenAuthListener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccountActivity extends Activity {

	public String TAG = "RenRenWeiBo";

	// public int acont_flag = 0; //account flag, 0 to start.
	// 1 for renren, 2 for sina, 3 for tencent.

	public static int acont_num = 0; // account number, 0 to start.
	private Button btn_cancel;

	// 你的应用ID
	private static final String APP_ID = "232940"; // 应用的API Keyprivate
	static final String API_KEY = "e05fb35b69be48968a61c2cfbb42fee2"; // 应用的Secret
																		// Keyprivate
	static final String SECRET_KEY = "9886dac044c3443683bd3604f1a05061";
	private Renren renren;
	private Handler handler;
	private Button renren_loginBtn;
	private Button renren_logoutBtn;
	private TextView loginText;
	private Button showFriendsBtn; 
	private ListView friendListView; 
	private ProgressDialog progressDialog;
	
	//直接使用access_token请求的参数：
		String method = "friends.getFriends";
		String v = "3.0";
		String access_token;
		String format = "JSON";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
		initUI();

		// 登录的Listener
		final RenrenAuthListener listener = new RenrenAuthListener() {
			// 登录成功
			public void onComplete(Bundle values) {
				showLoginBtn(false);
				btn_cancel.setVisibility(View.INVISIBLE);
				loginText.setText(R.string.auth_success);
				handler.post(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(
								AddAccountActivity.this,
								AddAccountActivity.this
										.getString(R.string.auth_success),
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			// 登录失败
			@Override
			public void onRenrenAuthError(RenrenAuthError renrenAuthError) {
				loginText.setText(R.string.auth_failed);
				handler.post(new Runnable() {

					@Override
					public void run() {
						Toast.makeText(
								AddAccountActivity.this,
								AddAccountActivity.this
										.getString(R.string.auth_failed),
								Toast.LENGTH_SHORT).show();
					}
				});
			}

			@Override
			public void onCancelLogin() {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancelAuth(Bundle values) {
				// TODO Auto-generated method stub

			}

		};
		
		
		//登录按钮的事件 
		renren_loginBtn.setOnClickListener(new View.OnClickListener() {

			@Override 
			public void onClick(View v) { 
				renren.authorize(AddAccountActivity.this, listener);
			} 
		}); 
		
		//退出按钮的事件 
		renren_logoutBtn.setOnClickListener(new View.OnClickListener() { 
		
			@Override 
			public void onClick(View v) { 
				renren.logout(getApplicationContext()); 
				showLoginBtn(true);
				btn_cancel.setVisibility(View.VISIBLE);
				loginText.setText(R.string.auth_string); 
			} 
		});

		btn_cancel.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AddAccountActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
		
		//为"显示好友列表"按钮添加事件
		showFriendsBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(renren != null){
					//创建异步获取数据的AsyncRenren实例 
					AsyncRenren asyncRenren = new AsyncRenren(renren);
					showProgress("请等待","数据获取中");
					//创建一个FriendsGetFriendsRequestParam用来获取好友数据 
					FriendsGetFriendsRequestParam param = new FriendsGetFriendsRequestParam();
					
					AbstractRequestListener<FriendsGetFriendsResponseBean> listener = 
							new AbstractRequestListener<FriendsGetFriendsResponseBean>(){

						@Override
						public void onComplete(final FriendsGetFriendsResponseBean bean) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() { 
								
								@Override 
								public void run() { 
									dismissProgress(); 
									showFriendList(bean); 
								}

								private void showFriendList(
										FriendsGetFriendsResponseBean bean) {
									// TODO Auto-generated method stub
									Log.d(TAG, "bean.tostring+++++++++" + bean.toString());
									
								}
							});
						}

						@Override
						public void onRenrenError(RenrenError renrenError) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() { 
								
								@Override 
								public void run() { 
									dismissProgress(); 
								} 
							}); 
						}

						@Override
						public void onFault(Throwable fault) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() { 
							
								@Override 
								public void run() { 
									dismissProgress(); 
								} 
							}); 
						}
					};
					asyncRenren.getFriends(param, listener);
				}
			}
		});
	}

	 /** * 显示等待框 * @param title 标题 * @param message 信息 */ 
	protected void showProgress(String title, String message) { 
		progressDialog = ProgressDialog.show(this, title, message); 
	} 
	/** * 取消等待框 */ 
	protected void dismissProgress() { 
		if (progressDialog != null) { 
			try { progressDialog.dismiss(); 
		} catch (Exception e) { } 
	} }
	
	private void initUI() {
		// TODO Auto-generated method stub
		renren = new Renren(API_KEY, SECRET_KEY, APP_ID, AddAccountActivity.this);
		handler = new Handler();
		renren_loginBtn = (Button) findViewById(R.id.renren_loginBtn);
		renren_logoutBtn = (Button) findViewById(R.id.renren_logoutBtn);
		loginText = (TextView) findViewById(R.id.loginText);
		btn_cancel = (Button) findViewById(R.id.button_cancel);
		showFriendsBtn = (Button) findViewById(R.id.showFriendsBtn); 
		friendListView = (ListView) findViewById(R.id.friendList);
		showLoginBtn(true);
	}

	private void showLoginBtn(boolean flag) {
		if(flag){ 
			renren_loginBtn.setVisibility(View.VISIBLE); 
			renren_logoutBtn.setVisibility(View.GONE); 
			showFriendsBtn.setVisibility(View.GONE); 
			friendListView.setVisibility(View.GONE); 
		} else { 
			renren_loginBtn.setVisibility(View.GONE);
			renren_logoutBtn.setVisibility(View.VISIBLE); 
			showFriendsBtn.setVisibility(View.VISIBLE); 
			friendListView.setVisibility(View.VISIBLE); 
		} 
	}
	
	public String getParams(){ 
		List<String> params = new ArrayList<String>();
		String method = "friends.getFriends";
		String v = "3.0";
		String access_token = renren.getAccessToken();
		String format = "JSON";
		Log.d(TAG, "+++++++++++++" + access_token);
		params.add("method="+method);
		params.add("v="+v);
		params.add("access_token="+access_token);
		params.add("format="+format); 

		// 拼装参数完毕后，params的值为：
		// [method=friends.getFriends, v=3.0, access_token=147396|6.cd69dd5a0549c925631969b8d2af1b3f.2592000.1315641600-244248724, format=JSON]

		return getSignature(params, renren.getSecret());
		}
   
	public String getSignature(List<String> paramList,String secret){
		Collections.sort(paramList);
		StringBuffer buffer = new StringBuffer();
		for (String param : paramList) {
			buffer.append(param); //将参数键值对，以字典序升序排列后，拼接在一起
		}
		buffer.append(secret); //符串末尾追加上应用的Secret Key
		try { //下面是将拼好的字符串转成MD5值，然后返回
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			StringBuffer result = new StringBuffer();
			try {
				for (byte b : md.digest(buffer.toString().getBytes("UTF-8"))) {
				result.append(Integer.toHexString((b & 0xf0) >>> 4));
				result.append(Integer.toHexString(b & 0x0f));
				}
			} catch (UnsupportedEncodingException e) {
				for (byte b : md.digest(buffer.toString().getBytes())) {
				result.append(Integer.toHexString((b & 0xf0) >>> 4));
				result.append(Integer.toHexString(b & 0x0f));
				}
			}
	
			return result.toString();
			} catch (java.security.NoSuchAlgorithmException ex) {
	
			}
	
			return null;
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
