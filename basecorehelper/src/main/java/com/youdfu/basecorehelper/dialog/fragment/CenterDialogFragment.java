package com.youdfu.basecorehelper.dialog.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;


public class CenterDialogFragment<V extends ViewDataBinding> extends BaseDialogFragment<V> {
    @Deprecated
    public CenterDialogFragment() {
    }

    public static CenterDialogFragment newInstance(@LayoutRes int layoutRes) {
        CenterDialogFragment baseDialogFragment = new CenterDialogFragment();
        Bundle args = new Bundle();
        args.putInt("layoutRes", layoutRes);
        baseDialogFragment.setArguments(args);
        return baseDialogFragment;
    }
}