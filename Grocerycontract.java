package com.example.a91910.sqlitedatabase;

import android.provider.BaseColumns;

public class Grocerycontract {

    private Grocerycontract(){}
    public static final class GroceryEtry implements BaseColumns{
        public static final String TABLE_NAME = "grocerylist";
        public static final String COLUMN_NAME = "name";
        public static final String COLOUM_AMT = "amount";
        public static final String TimeStamp = "timestmp";

    }
}
