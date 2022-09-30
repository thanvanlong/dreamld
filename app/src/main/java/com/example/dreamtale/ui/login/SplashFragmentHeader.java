package com.example.dreamtale.ui.login;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;

import butterknife.BindView;

public class SplashFragmentHeader extends BaseFragment<LoginActivityPresenter, SplashActivity> {

    @BindView(R.id.img_header)
    protected ImageView imgHeader;
    @BindView(R.id.txt_header)
    protected TextView txtHeader;

    private int mImgHeader;
    private int mTxtHeader;

    public SplashFragmentHeader(int mImgHeader, int mTxtHeader) {
        this.mImgHeader = mImgHeader;
        this.mTxtHeader = mTxtHeader;
    }

    public SplashFragmentHeader(int mTxtHeader) {
        this.mTxtHeader = mTxtHeader;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_header;
    }

    @Override
    public void onPrepareLayout() {
        if (mImgHeader != -1 ) {
            imgHeader.setImageResource(mImgHeader);
        }
        if (mTxtHeader != -1) {
            txtHeader.setText(getString(mTxtHeader));
        }
    }

    @Override
    public LoginActivityPresenter onCreatePresenter() {
        return null;
    }
}
