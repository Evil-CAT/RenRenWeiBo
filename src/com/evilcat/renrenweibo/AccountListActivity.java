package com.evilcat.renrenweibo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class AccountListActivity extends Activity {
	
	public String TAG = "RenRenWeiBo";
	
	private TextView show_no_account;
	private TextView to_add_account;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_list);
		
		show_no_account = (TextView) findViewById(R.id.textView1);
		to_add_account = (TextView) findViewById(R.id.textView2);
		
		to_add_account.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "TextView to add account clicked",
					     Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(AccountListActivity.this, AddAccountActivity.class);
				startActivity(intent);
			}
		});
	}
}
