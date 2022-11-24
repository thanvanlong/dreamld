package com.example.dreamtale.ui.mediaplayer;

import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.network.ServiceBuilder;

public class MediaControlPresenterImpl extends BasePresenterImpl<MediaControlView> implements MediaControlPresenter {
    public MediaControlPresenterImpl(MediaControlView view) {
        super(view);
    }

    @Override
    public void getContentList(long id, int pageSize, int pageNumber) {
//        ServiceBuilder.getService().
    }
}
