package com.sunzn.cube.library;

import androidx.fragment.app.DialogFragment;

public abstract class Base extends DialogFragment {

    public interface DismissListener {
        void onDismiss(int code);
    }

    public abstract int initContentView();

}
