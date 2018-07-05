package com.example.lap10715.review_greendaousingwithrx;

import android.app.Application;
import android.content.Context;

import com.example.lap10715.review_greendaousingwithrx.db.DBOpenHelper;
import com.example.lap10715.review_greendaousingwithrx.db.DbModel;
import com.example.lap10715.review_greendaousingwithrx.db.IDbModel;
import com.example.lap10715.review_greendaousingwithrx.presenter.IPresenter;
import com.example.lap10715.review_greendaousingwithrx.presenter.Presenter;

import java.util.ConcurrentModificationException;

public class MyApplication extends Application {

    private IDbModel mModel;

    @Override
    public void onCreate() {
        super.onCreate();

        Context context = getApplicationContext();
        this.mModel = new DbModel(new DBOpenHelper(context, "MODULE"));
    }

    public IDbModel getModel() {
        return mModel;
    }

}
