package com.example.dreamtale.ui.home;

import com.example.dreamtale.base.BasePresenter;

public interface HomePresenter extends BasePresenter {
    void getHomeData(int pageSize, int pageNumber);
}
