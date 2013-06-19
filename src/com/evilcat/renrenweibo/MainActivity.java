package com.evilcat.renrenweibo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public String TAG = "RenRenWeiBo";
	public String Message = "/*******************************************\n"
			+ "(C) Copyright 2009 Compal Electronics, Inc.\n"
			+ "This software is the property of Compal Electronics, Inc. \n"
			+ "You have to accept the terms in the license file before use.\n"
			+ "Author: Evil-CAT \n"
			+ "Version: 01 \n"
			+ "Description: \n";
	
	private TextView welcome;
	private Button btn_about;
	private Button btn_accountlist;
	private ImageButton btn_renren;
	private ImageButton btn_sina;
	private ImageButton btn_tencent;
	
	private boolean isExit = false;
	
	private String extSd_path = "/storage/extSdCard/";
	
	private UsbManager usbManager = null;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
		
		File destFile = new File(extSd_path);
		
		if(destFile.exists() && destFile.isDirectory()){
			if(destFile.list().length > 0){
				Log.d(TAG, "destFile length ========= " + destFile.list().length);
				String destFileName = destFile + "/testextsdcardpath.txt";
				Log.d(TAG, "destFileName ======" + destFileName);
				try {
					Log.d(TAG, "into try!>>>>>>>>>>>>>>>>>>>>");
					FileWriter fWriter = new FileWriter(destFileName);
					fWriter.write("this is a test!!!");
					fWriter.flush();
					fWriter.close();
					Log.d(TAG, "write file end >>>>>>>>>>>>>>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.d(TAG, "io exception>>>>>>>>>>>>>>");
					e.printStackTrace();
				}
			}
		}else{
			Log.d(TAG, "destFile doesn't exists>>>>>>>>");
		}
		
		Log.d(TAG, "sd_path ========= " + extSd_path);
		
		Toast.makeText(getApplicationContext(), "ext sdcard path ======" + extSd_path, Toast.LENGTH_SHORT).show();
		
		
		btn_renren.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Btn add account clicked", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,
						AddAccountActivity.class);
				startActivity(intent);
			}
		});

		btn_accountlist.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						"Btn account list clicked", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this,
						AccountListActivity.class);
				startActivity(intent);
			}
		});

		btn_about.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn about us clicked",
						Toast.LENGTH_SHORT).show();
				new AlertDialog.Builder(MainActivity.this).setTitle("About Us")
						.setPositiveButton("OK", null).setMessage(Message)
						.show();
			}
		});
		
		btn_sina.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn sina clicked", 
						Toast.LENGTH_SHORT).show();
			}
			
		});
	}
	
	 @Override  
	    public boolean onKeyDown(int keyCode, KeyEvent event) {  
	        if (keyCode == KeyEvent.KEYCODE_BACK) {  
	            exit();
	            return false;  
	        } else {  
	            return super.onKeyDown(keyCode, event);  
	        }  
	    }  
	
	private void exit() {
		// TODO Auto-generated method stub
		if (!isExit) {  
            isExit = true;  
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();  
            mHandler.sendEmptyMessageDelayed(0, 2000);  
        } else {  
            Intent intent = new Intent(Intent.ACTION_MAIN);  
            intent.addCategory(Intent.CATEGORY_HOME);  
            startActivity(intent);  
            finish();
        }  
	}
	
    Handler mHandler = new Handler() {  
        
        @Override  
        public void handleMessage(Message msg) {  
            // TODO Auto-generated method stub  
            super.handleMessage(msg);  
            isExit = false;  
        }  
    };  

	private void init() {
		welcome = (TextView) findViewById(R.id.textView1);
		btn_about = (Button) findViewById(R.id.button_about);
		btn_accountlist = (Button) findViewById(R.id.button_accountlist);
		btn_renren = (ImageButton) findViewById(R.id.btn_renren);
		btn_sina = (ImageButton) findViewById(R.id.btn_sina);
		btn_tencent = (ImageButton) findViewById(R.id.btn_tencent);
	}
}
