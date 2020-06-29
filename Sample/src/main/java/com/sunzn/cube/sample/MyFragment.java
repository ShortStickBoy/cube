package com.sunzn.cube.sample;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sunzn.cube.library.Cube;

public class MyFragment extends Cube<MyFragment> {

    public static final int FINISH = 1;

    public interface ActionListener {

        void exec();

    }

    public ActionListener mListener;

    public MyFragment setActionListener(ActionListener listener) {
        mListener = listener;
        return this;
    }

    @Override
    public int initContentView() {
        return R.layout.dialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        ProgressBar progress = findViewById(R.id.progressBar);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.exec();
            }
        });
    }

    @Override
    public int getWidth() {
        return WindowManager.LayoutParams.MATCH_PARENT;
    }

}
