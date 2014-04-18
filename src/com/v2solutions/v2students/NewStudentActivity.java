package com.v2solutions.v2students;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class NewStudentActivity extends Activity implements OnClickListener{
	EditText edtName,edtRollno,edtPhysics,edtChemisty,edtMaths;
	float physics,maths,chemistry;
	String name;
	int rollno;
	Button btnSave;
	SQLiteDatabase sqLiteDatabase;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		btnSave=(Button)findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		extractStudentData();
		
		ContentValues contentValues=new ContentValues();
		contentValues.put("roll_no", rollno);
		contentValues.put("name", name);
		contentValues.put("physics", physics);
		contentValues.put("chem", chemistry);
		contentValues.put("maths", maths);
		
		sqLiteDatabase=MainActivity.writableDatabase;
		long rowid=sqLiteDatabase.insert("students", null, contentValues);
		if(rowid==-1){
			AlertDialog.Builder alert=new AlertDialog.Builder(this);
			alert.setMessage("Record already inserted.");
			alert.setTitle("Alert");
			alert.show();
		}
		else
			Log.i("Row_______________________ Inserted=====", "" + rowid);
	}
	private void extractStudentData() {
		// TODO Auto-generated method stub
		edtName=(EditText) findViewById(R.id.editText1);
		name=edtName.getText().toString();
		edtRollno=(EditText) findViewById(R.id.editText2);
		rollno=Integer.parseInt(edtRollno.getText().toString());
		edtPhysics=(EditText) findViewById(R.id.editText3);
		physics=Float.parseFloat(edtPhysics.getText().toString());
		edtChemisty=(EditText) findViewById(R.id.editText4);
		chemistry=Float.parseFloat(edtChemisty.getText().toString());
		edtMaths=(EditText) findViewById(R.id.editText5);
		maths=Float.parseFloat(edtMaths.getText().toString());
		
	}
	
}