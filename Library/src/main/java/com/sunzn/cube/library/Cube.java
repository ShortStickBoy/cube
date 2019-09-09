package com.sunzn.cube.library;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("ALL")
public abstract class Cube<T extends Cube> extends Base {

    private boolean mCancel = true;

    private float mDimAmount = 0.5F;

    private DismissListener mDismissListener;

    private int mDismissCode = Integer.MAX_VALUE;

    private int mGravity = Gravity.START | Gravity.BOTTOM;

    private int mAnimation = R.style.Animation_Action_Fragment;

    private int mWidth = WindowManager.LayoutParams.MATCH_PARENT;

    private int mHeight = WindowManager.LayoutParams.WRAP_CONTENT;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        dialog.setCanceledOnTouchOutside(isCanceledOnTouch());
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setLayout(getWidth(), getHeight());
            window.setDimAmount(getDimAmount());
            window.setGravity(getGravity());
            window.setWindowAnimations(getAnimation());
        }
        return inflater.inflate(getLayoutRes(), container, false);
    }

    public T setDismissListener(DismissListener listener) {
        mDismissListener = listener;
        return (T) this;
    }

    public T setCanceledOnTouch(boolean cancel) {
        mCancel = cancel;
        return (T) this;
    }

    public boolean isCanceledOnTouch() {
        return mCancel;
    }

    public T setCancelAble(boolean cancel) {
        setCancelable(cancel);
        return (T) this;
    }

    public T setGravity(int gravity) {
        mGravity = gravity;
        return (T) this;
    }

    public int getGravity() {
        return mGravity;
    }

    public T setDimAmount(float amount) {
        mDimAmount = amount;
        return (T) this;
    }

    private float getDimAmount() {
        return mDimAmount;
    }

    public T setAnimation(int animation) {
        mAnimation = animation;
        return (T) this;
    }

    public int getAnimation() {
        return mAnimation;
    }

    public T setWidth(int width) {
        mWidth = width;
        return (T) this;
    }

    public int getWidth() {
        return mWidth;
    }

    public T setHeight(int height) {
        mHeight = height;
        return (T) this;
    }

    public int getHeight() {
        return mHeight;
    }

    public void show(FragmentManager manager) {
        try {
            show(manager, this.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T setShowTime(long millis) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (getContext() != null) dismiss();
            }
        }, millis);
        return (T) this;
    }

    public void postDelayFade(long millis) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (getContext() != null) dismiss();
            }
        }, millis);
    }

    public void dismissWithCode(int code) {
        setDismissCode(code);
        dismiss();
    }

    private void setDismissCode(int code) {
        mDismissCode = code;
    }

    private int getDismissCode() {
        return mDismissCode;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        onFragmentDismiss();
    }

    private void onFragmentDismiss() {
        if (mDismissListener != null) mDismissListener.onDismiss(getDismissCode());
    }

}
