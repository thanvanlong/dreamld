package com.example.dreamtale.common.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.adater.ContentAdapter;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomeBoxAdapter;
import com.example.dreamtale.ui.home.HomeBoxView;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.ui.home.HomePresenterImpl;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenter;
import com.example.dreamtale.ui.mediaplayer.MediaControlPresenterImpl;
import com.example.dreamtale.ui.mediaplayer.MediaControlView;
import com.example.dreamtale.utils.CompatibilityUtils;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ListContentFragment extends BaseFragment<MediaControlPresenter, HomeActivity> implements SwipeRefreshLayout.OnRefreshListener, MediaControlView {

    @BindView(R.id.rcy_list_content)
    RecyclerView rcyContentList;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.shimmer_content)
    ShimmerFrameLayout shimmerFrameLayout;

    private List<Content> contentList;
    int currentPage = 1;
    private boolean isRefreshing = false;
    private boolean isLoading = false;
    private int totalPage = 0;

    public ListContentFragment(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list_content;
    }

    @Override
    public void onPrepareLayout() {
        loadData();

        rcyContentList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) rcyContentList.getLayoutManager();

                int v1 = layoutManager.getChildCount();
                int v2 = layoutManager.getItemCount();
                int v3 = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (v1 + v3 > v2 && !isRefreshing && currentPage <= totalPage) {
                    currentPage ++;
                    isRefreshing = true;
                    loadMoreData();
                }
            }
        });
    }

    @Override
    public MediaControlPresenter onCreatePresenter() {
        return new MediaControlPresenterImpl(this);
    }

    public void loadData() {
        getPresenter().getContentList(10, 10, 1);
    }

    public void loadMoreData() {
        getPresenter().getContentList(10, currentPage, 10);
    }


    @Override
    public void onRefresh() {
        if (isRefreshing) {
            return;
        }

        isRefreshing = true;
        loadData();
    }

    @Override
    public void loadListContent(ContentDTO<Content> data) {
        shimmerFrameLayout.setVisibility(View.GONE);
        isRefreshing = false;
        refreshLayout.setRefreshing(false);
        contentList = data.getContentList();

        int width = CompatibilityUtils.getWidthContentRelatedItem(getViewContext());
        ContentAdapter contentAdapter = new ContentAdapter(contentList, width, getViewContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getViewContext(), 2);

        rcyContentList.setLayoutManager(layoutManager);
        rcyContentList.addItemDecoration(new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(getViewContext())));
        rcyContentList.setAdapter(contentAdapter);

        totalPage = data.getMetaData().getTotalPages();

    }
}
