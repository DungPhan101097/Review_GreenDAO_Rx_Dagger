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
import com.example.lap10715.review_greendaousingwithrx.db.model.Module;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapterSpinner extends ArrayAdapter<String> {

    private Context mContext;
    private List<Module> mModuleList;

    @BindView(R.id.tv_module_name)
    TextView mTvModuleName;

    public MyAdapterSpinner(@NonNull Context context, int resource, List<Module> modules) {
        super(context, resource);
        this.mContext = context;
        this.mModuleList = modules;
    }


    @Override
    public int getCount() {
        return mModuleList.size();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
        View row = inflater.inflate(R.layout.layout_item_spn, parent, false);
        ButterKnife.bind(this, row);

        mTvModuleName.setText(mModuleList.get(position).getName());

        return row;
    }
}
