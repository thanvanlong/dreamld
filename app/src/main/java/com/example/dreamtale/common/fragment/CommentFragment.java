package com.example.dreamtale.common.fragment;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenter;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenterImpl;
import com.example.dreamtale.ui.mediaplayer.MediaControlView;

import java.util.List;

public class CommentFragment extends BaseFragment<MediaControlPresenter, HomeActivity> implements SwipeRefreshLayout.OnRefreshListener, MediaControlView {

    @Override
    public int getLayoutId() {
        return R.layout.fragment_comment;
    }

    @Override
    public void onPrepareLayout() {

    }

    @Override
    public MediaControlPresenter onCreatePresenter() {
        return new MediaControlPresenterImpl(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void loadListContent(ContentDTO<Content> data) {

    }
}
