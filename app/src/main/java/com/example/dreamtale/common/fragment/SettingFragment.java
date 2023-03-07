package com.example.dreamtale.common.fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dreamtale.R;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.ui.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingFragment extends DialogFragment {
    @BindView(R.id.btn_payment)
    Button btnBuyPackage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        getDialog().getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
        WindowManager.LayoutParams p = getDialog().getWindow().getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE;
        p.x = 0;
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        btnBuyPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                Bundle bundle = new Bundle();
                bundle.putBoolean(Constant.Extras.CAN_SEARCH, false);
                bundle.putBoolean(Constant.Extras.TOOL_TITLE, true);
                bundle.putBoolean(Constant.Extras.NAVIGATION, false);
                bundle.putString(Constant.Extras.TITLE, "Buy package");
                HomeActivity.getInstance().addFragment(R.id.frg_common_content, new PackagePaymentFragment(), bundle, true, PackagePaymentFragment.class.getSimpleName());
            }
        });
    }
}
