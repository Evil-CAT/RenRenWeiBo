package com.evilcat.renrenweibo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public String TAG = "RenRenWeiBo";
	public String Message = "/*******************************************\n" +
							"(C) Copyright 2009 Compal Electronics, Inc.\n" +
							"This software is the property of Compal Electronics, Inc. \n" +
							"You have to accept the terms in the license file before use.\n" +
							"Author: Evil-CAT \n" +
							"Version: 01 \n" +
							"Description:  only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							"only for test only for test only for test only for test only for test \n" +
							" ******************************************/";
	private TextView welcome;
	private Button btn_add;
	private Button btn_about;
	private Button btn_exit;
	private Button btn_back;
	private Button btn_accountlist;
	
	private int exitcnt = 0;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		welcome = (TextView) findViewById(R.id.textView1); 
		btn_add = (Button) findViewById(R.id.button_add);
		btn_about = (Button) findViewById(R.id.button_about);
		btn_exit = (Button) findViewById(R.id.button_exit);
		btn_back = (Button) findViewById(R.id.button_cancel);
		btn_accountlist = (Button) findViewById(R.id.button_accountlist);
		
		btn_add.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn add account clicked",
					     Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, AddAccountActivity.class);
				startActivity(intent);
			}
		});
		
		btn_accountlist.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn account list clicked",
					     Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(MainActivity.this, AccountListActivity.class);
				startActivity(intent);
			}
		});
		
		btn_about.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn about us clicked",
					     Toast.LENGTH_SHORT).show();
				new AlertDialog.Builder(MainActivity.this)
								.setTitle("About Us")
								.setPositiveButton("OK", null)
								.setMessage(Message)
								.show();
			}
		});
		
		btn_back.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn back clicked",
					     Toast.LENGTH_SHORT).show();
				setContentView(R.layout.activity_main);
			}
		});
		
		btn_exit.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				exitcnt ++;
				if(exitcnt == 1){
					Log.d(TAG, "into if");
					Toast.makeText(getApplicationContext(), "click again to exit",
						     Toast.LENGTH_SHORT).show();
				}
				else if(exitcnt > 1){
					finish();
				}
			}
		});	
	}
}
