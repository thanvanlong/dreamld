package com.example.dreamtale.common.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dreamtale.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YesNoDialog extends DialogFragment {
    @BindView(R.id.txt_content_notification)
    protected TextView txtContentNotification;
    @BindView(R.id.btn_dialog_no)
    protected Button btnNo;
    @BindView(R.id.btn_dialog_yes)
    protected Button btnYes;

    private Context mContext;
    private String content;
    private ItemClickListener listener;

    public void setListener (ItemClickListener listener) {
        this.listener = listener;
    }

    public void init (Context context, String content) {
        this.mContext = context;
        this.content = content;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCanceledOnTouchOutside(false);
//        int width = getResources().getDimensionPixelSize(R.dimen.popup_width);
//        int height = getResources().getDimensionPixelSize(R.dimen.popup_height);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return inflater.inflate(R.layout.fragment_yes_no_dialog, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        txtContentNotification.setText(content);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.btnYesClick();
            }
        });


        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.btnNoClick();
            }
        });
    }

    public interface ItemClickListener{
        void btnYesClick();
        void btnNoClick();
    }
}
