package com.example.a91910.sqlitedatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DisplayAdapter displayAdapter;
    private SQLiteDatabase mdatabase;
    private EditText editText;
    private TextView mtextview;
    private Button button_add;
    private Button button_subs;
    private Button button_final;
    private int MAmmout = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GroceryDBHelper groceryDBHelper = new GroceryDBHelper(this);
        mdatabase = groceryDBHelper.getWritableDatabase();

        recyclerView=findViewById(R.id.recyclerviews);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displayAdapter = new DisplayAdapter(this,getAllitemcount());
        recyclerView.setAdapter(displayAdapter);
        editText = findViewById(R.id.editText);
        mtextview = findViewById(R.id.textView);
        button_add = findViewById(R.id.button);
        button_subs = findViewById(R.id.button2);
        button_final = findViewById(R.id.button3);

        //onclick functions
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        button_subs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });

        button_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             add_the_data();
             MAmmout = 0;
             mtextview.setText("0");
            }
        });
    }

    public void increase(){
        MAmmout++;
        mtextview.setText(String.valueOf(MAmmout));
    }
    public void decrease(){
        MAmmout--;
        mtextview.setText(String.valueOf(MAmmout));
    }
    public void add_the_data(){
        if(MAmmout == 0 || editText.getText().toString().trim().length() == 0){
            return;
        }
        String name = editText.getText().toString().trim();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Grocerycontract.GroceryEtry.COLUMN_NAME,name);
        contentValues.put(Grocerycontract.GroceryEtry.COLOUM_AMT,MAmmout);

        mdatabase.insert(Grocerycontract.GroceryEtry.TABLE_NAME,null,contentValues);
        displayAdapter.swapCursor(getAllitemcount());
        editText.getText().clear();
    }

    private Cursor getAllitemcount(){
        return mdatabase.query(Grocerycontract.GroceryEtry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                Grocerycontract.GroceryEtry.TimeStamp + " DESC"
                );
    }
}
