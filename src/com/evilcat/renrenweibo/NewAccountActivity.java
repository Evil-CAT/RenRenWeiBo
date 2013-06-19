package com.evilcat.renrenweibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class NewAccountActivity extends Activity {
	
	public String TAG = "RenRenWeiBo";
	
	private EditText name;
	private EditText passwd;
	private Button btn_ok;
	private Button btn_cancel;
	private TextView mTextView1;
	private TextView mTextView2;
	private CheckBox mCheckBox;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_account);
		
		name = (EditText) findViewById(R.id.edit_name);
		passwd = (EditText) findViewById(R.id.edit_passwd);
		btn_ok = (Button) findViewById(R.id.button_ok);
		btn_cancel = (Button) findViewById(R.id.button_cancel);
		mTextView1 = (TextView) findViewById(R.id.textView1);
		mTextView2 = (TextView) findViewById(R.id.textView2);
		mCheckBox = (CheckBox) findViewById(R.id.checkbox);
		
		name.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Edit text user name",
					     Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		passwd.setOnEditorActionListener(new OnEditorActionListener() {
			
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Edit text password",
					     Toast.LENGTH_SHORT).show();
				return false;
			}
		});
		
		btn_ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, name.getText().toString());
				if(name.getText().toString().equals("") || 
						passwd.getText().toString().equals("")){
					Toast.makeText(getApplicationContext(), "Empty name or password",
						     Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btn_cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(NewAccountActivity.this, AddAccountActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}
	
}
