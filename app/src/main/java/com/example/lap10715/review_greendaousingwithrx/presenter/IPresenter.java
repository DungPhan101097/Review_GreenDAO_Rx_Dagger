package com.example.lap10715.review_greendaousingwithrx.presenter;


import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;
import com.example.lap10715.review_greendaousingwithrx.view.IView;

import java.util.List;

import io.reactivex.Observable;

public interface IPresenter {

    void bind(IView view);

    void unbind();

    void getAllModule();

    void getNotesByModule(String moduleName);

    void getNotesByModuleAndCreateDay(Module module, String createAt);

    void insertNote(String title, String content, String module);

    void insertModule(String nameModule);

}
