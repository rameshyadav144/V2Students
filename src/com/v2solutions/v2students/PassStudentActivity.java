package com.v2solutions.v2students;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class PassStudentActivity extends Activity {

	SQLiteDatabase db;
	TableLayout tableLayout;
	TableRow tableRow;
	TextView txvHeader, txvRoll_No_Header, txvName_Header,txtName,txtRollNo;
	int roll_no;
	String name;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("report activity","inside");
		
		Intent intent = getIntent();
		String data = intent.getStringExtra("report");
		Log.i("report activity",data);
		
		CreateAcivityHeader(data);
		getDataReport(data);
		setContentView(tableLayout);
	}

	public void getDataReport(String data) {
		db = MainActivity.writableDatabase;
		if (data.equals("pass")) {

			// String
			// query="select roll_no,name from students where physics>=35 and chem>=35 and maths>=35";
			// db.execSQL(query);

			Cursor cursor = db.query("students", new String[] { "roll_no",
					"name" }, "physics>=35 and chem>=35 and maths>=35", null,
					null, null, null);
			int count = cursor.getCount();
			Log.i("count",""+count);
			
			cursor.moveToFirst();
			if (count > 0) {
				
				while(!cursor.isAfterLast())
					{tableRow = new TableRow(this);
					txtName = new TextView(this);
					txtRollNo = new TextView(this);
					txtName.setText(cursor.getString(1));
					tableRow.addView(txtName);
					txtRollNo.setText(cursor.getString(0)+"");
					tableRow.addView(txtRollNo);
					tableLayout.addView(tableRow);
					cursor.moveToNext();
				} 
			}
		} 
		else if (data.equals("fail")) {

			// String
			// query="select roll_no,name from students where physics>=35 and chem>=35 and maths>=35";
			// db.execSQL(query);

			Cursor cursor = db.query("students", new String[] { "roll_no",
					"name" }, "physics<35 or chem<35 or maths<35", null,
					null, null, null);
			int count = cursor.getCount();
			Log.i("count",""+count);
			
			cursor.moveToFirst();
			if (count > 0) {
				
				while(!cursor.isAfterLast())
					{tableRow = new TableRow(this);
					txtName = new TextView(this);
					txtRollNo = new TextView(this);
					txtName.setText(cursor.getString(1));
					tableRow.addView(txtName);
					txtRollNo.setText(cursor.getString(0)+"");
					tableRow.addView(txtRollNo);
					tableLayout.addView(tableRow);
					cursor.moveToNext();
				} 
			}
		}
		else {
			Log.i("Error", "No record to display");
		}
		
		
	}

	private void CreateAcivityHeader(String data) {
		// TODO Auto-generated method stub
		Log.i("CreateAcivityHeader", "inside");
		tableLayout = new TableLayout(this);
		TableRow.LayoutParams params=new TableRow.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		tableRow = new TableRow(this);
		txvHeader = new TextView(this);
		txvHeader.setLayoutParams(params);
		txvName_Header = new TextView(this);
		txvName_Header.setLayoutParams(params);
		txvRoll_No_Header = new TextView(this);
		txvRoll_No_Header.setLayoutParams(params);
			
		// Header

		if (data.equals("pass")) {
			txvHeader.setText("List Of Passed Students");
			tableRow.addView(txvHeader);
//			tableLayout.addView(tableRow);
			//

			// First Row Header
			txvRoll_No_Header.setText("Roll");
			tableRow.addView(txvRoll_No_Header);

			txvName_Header.setText("Name");
			tableRow.addView(txvName_Header);

			tableLayout.addView(tableRow);
			setContentView(tableLayout);
		} else if (data.equals("fail")) {
			
				txvHeader.setText("List Of Failed Students");

			

			tableRow.addView(txvHeader);
//			tableLayout.addView(tableRow);
			//

			// First Row Header
			txvRoll_No_Header.setText("Roll");
			tableRow.addView(txvRoll_No_Header);

			txvName_Header.setText("Name");
			tableRow.addView(txvName_Header);

			tableLayout.addView(tableRow);
			//
		}
		Log.i("CreateAcivityHeader", "end");
	
	}
}
