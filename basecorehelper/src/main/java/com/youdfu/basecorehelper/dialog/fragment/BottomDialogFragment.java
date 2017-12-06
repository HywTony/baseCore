package com.youdfu.basecorehelper.dialog.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.youdfu.basecorehelper.R;


public class BottomDialogFragment<V extends ViewDataBinding> extends BaseDialogFragment<V> {
    @Deprecated
    public BottomDialogFragment() {
    }

    public static BottomDialogFragment newInstance(@LayoutRes int layoutRes) {
        BottomDialogFragment baseDialogFragment = new BottomDialogFragment();
        Bundle args = new Bundle();
        args.putInt("layoutRes", layoutRes);
        baseDialogFragment.setArguments(args);
        return baseDialogFragment;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}