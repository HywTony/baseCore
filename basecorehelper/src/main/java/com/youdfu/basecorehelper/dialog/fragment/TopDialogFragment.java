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


public class TopDialogFragment<V extends ViewDataBinding> extends BaseDialogFragment<V> {


    @Deprecated
    public TopDialogFragment() {
    }

    public static TopDialogFragment newInstance(@LayoutRes int layoutRes) {
        TopDialogFragment baseDialogFragment = new TopDialogFragment();
        Bundle args = new Bundle();
        args.putInt("layoutRes", layoutRes);
        baseDialogFragment.setArguments(args);
        return baseDialogFragment;
    }

    public TopDialogFragment setMarginTop(int marginTop) {
        getArguments().putInt("marginTop", marginTop);
        return this;
    }

    public TopDialogFragment setMarginLeft(int marginLeft) {
        getArguments().putInt("marginLeft", marginLeft);
        return this;
    }

    public TopDialogFragment setMarginRight(int marginRight) {
        getArguments().putInt("marginRight", marginRight);
        return this;
    }

    public TopDialogFragment setGravity(int gravity) {
        getArguments().putInt("gravity", gravity);
        return this;
    }

    public TopDialogFragment setFullWidth(boolean fullWidth) {
        getArguments().putBoolean("fullWidth", fullWidth);
        return this;
    }

    @Override
    public void onActivityCreated(Bundle arg0) {
        super.onActivityCreated(arg0);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogTopAnimation;
    }

    public void onStart() {
        super.onStart();

        int marginTop = getArguments().getInt("marginTop", 0);
        int marginLeft = getArguments().getInt("marginLeft", 0);
        int marginRight = getArguments().getInt("marginRight", 0);
        boolean fullWidth = getArguments().getBoolean("fullWidth", true);
        int gravity = getArguments().getInt("gravity", 0);

        Window window = getDialog().getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = gravity > 0 ? gravity : Gravity.TOP;

        if (fullWidth)
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
        if (marginTop > 0)
            params.y = marginTop;
        if (marginLeft > 0)
            params.x = marginLeft;
        if (marginRight > 0)
            params.x = marginRight;

        window.setAttributes(params);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}