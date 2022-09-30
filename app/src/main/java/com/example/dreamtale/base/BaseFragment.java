package com.example.dreamtale.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<B extends BasePresenter, A extends BaseActivity>extends Fragment implements BaseView<B>  {
    protected View mRootView;
    protected boolean mIsInit = false;
    private B mPresenter;
    private Unbinder mUnbinder;

    public boolean ismIsInit() {
        return mIsInit;
    }

    public void setmIsInit(boolean mIsInit) {
        this.mIsInit = mIsInit;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            mPresenter = (B) onCreatePresenter();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        if (!mIsInit) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            mUnbinder = ButterKnife.bind(this, mRootView);
            onPrepareLayout();
            mIsInit = true;
        }

        return mRootView;
    }

    @Override
    public B getPresenter() {
        return mPresenter;
    }

    @Override
    public BaseActivity getViewContext() {
        return getBaseActivity();
    }

    protected A getBaseActivity() {
        return (A) getActivity();
    }

    public abstract int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
    }
}
