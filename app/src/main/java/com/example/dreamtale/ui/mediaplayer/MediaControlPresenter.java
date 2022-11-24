package com.example.dreamtale.ui.mediaplayer;

import com.example.dreamtale.base.BasePresenter;

public interface MediaControlPresenter extends BasePresenter {
    void getContentList(long id, int pageSize, int pageNumber);
}
