package com.youdfu.basecorehelper.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youdfu.basecorehelper.R;


public class TwoLineTextLayout extends RelativeLayout {
    protected TextView tv_unit, tv_data;
    private String sunit, sdata;
    private int tv_unitColor, tv_dataColor;
    private float tv_unitSize, tv_dataSize;
    private int layoutOrientation = 0;
    private boolean enable = true;
    private Typeface dataTypeface, unitTypeface;
    private boolean unitOnTop = false;
    private float marginTop = 0;

    public int getTv_unitColor() {
        return tv_unitColor;
    }

    public void setTv_unitColor(int tv_unitColor) {
        this.tv_unitColor = tv_unitColor;
        tv_unit.setTextColor(tv_unitColor);
    }

    public String getSunit() {
        return sunit;
    }

    public void setSunit(String sunit) {
        this.sunit = sunit;
        tv_unit.setText(sunit);
    }

    public String getSdata() {
        return sdata;
    }

    public void setSdata(String sdata) {
        this.sdata = sdata;
        tv_data.setText(sdata);
    }

    public void setSdata(CharSequence sdata) {
        this.sdata = sdata.toString();
        tv_data.setText(sdata);
    }

    public TwoLineTextLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttrs(attrs);
        init();
    }

    public TwoLineTextLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        init();
    }

    public TwoLineTextLayout(Context context) {
        super(context);
        init();
    }

    public int getTv_dataColor() {
        return tv_dataColor;
    }

    public void setTv_dataColor(int tv_dataColor) {
        this.tv_dataColor = tv_dataColor;
        tv_data.setTextColor(tv_dataColor);
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs,
                    R.styleable.TwoLineTextLayout);
            tv_unitColor = a.getColor(
                    R.styleable.TwoLineTextLayout_unittextColor, Color.BLACK);
            tv_dataColor = a.getColor(
                    R.styleable.TwoLineTextLayout_datatextColor, Color.BLACK);

            String typeface = a.getString(
                    R.styleable.TwoLineTextLayout_datatype);
            if (typeface != null) {
                dataTypeface = Typeface.createFromAsset(this.getContext().getAssets(), typeface);
            }

            typeface = a.getString(
                    R.styleable.TwoLineTextLayout_unittype);
            if (typeface != null) {
                unitTypeface = Typeface.createFromAsset(this.getContext().getAssets(), typeface);
            }

            marginTop = a.getDimension(
                    R.styleable.TwoLineTextLayout_marginTop, 0);
            Log.e("XXXX","marginTop = "+marginTop);

            tv_unitSize = a.getDimension(
                    R.styleable.TwoLineTextLayout_unittextSize, 14);
            tv_dataSize = a.getDimension(
                    R.styleable.TwoLineTextLayout_datatextSize, 14);

            layoutOrientation = a.getInt(
                    R.styleable.TwoLineTextLayout_layoutOrientation, 0);

            sunit = a.getString(R.styleable.TwoLineTextLayout_unittext);
            sdata = a.getString(R.styleable.TwoLineTextLayout_datatext);
            unitOnTop = a.getBoolean(R.styleable.TwoLineTextLayout_unitOnTop,
                    true);
            a.recycle();
        }
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void init() {
        if (layoutOrientation == 0) {
            // 居中对齐
            this.setGravity(Gravity.CENTER_VERTICAL);
        } else if (layoutOrientation == 1) {
            // 靠左对齐
            this.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        }

        tv_unit = new TextView(this.getContext());

        tv_unit.setId(R.id.id_1);
        tv_unit.setText(sunit != null ? sunit : "0");

        tv_unit.setTextColor(tv_unitColor);
        tv_unit.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_unitSize);

        tv_data = new TextView(this.getContext());
        tv_data.setId(R.id.id_2);
        tv_data.setText(sdata != null ? sdata : "0");
//        tv_data.setBackgroundColor(Color.RED);
        tv_data.setTextColor(tv_dataColor);
        tv_data.setTextSize(TypedValue.COMPLEX_UNIT_PX, tv_dataSize);
        if (dataTypeface != null) {
            tv_data.setTypeface(dataTypeface);
        }
        if (unitTypeface != null) {
            tv_unit.setTypeface(unitTypeface);
        }
        if (unitOnTop) {
            LayoutParams rl_data = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rl_data.addRule(RelativeLayout.BELOW, R.id.id_1);
            if (layoutOrientation == 0) {
                rl_data.addRule(RelativeLayout.CENTER_HORIZONTAL,
                        RelativeLayout.TRUE);
            }
            if (marginTop != 0) {
                rl_data.setMargins(0,(int)marginTop, 0, 0);
            }

            LayoutParams rl_unit = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (layoutOrientation == 0) {
                rl_unit.addRule(RelativeLayout.CENTER_HORIZONTAL,
                        RelativeLayout.TRUE);

            }
            this.addView(tv_unit, rl_unit);
            this.addView(tv_data, rl_data);
        } else {
            LayoutParams rl_unit = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rl_unit.addRule(RelativeLayout.BELOW, R.id.id_2);
            if (layoutOrientation == 0) {
                rl_unit.addRule(RelativeLayout.CENTER_HORIZONTAL,
                        RelativeLayout.TRUE);

            }
            if (marginTop != 0) {
                rl_unit.setMargins(0, (int)marginTop, 0, 0);
            }
            LayoutParams rl_data = new LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            if (layoutOrientation == 0) {
                rl_data.addRule(RelativeLayout.CENTER_HORIZONTAL,
                        RelativeLayout.TRUE);
            }
            this.addView(tv_data, rl_data);
            this.addView(tv_unit, rl_unit);

        }

    }
}
