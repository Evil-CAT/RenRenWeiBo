package com.evilcat.renrenweibo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddAccountActivity extends Activity {

	public String TAG = "RenRenWeiBo";
	
	public static int act_num = 0;     //account number, 0 to start.
	private Button btn_renren;
	private Button btn_sina;
	private Button btn_tencent;
	private Button btn_cancel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
		
		btn_renren = (Button) findViewById(R.id.button_renren);
		btn_sina = (Button) findViewById(R.id.button_sina);
		btn_tencent = (Button) findViewById(R.id.button_tencent);
		btn_cancel = (Button) findViewById(R.id.button_cancel);
		
		btn_renren.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn renren clicked",
					     Toast.LENGTH_SHORT).show();
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
		
		btn_tencent.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn tencent clicked",
					     Toast.LENGTH_SHORT).show();
			}
		});
		
		btn_cancel.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setContentView(R.layout.activity_main);
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
