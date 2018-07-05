package com.example.lap10715.review_greendaousingwithrx.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.lap10715.review_greendaousingwithrx.db.model.DaoMaster;

public class DBOpenHelper extends DaoMaster.OpenHelper{
    public DBOpenHelper(Context context, String name) {
        super(context, name);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);

        switch (oldVersion){
            case 1:
            case 2:

        }
    }
}
