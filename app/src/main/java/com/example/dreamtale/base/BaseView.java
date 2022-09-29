package com.example.dreamtale.base;

public interface BaseView<P extends BasePresenter> {

    void onPrepareLayout();
    P getPresenter();
    P onCreatePresenter();

}
