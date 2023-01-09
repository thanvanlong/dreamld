package com.example.dreamtale.ui.mediaplayer;

import com.example.dreamtale.base.BasePresenter;
import com.example.dreamtale.network.dto.Comment;

public interface MediaControlPresenter extends BasePresenter {
    void getContentList(int id, int pageSize, int pageNumber);
    void getComment(int id, int pageSize, int pageNumber, int type);
    void doComment(Comment comment);
}
