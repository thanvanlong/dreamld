package com.example.dreamtale.ui.mediaplayer;

import android.util.Log;

import com.example.dreamtale.base.BaseCallback;
import com.example.dreamtale.base.BasePresenterImpl;
import com.example.dreamtale.network.ServiceBuilder;
import com.example.dreamtale.network.dto.Comment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.utils.DialogUtils;

public class MediaControlPresenterImpl extends BasePresenterImpl<MediaControlView> implements MediaControlPresenter {
    public MediaControlPresenterImpl(MediaControlView view) {
        super(view);
    }

    @Override
    public void getContentList(int id, int pageSize, int pageNumber) {
        ServiceBuilder.getService().getListContent(id, pageSize, pageNumber).enqueue(new BaseCallback<ContentDTO<Content>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {

            }

            @Override
            public void onResponse(ContentDTO<Content> data) {
                mView.loadListContent(data);
            }

            @Override
            public void onNeedBuy(String message) {
                mView.popupMessage();
            }
        });
    }

    @Override
    public void getComment(int id, int pageSize, int pageNumber, int type) {
        ServiceBuilder.getService().getComment(pageSize, pageNumber, id, type).enqueue(new BaseCallback<ContentDTO<Comment>>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                Log.e("longtv", "sdfghjk error");
            }

            @Override
            public void onResponse(ContentDTO<Comment> data) {
                Log.e("longtv", "response");
                mView.loadComment(data);

            }
        });
    }

    @Override
    public void doComment(Comment comment) {
        DialogUtils.showProgressDialog(getViewContext());
        ServiceBuilder.getService().doComment(comment).enqueue(new BaseCallback<Comment>() {
            @Override
            public void onError(String errorCode, String errorMessage) {
                DialogUtils.dismissProgressDialog(getViewContext());
                DialogUtils.showToastMessage(errorMessage, getViewContext(), true);
            }

            @Override
            public void onResponse(Comment data) {
                DialogUtils.dismissProgressDialog(getViewContext());
                mView.doCommentSuccess(data);
            }
        });
    }

}
