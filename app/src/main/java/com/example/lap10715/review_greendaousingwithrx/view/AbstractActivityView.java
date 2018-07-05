package com.example.lap10715.review_greendaousingwithrx.view;

import android.support.v7.app.AppCompatActivity;

import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;

import java.util.List;

public class AbstractActivityView  extends AppCompatActivity
        implements IView {

    @Override
    public void receiveModules(List<Module> modules) {

    }

    @Override
    public void receiveNotesByModule(List<Note> notes) {

    }

    @Override
    public void receiveNotesByModuleAndCreateDay(List<Note> notes) {

    }

    @Override
    public void receiveErrorInsertNote() {

    }

    @Override
    public void receiveSuccessInsertNote(Note note) {

    }

    @Override
    public void receiveErrorInsertModule() {

    }

    @Override
    public void receiveSuccessInsertModule(Module module) {

    }
}
