package com.example.dreamtale.base;

import androidx.databinding.BaseObservable;

public class BasePresenterImpl<V extends BaseView> extends BaseObservable implements BasePresenter {
    protected V mView;

    public BasePresenterImpl(V view) {
        mView = view;

    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public BaseActivity getViewContext() {
        return mView.getViewContext();
    }

    public String getString(int id, Object... args) {
        return getViewContext() != null ?  getViewContext().getString(id, args) : null;
    }
}
