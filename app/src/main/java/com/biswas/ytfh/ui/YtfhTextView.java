package com.biswas.ytfh.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.biswas.ytfh.R;
import com.biswas.ytfh.app.YtfhApplication;

public class YtfhTextView extends AppCompatTextView {


    public static final int FONT_YTFH_REGULAR = 0x00;
    public static final int FONT_YTFH_REGULAR_MEDIUM = 0x01;
    public static final int FONT_YTFH_REGULAR_BOLD = 0x02;


    YtfhApplication mApp;

    public YtfhTextView(Context context) {
        super(context);
        init(null);
    }

    public YtfhTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public YtfhTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void setFont(int fontName) {
        switch (fontName) {
            case FONT_YTFH_REGULAR:
                setTypeface(mApp.getYtfhFontRegular());
                break;
            case FONT_YTFH_REGULAR_BOLD:
                setTypeface(mApp.getYtfhFontBold());
                break;
            case FONT_YTFH_REGULAR_MEDIUM:
                setTypeface(mApp.getYtfhFontMedium());
                break;
            default:
                setTypeface(mApp.getYtfhFontRegular());
        }
    }

    private void init(AttributeSet attrs) {
        mApp = (YtfhApplication) getContext().getApplicationContext();
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.YtfhTextView);
            int fontName = a.getInteger(R.styleable.YtfhTextView_fontName, FONT_YTFH_REGULAR);
            setFont(fontName);
            a.recycle();
        } else
            setTypeface(mApp.getYtfhFontRegular());
    }
}
