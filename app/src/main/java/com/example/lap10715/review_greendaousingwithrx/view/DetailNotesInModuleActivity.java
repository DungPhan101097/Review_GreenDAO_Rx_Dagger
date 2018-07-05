package com.example.lap10715.review_greendaousingwithrx.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lap10715.review_greendaousingwithrx.MyApplication;
import com.example.lap10715.review_greendaousingwithrx.R;
import com.example.lap10715.review_greendaousingwithrx.db.DBOpenHelper;
import com.example.lap10715.review_greendaousingwithrx.db.DbModel;
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
import dagger.Provides;

public class DetailNotesInModuleActivity extends AbstractActivityView {

    private static final int REQUEST_CODE = 1;
    private String mModuleName;
    private MyAdapterListNote mAdapter;
    private List<Note> mNotes;

    @Inject
    IPresenter mPresenter;

    @BindView(R.id.tv_module_name)
    TextView mTvModuleName;

    @BindView(R.id.lv_node_list)
    ListView mLvNoteListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note_module);

        ButterKnife.bind(this);

        MyComponent component = DaggerMyComponent
                .builder()
                .myModule(new MyModule(this, "MODULE"))
                .build();
        component.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bind(this);

        Intent intent = getIntent();

        if (intent != null) {
            mModuleName = intent.getStringExtra(MainActivity.SELECTED_MODULE);
            mTvModuleName.setText(mModuleName);

            mPresenter.getNotesByModule(mModuleName);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.act_add_note:
                Intent intent = new Intent(this, AddNewNote.class);
                intent.putExtra(MainActivity.SELECTED_MODULE, mModuleName);
                startActivityForResult(intent, REQUEST_CODE);
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void receiveNotesByModule(List<Note> notes) {
        mNotes = notes;
        mAdapter = new MyAdapterListNote(this, R.layout.layout_note, notes);
        mLvNoteListView.setAdapter(mAdapter);
        if (mNotes.size() == 0) {
            mPresenter.insertNote("Note Dump title", "content dump", mModuleName);
        }
    }

    @Override
    public void receiveErrorInsertNote() {
        super.receiveErrorInsertNote();
    }

    @Override
    public void receiveSuccessInsertNote(Note note) {
        super.receiveSuccessInsertNote(note);
        mNotes.add(note);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                String titleNote = data.getStringExtra(AddNewNote.TITLE_NOTE);
                String contentNote = data.getStringExtra(AddNewNote.CONTENT_NOTE);

                mPresenter.insertNote(titleNote, contentNote, mModuleName);
            }
        }
    }
}
