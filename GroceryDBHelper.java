package com.example.a91910.sqlitedatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.a91910.sqlitedatabase.Grocerycontract.*;

public class GroceryDBHelper extends SQLiteOpenHelper {
    public static final String DATABBASE_NAME = "grocerylist.db";
    public static final int versions = 1;
    public GroceryDBHelper( Context context) {
        super(context, DATABBASE_NAME, null, versions);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
   db.execSQL("CREATE TABLE "+GroceryEtry.TABLE_NAME+" ("+GroceryEtry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
   +GroceryEtry.COLUMN_NAME+" TEXT NOT NULL,"+GroceryEtry.COLOUM_AMT+" INTEGER NOT NULL,"+
   GroceryEtry.TimeStamp+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  db.execSQL("DROP TABLE IF EXISTS "+GroceryEtry.TABLE_NAME);
    onCreate(db);
    }
}
