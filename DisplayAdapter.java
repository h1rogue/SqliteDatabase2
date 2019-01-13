package com.example.a91910.sqlitedatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.MyViewHolder> {
private Context mcontext;
private Cursor mcursor;
 public DisplayAdapter(Context context, Cursor cursor){
     mcontext = context;
     mcursor = cursor;
 }
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.grocerylist,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        if(!mcursor.moveToPosition(i)){
            return ;
        }
     String name = mcursor.getString(mcursor.getColumnIndex(Grocerycontract.GroceryEtry.COLUMN_NAME));
     String amount = mcursor.getString(mcursor.getColumnIndex(Grocerycontract.GroceryEtry.COLOUM_AMT));

     myViewHolder.textViewname.setText(name);
     myViewHolder.textViewAmout.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        return mcursor.getCount();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewname;
         public TextView textViewAmout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewname=itemView.findViewById(R.id.textView3);
            textViewAmout=itemView.findViewById(R.id.textView2);
        }
    }
    public void swapCursor(Cursor newcursor){
     if(mcursor!=null)
     {
         mcursor.close();
     }
     mcursor = newcursor;
     if(mcursor!=null){
         notifyDataSetChanged();
     }
    }
}
