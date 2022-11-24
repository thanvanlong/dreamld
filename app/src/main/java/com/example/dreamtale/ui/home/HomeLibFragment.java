package com.example.dreamtale.ui.home;

import android.view.View;
import android.widget.Button;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.ui.login.LoginFragment;

import butterknife.BindView;

public class HomeLibFragment extends BaseFragment<HomePresenter, HomeActivity> {

//    @BindView(R.id.btn_read_more)
//    Button btnReadMore;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onPrepareLayout() {
//        btnReadMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }

    @Override
    public HomePresenter onCreatePresenter() {
        return null;
    }
}
