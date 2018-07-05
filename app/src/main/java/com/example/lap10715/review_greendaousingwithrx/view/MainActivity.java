package com.example.lap10715.review_greendaousingwithrx.view;

import android.app.Application;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.lap10715.review_greendaousingwithrx.MyApplication;
import com.example.lap10715.review_greendaousingwithrx.R;
import com.example.lap10715.review_greendaousingwithrx.db.DBOpenHelper;
import com.example.lap10715.review_greendaousingwithrx.db.DbModel;
import com.example.lap10715.review_greendaousingwithrx.db.model.Module;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;
import com.example.lap10715.review_greendaousingwithrx.di.components.DaggerMyComponent;
import com.example.lap10715.review_greendaousingwithrx.di.components.MyComponent;
import com.example.lap10715.review_greendaousingwithrx.di.modules.MyModule;
import com.example.lap10715.review_greendaousingwithrx.presenter.IPresenter;
import com.example.lap10715.review_greendaousingwithrx.presenter.Presenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AbstractActivityView {

    public static final String SELECTED_MODULE = "selected_module";
    private List<Module> mModuleList;
    private ArrayAdapter<String> mAdapter;

    @Inject
    IPresenter mPresenter;

    @BindView(R.id.btn_add_module)
    FloatingActionButton mBtnAddModule;

    @BindView(R.id.spn_modules)
    Spinner mSpnModules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MyComponent component = DaggerMyComponent
                .builder()
                .myModule(new MyModule(this, "MODULE"))
                .build();
        component.inject(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.act_get_notes_by_nmodule:
                Intent intent = new Intent(this, DetailNotesInModuleActivity.class);

                int indexModuleSelected = mSpnModules.getSelectedItemPosition();
                Module module = mModuleList.get(indexModuleSelected);

                intent.putExtra(SELECTED_MODULE, module.getName());
                startActivity(intent);
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bind(this);

        loadModules();
    }

    private void loadModules() {
        mPresenter.getAllModule();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unbind();
    }

    @Override
    public void receiveModules(List<Module> modules) {
        mModuleList = modules;
        mAdapter = new MyAdapterSpinner(this, R.layout.layout_item_spn, mModuleList);
        mSpnModules.setAdapter(mAdapter);

        if(mModuleList.size() == 0){
            mPresenter.insertModule("Learn");
        }
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
        // do something.
    }

    @Override
    public void receiveSuccessInsertModule(Module module) {
        mModuleList.add(module);
        mAdapter.notifyDataSetChanged();
    }

    public void insertModule(String moduleName){
        mPresenter.insertModule(moduleName);
    }

    @OnClick(R.id.btn_add_module)
    public void addNewModule(){
        AddModuleDialog addModuleDialog = new AddModuleDialog(this);
        addModuleDialog.show();
    }

}