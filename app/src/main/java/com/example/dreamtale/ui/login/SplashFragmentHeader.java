package com.example.dreamtale.ui.login;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;

import butterknife.BindView;

public class SplashFragmentHeader extends BaseFragment<LoginPresenter, OnBoardingActivity> {

    @BindView(R.id.img_header)
    protected ImageView imgHeader;
    @BindView(R.id.txt_header)
    protected TextView txtHeader;

    private int mImgHeader;
    private int mTxtHeader;
    private static SplashFragmentHeader mInstance;

    public static SplashFragmentHeader getmInstance() {
        return mInstance;
    }

    public static void setmInstance(SplashFragmentHeader mInstance) {
        SplashFragmentHeader.mInstance = mInstance;
    }

    public SplashFragmentHeader(int mTxtHeader) {
        this.mTxtHeader = mTxtHeader;
    }

    public ImageView getImgHeader() {
        return imgHeader;
    }

    public void setImgHeader(ImageView imgHeader) {
        this.imgHeader = imgHeader;
    }

    public TextView getTxtHeader() {
        return txtHeader;
    }

    public void setTxtHeader(TextView txtHeader) {
        this.txtHeader = txtHeader;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_splash_header;
    }

    @Override
    public void onPrepareLayout() {
        setmInstance(this);
    }

    @Override
    public LoginPresenter onCreatePresenter() {
        return null;
    }
}
