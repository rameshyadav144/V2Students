package com.v2solutions.v2students;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;


@EActivity(R.layout.activity_main)
public class MainActivity extends Activity implements OnClickListener {

	Button btNew,btDetail,btMarksheet,btRank;
	StudentDB db;
	static SQLiteDatabase writableDatabase;
	
	Context context = this;
	private String APP_PREFERENCE = "student";
	private String IS_ICON_CREATED = "YES";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		
		/* if(!getSharedPreferences(APP_PREFERENCE, Activity.MODE_PRIVATE).getBoolean(IS_ICON_CREATED, false)){
			  createShortcut();
	          getSharedPreferences(APP_PREFERENCE, Activity.MODE_PRIVATE).edit().putBoolean(IS_ICON_CREATED, true).commit();
	      }*/
		 
	}
		//Creating ShortCut 
		
		
		 
		// createShortcut();
	      
		
	
	
	private void createShortcut() {
		Intent shortcutIntent = new Intent();
		shortcutIntent.setClassName("com.v2solutions.v2students",
				"com.v2solutions.v2students.MainActivity_");
		shortcutIntent.setAction(Intent.ACTION_MAIN);
		
		 shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 

		Intent addIntent = new Intent();
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "V2Students");

		addIntent.putExtra("duplicate", false);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(context,
						R.drawable.student));

		addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		context.sendBroadcast(addIntent);
		
	}



	@AfterViews 
	void afterViews(){
		
		btNew=(Button) findViewById(R.id.btNew);
		btDetail=(Button) findViewById(R.id.btShowPass);
		btMarksheet=(Button) findViewById(R.id.btMarksheet);
		btRank=(Button) findViewById(R.id.btRank);
		btNew.setOnClickListener(this);
		btDetail.setOnClickListener(this);
		btMarksheet.setOnClickListener(this);
		btRank.setOnClickListener(this);
		if(db == null){
			db = new StudentDB(this, "StudentsDB", null, 1);
			writableDatabase = db.getWritableDatabase();
		}
		
	}
	
	/*private void createShortcut() {

		Intent shortcutIntent = new Intent();
		shortcutIntent.setClassName("com.v2solutions.v2students",
				"com.v2solutions.v2students.MainActivity");
		shortcutIntent.setAction(Intent.ACTION_MAIN);
		
		 * shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		 * shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 

		Intent addIntent = new Intent();
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "V2Students");

		addIntent.putExtra("duplicate", false);
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
				Intent.ShortcutIconResource.fromContext(context,
						R.drawable.ic_launcher));

		addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
		context.sendBroadcast(addIntent);
	}*/


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
			case R.id.btNew:
			{
				Intent intent=new Intent(this,NewStudentActivity.class);
				startActivity(intent);
				break;
				
			}
			case R.id.btShowPass:
			{
				Log.i("inside btShowPass ","btShowPass clicked");
				final Dialog dialog=new Dialog(this);
				dialog.setContentView(R.layout.activity_radio);
				dialog.setTitle("Select Result");
				dialog.show();
				RadioButton rbPass,rbFail;
				rbPass=(RadioButton) dialog.findViewById(R.id.rbPass);
				rbFail=(RadioButton) dialog.findViewById(R.id.rbFail);
				rbPass.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.i("radio 1","inside");
						// TODO Auto-generated method stub
						Intent intent=new Intent(MainActivity.this,PassStudentActivity.class);
						intent.putExtra("report", "pass");
						startActivity(intent);
						dialog.dismiss();
					}
				});
				rbFail.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Log.i("radio 1","inside");
						// TODO Auto-generated method stub
						Intent intent=new Intent(MainActivity.this,PassStudentActivity.class);
						intent.putExtra("report", "fail");
						startActivity(intent);
						dialog.dismiss();
					}
				});
				break;
			}
			case R.id.btMarksheet:
			{
				Dialog dialog=new Dialog(this);
				dialog.setContentView(R.layout.activity_rollno);
				dialog.setTitle("Enter Roll Number:");
				TextView tv;
				EditText et;
				break;
			}
			case R.id.btRank:
			{
				Intent intent = new Intent(this,ShowRank.class);
				startActivity(intent);
				break;
			}
		}
	}


}
