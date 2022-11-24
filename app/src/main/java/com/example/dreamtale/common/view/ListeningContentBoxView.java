package com.example.dreamtale.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class ListeningContentBoxView extends LinearLayout {
    public ListeningContentBoxView(Context context) {
        super(context);
    }

    public ListeningContentBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ListeningContentBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ListeningContentBoxView(Context context, AttributeSet attrs, int defStyleAttr,
                                   int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        this.setMeasuredDimension(widthMeasureSpec, (int) (widthSize * 7 /16));

    }
}
