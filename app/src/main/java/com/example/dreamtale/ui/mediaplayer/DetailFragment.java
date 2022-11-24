package com.example.dreamtale.ui.mediaplayer;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.dreamtale.R;
import com.example.dreamtale.base.BaseFragment;
import com.example.dreamtale.common.adater.ContentAdapter;
import com.example.dreamtale.common.constant.Constant;
import com.example.dreamtale.common.view.HorizontalItemDecoration;
import com.example.dreamtale.network.dto.Box;
import com.example.dreamtale.network.dto.Content;
import com.example.dreamtale.network.dto.ContentDTO;
import com.example.dreamtale.ui.home.HomeActivity;
import com.example.dreamtale.ui.home.HomeBoxView;
import com.example.dreamtale.ui.home.HomePresenter;
import com.example.dreamtale.utils.CompatibilityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class DetailFragment extends BaseFragment<MediaControlPresenter, HomeActivity> implements MediaControlView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.rcy_related)
    RecyclerView rcyContentRelated;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.btn_read_more)
    Button btnReadMore;
    ContentAdapter contentAdapter;
    private Content content;
    private int currentPage;
    private List<Content> contentList;
    private boolean isLoading = false;
    private int totalPage = 0;

    private static DetailFragment instance;

    public static DetailFragment getInstance() {
        return instance;
    }

    public static void setInstance(DetailFragment instance) {
        DetailFragment.instance = instance;
    }

    public synchronized static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        instance = fragment;
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_story_detail;
    }

    @Override
    public void onPrepareLayout() {
        Bundle bundle = getArguments();

        content = (Content) bundle.getSerializable(Constant.Extras.DATA);
        tvDesc.setText(content.getDescription());
        tvDesc.setMovementMethod(new ScrollingMovementMethod());
        
        loadData();

        rcyContentRelated.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager layoutManager = (GridLayoutManager) rcyContentRelated.getLayoutManager();

                int v1 = layoutManager.getChildCount();
                int v2 = layoutManager.getItemCount();
                int v3 = layoutManager.findFirstCompletelyVisibleItemPosition();

                if (v1 + v3 > v2 && !isLoading && currentPage <= totalPage) {
                    currentPage ++;
                    isLoading = true;
                    loadMoreData();
                }
            }
        });
    }

    public void loadData() {
        getPresenter().getContentList(content.getId(), 10, 1);
    }

    public void loadMoreData() {
        getPresenter().getContentList(10, currentPage, 10);
    }

    @Override
    public MediaControlPresenter onCreatePresenter() {
        return new MediaControlPresenterImpl(this);
    }


    @Override
    public void loadListContent(ContentDTO<Content> data) {
        if (data == null ) {
            return;
        }
        if (contentList.size() > 0) {
            contentList.clear();
        }
        contentList.addAll(data.getContentList());
        contentAdapter = new ContentAdapter(contentList, getViewContext());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getViewContext(), 2);
        rcyContentRelated.setLayoutManager(layoutManager);
        rcyContentRelated.addItemDecoration(new HorizontalItemDecoration(CompatibilityUtils.getItemSpacing(getViewContext())));
        rcyContentRelated.setAdapter(contentAdapter);

        totalPage = data.getMetaData().getTotalPages();
    }

    @Override
    public void onRefresh() {
        if (isLoading) {
            return;
        }

        isLoading = true;
        loadData();
    }
}
