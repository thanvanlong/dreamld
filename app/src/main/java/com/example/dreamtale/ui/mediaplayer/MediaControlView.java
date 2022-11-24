package com.example.dreamtale.ui.mediaplayer;

import com.example.dreamtale.base.BaseView;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;

import java.util.List;

public interface MediaControlView extends BaseView<MediaControlPresenter> {
    void loadListContent(ContentDTO<Content> data);
}
