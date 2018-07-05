package com.example.lap10715.review_greendaousingwithrx.view;

import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;

import java.util.List;

public interface IView {
    void receiveModules(List<Module> modules);

    void receiveNotesByModule(List<Note> notes);

    void receiveNotesByModuleAndCreateDay(List<Note> notes);

    void receiveErrorInsertNote();

    void receiveSuccessInsertNote(Note note);

    void receiveErrorInsertModule();

    void receiveSuccessInsertModule(Module module);
}
