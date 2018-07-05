package com.example.lap10715.review_greendaousingwithrx.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lap10715.review_greendaousingwithrx.R;
import com.example.lap10715.review_greendaousingwithrx.db.model.Note;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterListNote extends ArrayAdapter {
    private Context mContext;
    private int mResource;
    private List<Note> mListNote;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @BindView(R.id.tv_content)
    TextView mTvContent;

    @BindView(R.id.tv_date)
    TextView mTvDate;

    public MyAdapterListNote(@NonNull Context context, int resource, List<Note> notes) {
        super(context, resource);
        this.mContext = context;
        this.mResource = resource;
        this.mListNote = notes;
    }

    @Override
    public int getCount() {
        return mListNote.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return mListNote.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View row = inflater.inflate(mResource, parent, false);

        ButterKnife.bind(this, row);
        Note note = mListNote.get(position);

        mTvTitle.setText(note.getTitle());
        mTvContent.setText(note.getContent());
        mTvDate.setText(note.getCreateAt());

        return row;
    }
}
