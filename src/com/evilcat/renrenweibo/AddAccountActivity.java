package com.evilcat.renrenweibo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class AddAccountActivity extends Activity {

	public String TAG = "RenRenWeiBo";
	
//	public int acont_flag = 0;     		//account flag, 0 to start. 
										//1 for renren, 2 for sina, 3 for tencent.
	
	public static int acont_num = 0;		//account number, 0 to start.
	private RadioGroup group_add;
//	private RadioButton btn_renren;
//	private RadioButton btn_sina;
//	private RadioButton btn_tencent;
	private Button btn_ok;
	private Button btn_cancel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_account);
		
		group_add = (RadioGroup) findViewById(R.id.radioGroup);
		btn_ok = (Button) findViewById(R.id.button_ok);
		btn_cancel = (Button) findViewById(R.id.button_cancel);
		
		group_add.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				int radioButtonId = group.getCheckedRadioButtonId();
				Log.d(TAG, "selected radiobutton id is : " + radioButtonId);
			}
		});
/*		
		btn_renren.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn renren clicked",
					     Toast.LENGTH_SHORT).show();
				acont_flag = 1;
			}
		});
		
		btn_sina.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn sina clicked",
					     Toast.LENGTH_SHORT).show();
				acont_flag = 2;
			}
		});
		
		btn_tencent.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn tencent clicked",
					     Toast.LENGTH_SHORT).show();
				acont_flag = 3;
			}
		});
*/
		btn_ok.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Btn ok clicked",
					     Toast.LENGTH_SHORT).show();
//				Log.d(TAG,"====account flag = " + acont_flag);
				switch(v.getId()){
					case R.id.radiobtn_renren:
						Log.d(TAG, "radio button renren");break;
					case R.id.radiobtn_sina:
						Log.d(TAG, "radio button sina");break;
					case R.id.radiobtn_tencent:
						Log.d(TAG, "radio button tencent");break;
				}
				Intent intent = new Intent(AddAccountActivity.this, NewAccountActivity.class);
				startActivity(intent);
			}
		});
		
		btn_cancel.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AddAccountActivity.this, MainActivity.class);
				startActivity(intent);
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
