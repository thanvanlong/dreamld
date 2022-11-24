package com.example.dreamtale.common.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import com.example.dreamtale.R;
import com.example.dreamtale.ui.login.CategoryAdapter;

public class CategoryBoxView extends LinearLayout{

    public CategoryBoxView(Context context) {
        super(context);
        init(context);
    }

    public CategoryBoxView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CategoryBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CategoryBoxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        this.setMeasuredDimension(widthMeasureSpec, (int) (widthSize * 9 /16));

    }

    public void init(Context context) {
//        setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Drawable unwrappedDrawable = AppCompatResources.getDrawable(context, R.drawable.bg_category_selected);
////                view.setBackground(unwrappedDrawable);
//            }
//        });
    }
//
//    @Override
//    public void onClick(View view) {
//        Log.e("longtv", "onClick view custom: " );
//    }
}
