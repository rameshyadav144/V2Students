package com.v2solutions.v2students;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShowRank extends Activity {

	private SQLiteDatabase db;
	private TableRow tableRow;
	private TextView txtName;
	private TextView txtRollNo;
	private ViewGroup tableLayout;
	private TextView txtRank;
	private int total[];
	private int[] rollNo;
	private int[] temp;
	private int[] temp2;
	private Object[] names;
	private Object[] tempName;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		db = MainActivity.writableDatabase;
		tableLayout = new TableLayout(this);
		fetchPassData();
		
		//setContentView(tableLayout);
		sortTotal();
		createUI();
		setContentView(tableLayout);
		
	}

	private void createUI() {
		// TODO Auto-generated method stub
		
		for(int i = 0 ; i < total.length;i++){
			tableRow = new TableRow(this);
			txtName = new TextView(this);
			txtRollNo = new TextView(this);
			txtRank = new TextView(this);
			txtRollNo.setText(rollNo[i]+"");
			tableRow.addView(txtRollNo);
			txtName.setText(names[i]+"");
			tableRow.addView(txtName);
			txtRank.setText((i+1)+"");
			tableRow.addView(txtRank);
			tableLayout.addView(tableRow);
			
			
			
		}
		
	}

	private void fetchPassData() {
		// TODO Auto-generated method stub
		Cursor cursor = db.query("students", new String[] { "roll_no",
		"name","maths","physics","chem" }, "physics>=35 and chem>=35 and maths>=35", null,
		null, null, null);
int count = cursor.getCount();
total = new int[count];
rollNo = new int[count];
temp = new int[count];
temp2 = new int[count];
Log.i("count",""+count);
names = new String[count];
tempName = new String[count];
cursor.moveToFirst();
if (count > 0) {
	int i = 0;
	while(!cursor.isAfterLast())
		{
		Log.i("Maths",cursor.getFloat(4)+"");
		
		total[i] = (int)(cursor.getFloat(cursor.getColumnIndex("maths")) + cursor.getFloat(cursor.getColumnIndex("physics")) + cursor.getFloat(cursor.getColumnIndex("chem")));
		
		rollNo[i] =cursor.getInt(0);
		names[i] = cursor.getString(cursor.getColumnIndex("name"));
		i++;
		
			
		
		
		
		/*txtName.setText(cursor.getString(1));
		tableRow.addView(txtName);
		txtRollNo.setText(cursor.getString(0)+"");
		
		tableRow.addView(txtRollNo);
		
		tableLayout.addView(tableRow);*/
		cursor.moveToNext();
	} 
}

		
	}
	public void sortTotal(){
		for(int i = 0 ; i < total.length ;i++){
			for(int j = i+1 ; j <total.length ; j++){
				if(total[i]<total[j]){
				temp[i] = total[i];
				total[i] = total[j];
				total[j] = temp[i];
				temp2[i]=rollNo[i];
				rollNo[i]=rollNo[j];
				rollNo[j]=temp2[i];
				tempName[i]=names[i];
				names[i] = names[j];
				names[j] = tempName[i];
				
				
				}
			}
		}
		for(int i = 0; i < total.length;i++){
		Log.i("Soreted",total[i]+" Roll No "+ rollNo[i] + "Rank "+(i+1)+" Name"+ names[i]);	
		}
		
		
	}

	
}
