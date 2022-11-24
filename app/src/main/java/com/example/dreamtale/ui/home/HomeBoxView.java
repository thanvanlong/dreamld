package com.example.dreamtale.ui.home;

import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;

import java.util.List;

public interface HomeBoxView extends BaseView<HomePresenter> {
    void loadHomeBox(ContentDTO<Box> data);
    void loadDataFailed();
}
