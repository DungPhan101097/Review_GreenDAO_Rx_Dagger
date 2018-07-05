package com.example.lap10715.review_greendaousingwithrx.di.components;

import com.example.lap10715.review_greendaousingwithrx.di.modules.MyModule;
import com.example.lap10715.review_greendaousingwithrx.presenter.Presenter;
import com.example.lap10715.review_greendaousingwithrx.view.DetailNotesInModuleActivity;
import com.example.lap10715.review_greendaousingwithrx.view.MainActivity;

import dagger.Component;

@Component(modules = {MyModule.class})
public interface MyComponent {
    void inject(MainActivity mainActivity);

    void inject(DetailNotesInModuleActivity detailActivity);
}
