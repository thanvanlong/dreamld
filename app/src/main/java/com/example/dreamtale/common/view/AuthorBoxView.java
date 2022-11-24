package com.example.dreamtale.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class AuthorBoxView extends LinearLayout {
    public AuthorBoxView(Context context) {
        super(context);
    }

    public AuthorBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AuthorBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AuthorBoxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        this.setMeasuredDimension(widthMeasureSpec, (int) (widthSize * 6 /16));

    }
}
