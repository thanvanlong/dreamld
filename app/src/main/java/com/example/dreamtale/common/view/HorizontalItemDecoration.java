package com.example.dreamtale.common.view;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class HorizontalItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpace;
    private boolean mIsRate;

    public HorizontalItemDecoration(int space) {
        this.mSpace = space / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.top = mSpace;
        if (mIsRate) {
            outRect.right = mSpace;
        } else {
            outRect.right = mSpace;
        }

        outRect.bottom = mSpace*2;
        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = mSpace;
        } else {
            outRect.left = mSpace;
        }
    }
}
