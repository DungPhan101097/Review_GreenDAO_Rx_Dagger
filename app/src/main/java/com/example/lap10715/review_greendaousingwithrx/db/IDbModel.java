package com.example.lap10715.review_greendaousingwithrx.db;

import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;

import java.util.List;

import io.reactivex.Observable;

public interface IDbModel {

    Observable<List<Module>> getAllModule();

    Observable<List<Note>> getNotesByModule(String moduleName);

    Observable<List<Note>> getNotesByModuleAndCreateDay(Long moduleId, String createAt);

    Observable<Long> insertNote(final Note note);

    Observable<Long> insertModule(final Module module);

    Observable<Module> getModuleByName(String moduleName);
}
