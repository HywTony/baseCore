package com.youdfu.basecorehelper.dialog.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


public class BaseDialogFragment<V extends ViewDataBinding> extends DialogFragment {
    public V mDataBinding;
    View rootView;

    @Deprecated
    public BaseDialogFragment() {
    }


    SparseArray<View.OnClickListener> clickEvents = new SparseArray<>();

    public static BaseDialogFragment newInstance(@LayoutRes int layoutRes) {
        BaseDialogFragment baseDialogFragment = new BaseDialogFragment();
        Bundle args = new Bundle();
        args.putInt("layoutRes", layoutRes);
        baseDialogFragment.setArguments(args);
        return baseDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int resLayout = getArguments().getInt("layoutRes");
        mDataBinding = DataBindingUtil.inflate(
                inflater,
                resLayout,
                container,
                false);
        rootView = mDataBinding.getRoot();

        //添加这一行,无标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        //绑定点击事件
        for (int i = 0; i < clickEvents.size(); i++) {
            rootView.findViewById(clickEvents.keyAt(i)).setOnClickListener(clickEvents.valueAt(i));
        }

        afterCreated();
        if (mOnLifeCycle != null) {
            mOnLifeCycle.aftreCreated();
        }
        return rootView;
    }

    public void afterCreated() {

    }

    public BaseDialogFragment setClick(int resId, View.OnClickListener listener) {
        clickEvents.put(resId, listener);
        return this;
    }

    public BaseDialogFragment setDialogCancelable(boolean cancelable) {
        super.setCancelable(cancelable);
        return this;
    }

    public void show(FragmentActivity context) {
//        if (null != getActivity() && !getActivity().isFinishing() && (null == getDialog() || !getDialog().isShowing())) {
            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(this, "dialog");
            transaction.commitAllowingStateLoss();
            transaction.show(this);
//        }
    }

    @Override
    public void dismiss() {
//        if (null != getActivity() && !getActivity().isFinishing() && null != getDialog() && getDialog().isShowing()) {
            super.dismissAllowingStateLoss();
//        }
    }

    OnLifeCycle mOnLifeCycle;

    public BaseDialogFragment setOnLifeCycle(OnLifeCycle mOnLifeCycle) {
        this.mOnLifeCycle = mOnLifeCycle;
        return this;
    }

    public interface OnLifeCycle {
        void aftreCreated();
    }
}