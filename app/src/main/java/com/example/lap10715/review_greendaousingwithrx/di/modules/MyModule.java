package com.example.lap10715.review_greendaousingwithrx.di.modules;

import android.content.Context;

import com.example.lap10715.review_greendaousingwithrx.db.DBOpenHelper;
import com.example.lap10715.review_greendaousingwithrx.db.DbModel;
import com.example.lap10715.review_greendaousingwithrx.db.IDbModel;
import com.example.lap10715.review_greendaousingwithrx.presenter.IPresenter;
import com.example.lap10715.review_greendaousingwithrx.presenter.Presenter;


import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MyModule {

    private Context mContext;
    private String mName;

    public MyModule(Context mContext, String mName) {
        this.mContext = mContext;
        this.mName = mName;
    }

    @Provides
    public IPresenter providePresenter(IDbModel model){
        return new Presenter(model);
    }

    @Provides
    public IDbModel provideModel(DBOpenHelper dbOpenHelper){
        return new DbModel(dbOpenHelper);
    }

    @Provides
    public CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    public DBOpenHelper provideDBOpenHelper(){
        return new DBOpenHelper(mContext, mName);
    }
}
