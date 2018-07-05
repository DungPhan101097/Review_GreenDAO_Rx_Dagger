package com.example.lap10715.review_greendaousingwithrx.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;

import com.example.lap10715.review_greendaousingwithrx.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddModuleDialog extends Dialog implements View.OnClickListener{

    private Context mContext;

    @BindView(R.id.btn_yes)
    Button mBtnYes;

    @BindView(R.id.btn_no)
    Button mBtnNo;

    @BindView(R.id.edt_module_name)
    TextView mTvModuleName;

    public AddModuleDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_module);

        ButterKnife.bind(this);

        mBtnNo.setOnClickListener(this);
        mBtnYes.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.insertModule(mTvModuleName.getText().toString());
                break;
            case R.id.btn_no:
                dismiss();
                break;
        }
        dismiss();
    }
}
