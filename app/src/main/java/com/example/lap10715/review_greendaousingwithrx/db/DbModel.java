package com.example.lap10715.review_greendaousingwithrx.db;

import android.database.sqlite.SQLiteDatabase;

import com.example.lap10715.review_greendaousingwithrx.db.model.DaoMaster;
import com.example.lap10715.review_greendaousingwithrx.db.model.DaoSession;
import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.ModuleDao;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;
import com.example.lap10715.review_greendaousingwithrx.view.MainActivity;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;

public class DbModel implements IDbModel {

    private final DaoSession mDaoSession;

    public DbModel(DBOpenHelper dbOpenHelper) {
        this.mDaoSession = new DaoMaster(dbOpenHelper.getWritableDb()).newSession();
    }

    @Override
    public Observable<List<Module>> getAllModule() {
        return Observable.fromCallable(() -> mDaoSession.getModuleDao().loadAll());
    }

    @Override
    public Observable<List<Note>> getNotesByModule(String moduleName) {

//        Module module =  mDaoSession.getModuleDao()
//                .queryBuilder()
//                .where(ModuleDao.Properties.Name.eq(moduleName))
//                .unique();
        return Observable.fromCallable(() -> {
            List<Module> modules = mDaoSession.getModuleDao()
                    .queryBuilder()
                    .where(ModuleDao.Properties.Name.eq(moduleName))
                    .list();
            Module module = modules.get(0);
            return module.getNoteList();
        });
    }

    @Override
    public Observable<List<Note>> getNotesByModuleAndCreateDay(Long moduleId, String createAt) {
        return Observable.fromCallable(() -> {
            QueryBuilder<Module> qb = mDaoSession.getModuleDao().queryBuilder();
            qb.where(ModuleDao.Properties.Id.eq(moduleId),
                    ModuleDao.Properties.CreateAt.eq(createAt));

            Module module = qb.uniqueOrThrow();

            if (module != null)
                return module.getNoteList();
            else
                return null;
        });
    }

    @Override
    public Observable<Long> insertNote(Note note) {
        return Observable.fromCallable(() -> mDaoSession.getNoteDao().insert(note));
    }

    @Override
    public Observable<Long> insertModule(Module module) {
        return Observable.fromCallable(() -> mDaoSession.getModuleDao().insert(module));
    }

    @Override
    public Observable<Module> getModuleByName(String moduleName) {

        return Observable.fromCallable(() -> {
            List<Module> modules = mDaoSession.getModuleDao()
                    .queryBuilder()
                    .where(ModuleDao.Properties.Name.eq(moduleName))
                    .list();
            return modules.get(0);
        });
    }
}
