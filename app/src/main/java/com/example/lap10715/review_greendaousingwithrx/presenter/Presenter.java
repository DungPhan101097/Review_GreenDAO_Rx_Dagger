package com.example.lap10715.review_greendaousingwithrx.presenter;

import com.example.lap10715.review_greendaousingwithrx.db.IDbModel;
import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;
import com.example.lap10715.review_greendaousingwithrx.view.IView;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Presenter implements IPresenter {

    private IView mView;

    private IDbModel mModel;

    @Inject
    CompositeDisposable mComs;

    public Presenter(IDbModel mModel) {
        this.mModel= mModel;
    }

    public void bind(IView view) {
        this.mView = view;
        mComs = new CompositeDisposable();
    }

    public void unbind() {
        this.mView = null;
        mComs.dispose();
        mComs.clear();
    }

    @Override
    public void getAllModule() {
        mComs.add(mModel.getAllModule()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(modules -> mView.receiveModules(modules)));
    }

    @Override
    public void getNotesByModule(String moduleName) {
        mComs.add(mModel.getNotesByModule(moduleName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> mView.receiveNotesByModule(notes)
                ));
    }

    @Override
    public void getNotesByModuleAndCreateDay(Module module, String createAt) {
        mComs.add(mModel.getNotesByModuleAndCreateDay(module.getId(), createAt)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notes -> mView.receiveNotesByModuleAndCreateDay(notes)));
    }

    @Override
    public void insertNote(String title, String content, String module) {
        Date date = new Date();
        String createAt = new SimpleDateFormat("MM-dd-yyyy").format(date);

       mComs.add(mModel.getModuleByName(module)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(module1 -> {
                    Note note = new Note(null, title, content, createAt, module1.getId());

                    mComs.add(mModel.insertNote(note)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(aLong -> {
                                if (aLong < 0)
                                    mView.receiveErrorInsertNote();
                                else
                                    mView.receiveSuccessInsertNote(note);
                            }));
                }));
    }

    @Override
    public void insertModule(String nameModule) {
        Date date = new Date();
        String createAt = new SimpleDateFormat("MM-dd-yyyy").format(date);

        Module module = new Module(null, nameModule, createAt);
        mComs.add(mModel.insertModule(module)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (aLong < 0)
                        mView.receiveErrorInsertModule();
                    else
                        mView.receiveSuccessInsertModule(module);
                }));
    }
}
