package com.v2solutions.v2students;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class StudentDB extends SQLiteOpenHelper{
//	public static SQLiteDatabase sqLiteDatabase;
	public StudentDB(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase sqlDB) {

		Log.i("StudentDB : ","onCreate");
		String createTable = "CREATE TABLE students(roll_no INT PRIMARY KEY, name VARCHAR(25), physics REAL, chem REAL, maths REAL)";
		sqlDB.execSQL(createTable);
		Log.i("onCreate", "Table Created");
//		sqLiteDatabase=getWritableDatabase();
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
		
	}
//	public SQLiteDatabase getSQLiteDatabase(){
//		
//		return sqLiteDatabase;
//	}
}
