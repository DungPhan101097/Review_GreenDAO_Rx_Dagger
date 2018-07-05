package com.example.lap10715.review_greendaousingwithrx.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.lap10715.review_greendaousingwithrx.R;
import com.example.lap10715.review_greendaousingwithrx.db.DBOpenHelper;
import com.example.lap10715.review_greendaousingwithrx.db.DbModel;
import com.example.lap10715.review_greendaousingwithrx.presenter.IPresenter;
import com.example.lap10715.review_greendaousingwithrx.presenter.Presenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddNewNote extends AbstractActivityView {

    public static final String TITLE_NOTE = "title_note";
    public static final String CONTENT_NOTE = "content_note";

    @BindView(R.id.edt_title)
    EditText mEdtTitle;

    @BindView(R.id.edt_content)
    EditText mEdtContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        ButterKnife.bind(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick({R.id.btn_add_note})
    public void addNote(){
        String title = mEdtTitle.getText().toString();
        String content = mEdtContent.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra(TITLE_NOTE, title);
        resultIntent.putExtra(CONTENT_NOTE, content);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

}
